# ============================================================
# جلسه ۱۹ — TypeVar و Generic ساده
# فایل: 04_generic_basics.py
# ============================================================

from typing import TypeVar

T = TypeVar("T")


def first(items: list[T]) -> T | None:
    """Generic — T می‌تواند هر نوعی باشد"""
    return items[0] if items else None


def pair(a: T, b: T) -> tuple[T, T]:
    return (a, b)


print("اول int:", first([1, 2, 3]))
print("اول str:", first(["a", "b"]))
print("اول خالی:", first([]))

print("pair int:", pair(1, 2))
print("pair str:", pair("سلام", "دنیا"))

# type hint روی متغیر
scores: list[int] = [18, 15, 12]
names: list[str] = ["علی", "مریم"]
mapping: dict[str, int] = dict(zip(names, scores))
print("mapping:", mapping)
