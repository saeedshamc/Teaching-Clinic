# ============================================================
# تمرین ۲ جلسه ۴۶ — TypeVar و Callable
# ============================================================

from typing import Callable, TypeVar

T = TypeVar("T")


def transform(items: list[T], fn: Callable[[T], T]) -> list[T]:
    return [fn(x) for x in items]


def pair_first(pair: tuple[T, T]) -> T:
    return pair[0]


if __name__ == "__main__":
    print(transform([1, 2, 3], lambda x: x * 10))
    print(transform(["a", "b"], lambda s: s.upper()))
    print(pair_first((100, 200)))
