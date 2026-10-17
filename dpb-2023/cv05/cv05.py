import datetime

from pymongo.cursor import Cursor

from init import collection

'''
DPB - 5. Cvičení

Implementujte jednotlivé body pomocí PyMongo knihovny - rozhraní je téměř stejné jako v Mongo shellu.
Před testováním Vašich řešení si nezapomeňte zapnout Mongo v Dockeru.

Pro pomoc je možné např. použít https://www.w3schools.com/python/python_mongodb_getstarted.asp

Funkce find vrací kurzor - pro vypsání výsledku je potřeba pomocí foru iterovat nad kurzorem:

cursor = collection.find(...)
for restaurant in cursor:
    print(restaurant) # případně print(restaurant['name'])

Všechny výsledky limitujte na 10 záznamů. Nepoužívejte české názvy proměnných!
'''


def _(cursor: Cursor):
    cursor = cursor.limit(10)
    for document in cursor:
        print(document)
    return cursor


def print_delimiter(n):
    print('\n', '#' * 10, 'Úloha', n, '#' * 10, '\n')


# 1. Vypsání všech restaurací
print_delimiter(1)
_(collection.find())

# 2. Vypsání všech restaurací - pouze názvů, abecedně seřazených
print_delimiter(2)
_(collection.find().sort('name').skip(1))

# 3. Vypsání pouze 10 záznamů z předchozího dotazu
print_delimiter(3)
_(collection.find({}))

# 4. Zobrazte dalších 10 záznamů
print_delimiter(4)
_(collection.find({}).skip(10))

# 5. #Vypsání restaurací ve čtvrti Bronx (čtvrť = borough)
print_delimiter(5)
_(collection.find({'borough': 'Bronx'}))

# 6. Vypsání restaurací, jejichž název začíná na písmeno M
print_delimiter(6)
_(collection.find({'name': {'$regex': '^M'}}))

# 7. Vypsání restaurací, které mají skóre větší než 80
print_delimiter(7)
_(collection.find({'grades.score': {'$gt': 80}}))

# 8. Vypsání restaurací, které mají skóre mezi 80 a 90
print_delimiter(8)
_(collection.find({"grades": {"$elemMatch": {"score": {"$gte": 80, "$lte": 90}}}}))

'''
Bonusové úlohy:
'''

# 9. Vypsání všech restaurací, které mají skóre mezi 80 a 90 a zároveň nevaří americkou (American) kuchyni
print_delimiter(9)
_(collection.find({'grades': {'$elemMatch': {'score': {'$gt': 80, '$lt': 90}}}, 'cuisine': {'$ne': 'American '}}))

# 10. Vypsání všech restaurací, které mají alespoň osm hodnocení
print_delimiter(10)
_(collection.find({'grades.7': {'$exists': True}}))
print('-')
_(collection.find({'$where': 'this.grades.length >= 8'}))

# 11. Vypsání všech restaurací, které mají alespoň jedno hodnocení z roku 2014 
print_delimiter(11)
_(collection.find(
    {'grades.date': {'$gte': datetime.datetime(2014, 1, 1), '$lt': datetime.datetime(2015, 1, 1)}},
    {'name': 1, 'grades': 1}))

'''
V této části budete opět vytvářet vlastní restauraci.

Řešení:
Vytvořte si vaši restauraci pomocí slovníku a poté ji vložte do DB.
restaurant = {
    ...
}
'''

# 12. Uložte novou restauraci (stačí vyplnit název a adresu)
print_delimiter(12)
my_restaurant = collection.insert_one({
    'name': 'Pizzeria',
    'address': {
        'street': 'Karlovo náměstí',
        'building': '1',
        'zipcode': '11000',
        'coord': [-73.9557413, 40.7720266],
    }
}).inserted_id
print(my_restaurant)

# 13. Vypište svoji restauraci
print_delimiter(13)
print(collection.find_one({'_id': my_restaurant}))

# 14. Aktualizujte svoji restauraci - změňte libovolně název
print_delimiter(14)
print(collection.update_one({'_id': my_restaurant}, {'$set': {'name': 'Pizzeria 2'}}).raw_result)

# 15. Smažte svoji restauraci
# 15.1 pomocí id (delete_one)
# 15.2 pomocí prvního nebo druhého názvu (delete_many, využití or)
print_delimiter(15)
print(collection.delete_one({'_id': my_restaurant}).raw_result)
print(collection.delete_many({'name': {'$in': ['Pizzeria', 'Pizzeria 2']}}).raw_result)
'''
Poslední částí tohoto cvičení je vytvoření jednoduchého indexu.

Použijte např. 3. úlohu s vyhledáváním čtvrtě Bronx. První použijte Váš již vytvořený dotaz a na výsledek použijte:

cursor.explain()['executionStats'] - výsledek si vypište na výstup a všimněte si položky 'totalDocsExamined'

Poté vytvořte index na 'borough', zopakujte dotaz a porovnejte hodnoty 'totalDocsExamined'.

S řešením pomůže https://pymongo.readthedocs.io/en/stable/api/pymongo/collection.html#pymongo.collection.Collection.create_index
'''
print_delimiter(11)
collection.drop_index('borough_1')
print(collection.find({'borough': 'Bronx'}).explain()['executionStats'])
collection.create_index('borough')
print(collection.find({'borough': 'Bronx'}).explain()['executionStats'])
