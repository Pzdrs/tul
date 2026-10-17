import redis

r = redis.Redis(host='localhost', port=6379, db=0)

leaderboard = {
    'Alfred': 888,
    'Bob': 889,
    'Cindy': 176,
    'David': 587,
    'Eve': 123,
    'Frank': 456,
    'George': 789,
    'Helen': 101,
    'Ivan': 102,
    'John': 149,
    'Karl': 100,
    'Linda': 46,
    'Mike': 86,
}

for name, score in leaderboard.items():
    r.zadd('leaderboard', {name: score})

redis_leaderboard = r.zrange('leaderboard', 0, -1, withscores=True, desc=True)
print('leaderboard', redis_leaderboard)
print()
print('top 3', r.zrange('leaderboard', 0, 2, withscores=True, desc=True))
print('lowest score', r.zrange('leaderboard', 0, 0, withscores=True))
print('number or players with less than 100pts', r.zcount('leaderboard', 0, 100))
print('players with more than 850pts', r.zrangebyscore('leaderboard', 850, '+inf'))
print('alfred\'s position', r.zrank('leaderboard', 'Alfred'))
r.zincrby('leaderboard', 12, 'Alfred')
print('alfred\'s position', r.zrank('leaderboard', 'Alfred'))
