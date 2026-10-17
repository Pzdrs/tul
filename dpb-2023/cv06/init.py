from pymongo import MongoClient
from pymongo.collection import Collection

client = MongoClient('localhost', 27017)
db = client.cv04
collection: Collection = db.restaurants
