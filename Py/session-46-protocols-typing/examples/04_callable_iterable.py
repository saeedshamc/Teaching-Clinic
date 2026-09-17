# ============================================================
# جلسه ۴۶ — Callable و Iterable
# فایل: 04_callable_iterable.py
# ============================================================

from typing import Callable, Iterable


def apply_to_all(items: Iterable[int], fn: Callable[[int], int]) -> list[int]:
    return [fn(x) for x in items]


def double(x: int) -> int:
    return x * 2


def square(x: int) -> int:
    return x * x


data = range(1, 6)
print("double:", apply_to_all(data, double))
print("square:", apply_to_all(data, square))


def filter_positive(items: Iterable[int]) -> list[int]:
    return [x for x in items if x > 0]


print("filter:", filter_positive([-1, 2, -3, 4]))
