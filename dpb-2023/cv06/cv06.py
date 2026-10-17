from bson import ObjectId
from pymongo.command_cursor import CommandCursor
from pymongo.cursor import Cursor

from init import collection

'''
DPB - 6. cvičení - Agregační roura a Map-Reduce

V tomto cvičení si můžete vybrat, zda ho budete řešit v Mongo shellu nebo pomocí PyMongo knihovny.

Před testováním Vašich řešení si nezapomeňte zapnout Mongo v Dockeru - používáme stejná data jako v minulých cvičeních.

Pro pomoc je možné např. použít https://api.mongodb.com/python/current/examples/aggregation.html a přednášku.

Všechny výsledky limitujte na 10 záznamů. Nepoužívejte české názvy proměnných!

Struktura záznamu v db:
{
  "address": {
     "building": "1007",
     "coord": [ -73.856077, 40.848447 ],
     "street": "Morris Park Ave",
     "zipcode": "10462"
  },
  "borough": "Bronx",
  "cuisine": "Bakery",
  "grades": [
     { "date": { "$date": 1393804800000 }, "grade": "A", "score": 2 },
     { "date": { "$date": 1378857600000 }, "grade": "A", "score": 6 },
     { "date": { "$date": 1358985600000 }, "grade": "A", "score": 10 },
     { "date": { "$date": 1322006400000 }, "grade": "A", "score": 9 },
     { "date": { "$date": 1299715200000 }, "grade": "B", "score": 14 }
  ],
  "name": "Morris Park Bake Shop",
  "restaurant_id": "30075445"
}
'''


def _(cursor: Cursor | CommandCursor):
    if isinstance(cursor, Cursor):
        cursor = cursor.limit(10)
    for document in cursor:
        print(document['name'] if 'name' in document else document)
    return cursor


def print_delimiter(n):
    print('\n', '#' * 10, 'Úloha', n, '#' * 10, '\n')


'''
Agregační roura
Zjistěte počet restaurací pro každé PSČ (zipcode)
 a) seřaďte podle zipcode vzestupně
 b) seřaďte podle počtu restaurací sestupně
Výpis limitujte na 10 záznamů a k provedení použijte collection.aggregate(...)
'''
print_delimiter('1 a)')
# _(collection.aggregate([
#     {'$group': {'_id': '$address.zipcode', 'count': {'$sum': 1}}},
#     {'$sort': {'_id': 1}},
#     {'$limit': 10}
# ]))

print_delimiter('1 b)')
# _(collection.aggregate([
#     {'$group': {'_id': '$address.zipcode', 'count': {'$sum': 1}}},
#     {'$sort': {'count': -1}},
#     {'$limit': 10}
# ]))

'''
Agregační roura

Restaurace obsahují pole grades, kde jsou jednotlivá hodnocení. Vypište průměrné score pro každou hodnotu grade.
V agregaci vynechte grade pro hodnotu "Not Yet Graded" (místo A, B atd. se může vyskytovat tento řetězec).

'''
print_delimiter(2)
# _(collection.aggregate([
#     {'$unwind': '$grades'},
#     {'$match': {'grades.grade': {'$ne': 'Not Yet Graded'}}},  # skipujem hodnoceni ktery este nejsou graded
#     {'$group': {'_id': '$grades.grade', 'avg': {'$avg': '$grades.score'}}},  # definuju ouput strukturu
#     {'$sort': {'avg': -1}},
#     {'$limit': 10}
# ]))

# BONUSOVKA MOREEEE
print_delimiter('BONUS 1')
# for doc in collection.aggregate([
#     {'$unwind': '$grades'},
#     {'$match': {'grades.grade': 'A'}},
#     {'$group': {
#         '_id': '$_id',
#         'name': {'$first': '$name'},
#         'average': {'$avg': '$grades.score'},
#         'grade_count': {'$count': {}},
#     }},
#     {'$match': {'grade_count': {'$gte': 3}}},
#     {'$sort': {'average': -1}},
#     {'$limit': 5}
# ]):
#     print(doc)

print_delimiter('BONUS 2')
# for doc in collection.aggregate([
#     {'$unwind': '$grades'},
#     {'$match': {'grades.grade': 'A'}},
#     {'$group': {
#         '_id': '$_id',
#         'cuisine': {'$first': '$cuisine'},
#         'name': {'$first': '$name'},
#         'average': {'$avg': '$grades.score'},
#         'grade_count': {'$count': {}},
#     }},
#     {'$sort': {'average': -1}},
#     {'$group': {
#         '_id': '$cuisine',
#         'average': {'$first': '$average'},
#         'restaurant': {'$first': '$name'},
#     }},
#     {'$sort': {'_id': 1}},
# ]):
#     print(doc)

print_delimiter('BONUS 3')
# for doc in collection.aggregate([
#     {'$addFields': {
#         'wc': {'$size': {'$split': ['$name', ' ']}}
#     }},
#     # only viceslovny nazev
#     {'$match': {'wc': {'$gt': 1}}},
#     {'$unwind': '$grades'},
#     {'$addFields': {
#         'high_score': {
#             '$cond': {
#                 'if': {'$gte': ["$grades.score", 10]},
#                 'then': 1,
#                 'else': 0
#             }
#         }
#     }},
#     {'$group': {
#         '_id': '$_id',
#         'name': {'$first': '$name'},
#         'wc': {'$first': '$wc'},
#         'sufficient_scores': {'$sum': '$high_score'},
#     }},
#     {'$match': {'sufficient_scores': {'$gte': 2}}},
#     # output touch-ups
#     {'$project': {
#         '_id': 1,
#         'name': 1,
#         'sufficient_grades': '$sufficient_scores',
#     }},
#     # {'$group': {
#     #     '_id': None,
#     #     'count': {'$count': {}},
#     # }}
# ]):
#     print(doc)
