from collections import defaultdict
from cassandra.cluster import Cluster

'''
DPB - 11. cvičení Cassandra

Use case: Discord server - reálně používáno pro zprávy, zde pouze zjednodušená varianta.

Instalace python driveru: pip install cassandra-driver

V tomto cvičení se budou následující úlohy řešit s využitím DataStax driveru pro Cassandru.
Dokumentaci lze nalézt zde: https://docs.datastax.com/en/developer/python-driver/3.25/getting_started/


Optimální řešení (nepovinné) - pokud něco v db vytváříme, tak první kontrolujeme, zda to již neexistuje.


Pro uživatele PyCharmu:

Pokud chcete zvýraznění syntaxe, tak po napsání prvního dotazu se Vám u něj objeví žlutá žárovka, ta umožňuje vybrat 
jazyk pro tento projekt -> vyberte Apache Cassandra a poté Vám nabídne instalaci rozšíření pro tento typ db.
Zvýraznění občas nefunguje pro příkaz CREATE KEYSPACE.

Také je možné do PyCharmu připojit databázi -> v pravé svislé liště najděte Database a připojte si lokální Cassandru.
Řešení cvičení chceme s využitím DataStax driveru, ale s integrovaným nástrojem pro databázi si můžete pomoct sestavit
příslušně příkazy.


Pokud se Vám nedaří připojit se ke Cassandře v Dockeru, zkuste smazat kontejner a znovu spustit:

docker run --name dpb_cassandra -p 127.0.0.1:9042:9042 -p 127.0.0.1:9160:9160 -d cassandra:latest

'''


def print_delimiter(n):
    print('\n', '#' * 10, 'Úloha', n, '#' * 10, '\n')


def print_result(result):
    for row in result:
        print(row)


cluster = Cluster()  # automaticky se připojí k localhostu na port 9042
session = cluster.connect()

"""
1. Vytvořte keyspace 'dc' a přepněte se do něj (SimpleStrategy, replication_factor 1)
"""

print_delimiter(1)
session.execute(
    "CREATE KEYSPACE IF NOT EXISTS dc WITH REPLICATION = {'class': 'SimpleStrategy', 'replication_factor': 1}"
)
session.set_keyspace('dc')

"""
2. V csv souboru message_db jsou poskytnuta data pro cvičení. V prvním řádku naleznete názvy sloupců.
   Vytvořte tabulku messages - zvolte vhodné datové typy (time bude timestamp)
   Primárním klíčem bude room_id a time
   Data chceme mít seřazené podle času, abychom mohli rychle získat poslední zprávy

   Jako id v této úloze zvolíme i time - zdůvodněte, proč by se v praxi time jako id neměl používat.

   Pokud potřebujeme použít čas, tak se v praxi používá typ timeuuid nebo speciální identifikátor, tzv. Snowflake ID
   (https://en.wikipedia.org/wiki/Snowflake_ID). Není potřeba řešit v tomto cvičení.
"""

print_delimiter(2)
session.execute(
    "CREATE TABLE IF NOT EXISTS messages (room_id int, time timestamp, user_id int, message text, PRIMARY KEY (room_id, time)) WITH CLUSTERING ORDER BY (time DESC)"
)

"""
3. Do tabulky messages importujte message_db.csv
   COPY není možné spustit pomocí DataStax driveru ( 'copy' is a cqlsh (shell) command rather than a CQL (protocol) command)
   -> 2 možnosti:
      a) Nakopírovat csv do kontejneru a spustit COPY příkaz v cqlsh konzoli uvnitř dockeru
      b) Napsat import v Pythonu - otevření csv a INSERT dat
CSV soubor může obsahovat chybné řádky - COPY příkaz automaticky přeskočí řádky, které se nepovedlo správně parsovat
"""

print_delimiter(3)
print('inserting...')
if False:
    with open('message_db.csv', 'r') as f:
        next(f)
        for i, line in enumerate(f):
            line = line.strip()
            if line:
                data = line.split(';')
                session.execute(
                    f"INSERT INTO messages (room_id, user_id, time, message) VALUES ({data[0]}, {data[1]}, '{data[2]}', $${data[3]}$$);"
                )
print('done')
"""
4. Kontrola importu - vypište 1 zprávu
"""

print_delimiter(4)
print(session.execute("SELECT COUNT(*) FROM messages").all())

