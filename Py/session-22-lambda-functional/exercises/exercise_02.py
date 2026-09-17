# ============================================================
# تمرین ۴–۵ جلسه ۲۲ — Lambda (خانه)
# ============================================================

from functools import reduce

students = [
    {"name": "علی", "score": 18},
    {"name": "مریم", "score": 15},
    {"name": "رضا", "score": 20},
]

by_score = sorted(students, key=lambda s: s["score"], reverse=True)
print("مرتب‌شده:")
for s in by_score:
    print(f"  {s['name']}: {s['score']}")

numbers = [1, 2, 3, 4, 5]
product = reduce(lambda acc, n: acc * n, numbers)
print("حاصلضرب:", product)
