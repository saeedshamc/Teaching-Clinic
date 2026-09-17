# ============================================================
# جلسه ۴۶ — TypeVar
# فایل: 03_typevar.py
# ============================================================

from typing import TypeVar

T = TypeVar("T")


def first(items: list[T]) -> T:
    return items[0]


def swap_pair(a: T, b: T) -> tuple[T, T]:
    return b, a


def last(items: list[T]) -> T:
    return items[-1]


nums = [10, 20, 30]
names = ["علی", "مریم"]

print("first nums:", first(nums))
print("first names:", first(names))
print("swap:", swap_pair(1, 2))
print("last names:", last(names))
