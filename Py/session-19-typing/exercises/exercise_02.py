# ============================================================
# تمرین ۴–۵ جلسه ۱۹ — Type Hints (خانه)
# ============================================================

from collections.abc import Callable
from typing import TypedDict


class Student(TypedDict):
    name: str
    score: int


def is_passed(student: Student) -> bool:
    return student["score"] >= 10


def apply_all(numbers: list[int], func: Callable[[int], int]) -> list[int]:
    return [func(n) for n in numbers]


students: list[Student] = [
    {"name": "علی", "score": 18},
    {"name": "مریم", "score": 8},
]

for s in students:
    status = "قبول" if is_passed(s) else "مردود"
    print(f"{s['name']}: {status}")

print("مربع:", apply_all([1, 2, 3], lambda x: x ** 2))