"""
5. Vypište posledních 5 zpráv v místnosti 1 odeslaných uživatelem 2
    Nápověda 1: Sekundární index (viz přednáška) 
    Nápověda 2: Data jsou řazena již při vkládání
"""

print_delimiter(5)
session.execute("CREATE INDEX IF NOT EXISTS users ON messages (user_id); ")
for record in session.execute("SELECT * FROM messages WHERE room_id=1 AND user_id=2 LIMIT 5;").all():
    print(record)

"""
6. Vypište počet zpráv odeslaných uživatelem 2 v místnosti 1
"""

print_delimiter(6)
print(session.execute("SELECT COUNT(*) FROM messages WHERE room_id=1 AND user_id=2;").all())

"""
7. Vypište počet zpráv v každé místnosti
"""

print_delimiter(7)
print(session.execute("SELECT room_id, COUNT(*) FROM messages GROUP BY room_id;").all())

"""
8. Vypište id všech místností (3 hodnoty)
"""

print_delimiter(8)
print(session.execute("SELECT DISTINCT room_id FROM messages;").all())

"""
Bonusové úlohy:

1. Pro textovou analýzu chcete poskytovat anonymizovaná textová data. Vytvořte Materialized View pro tabulku messages,
který bude obsahovat pouze čas, room_id a zprávu.

Vypište jeden výsledek z vytvořeného view

Před začátkem řešení je potřeba jít do souboru cassandra.yaml uvnitř docker kontejneru a nastavit enable_materialized_views=true

docker exec -it dpb_cassandra bash
sed -i -r 's/enable_materialized_views: false/enable_materialized_views: true/' /etc/cassandra/cassandra.yaml

Poté restartovat kontejner

2. Chceme vytvořit funkci (UDF), která při výběru dat vrátí navíc příznak, zda vybraný text obsahuje nevhodný výraz.

Vyberte jeden výraz (nemusí být nevhodný:), vytvořte a otestujte Vaši funkci.

Potřeba nastavit enable_user_defined_functions=true v cassandra.yaml

sed -i -r 's/enable_user_defined_functions: false/enable_user_defined_functions: true/' /etc/cassandra/cassandra.yaml

3. Zjistěte čas odeslání nejnovější a nejstarší zprávy.

4. Zjistěte délku nejkratší a nejdelší zprávy na serveru.	

5. Pro každého uživatele zjistěte průměrnou délku zprávy.		

V celém cvičení by nemělo být použito ALLOW FILTERING.
"""

print_delimiter('BONUS 1')
session.execute(
    "CREATE MATERIALIZED VIEW IF NOT EXISTS materialized_messages AS SELECT room_id, time, message FROM messages WHERE room_id IS NOT NULL AND time IS NOT NULL PRIMARY KEY (room_id, time);"
)

print(session.execute("SELECT * FROM materialized_messages LIMIT 1;").all())

print_delimiter('BONUS 2')
session.execute("""
    CREATE OR REPLACE FUNCTION contains_profanity (input text)
    CALLED ON NULL INPUT
    RETURNS boolean
    LANGUAGE java
    AS $$
      return input.contains("mos spatny slovo");
    $$;
 """)

print(session.execute(
    """SELECT message, contains_profanity(message) as contains_profanity FROM messages LIMIT 1;
""").all())

print_delimiter('BONUS 3')
print(session.execute("""
    SELECT MAX(time) AS latest_msg, MIN(time) AS oldest_msg FROM messages;
""").all())

print_delimiter('BONUS 4')
session.execute("""
    CREATE OR REPLACE FUNCTION len (input text)
    RETURNS NULL ON NULL INPUT
    RETURNS int
    LANGUAGE java
    AS $$
        return input.length();
    $$;
""")

print(session.execute("""
    SELECT MAX(len(message)) AS longest_msg, MIN(len(message)) AS shortest_msg FROM messages;
                      """).all())

print_delimiter('BONUS 5')
message_lengths = defaultdict(int)
message_counts = defaultdict(int)

messages = session.execute("SELECT user_id, message FROM messages")

for message in messages:
    user_id, message_text = message.user_id, message.message
    message_lengths[user_id] += len(message_text)
    message_counts[user_id] += 1

average_message_lengths = {
    user_id: message_lengths[user_id] / message_counts[user_id]
    for user_id in message_lengths
}

sorted_average_message_lengths = dict(sorted(average_message_lengths.items()))

for user_id, average_length in sorted_average_message_lengths.items():
    print(user_id, average_length)

