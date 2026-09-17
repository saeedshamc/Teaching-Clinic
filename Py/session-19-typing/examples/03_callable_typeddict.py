# ============================================================
# جلسه ۱۹ — Callable و TypedDict
# فایل: 03_callable_typeddict.py
# ============================================================

from collections.abc import Callable
from typing import TypedDict


class Product(TypedDict):
    name: str
    price: int
    stock: int


def apply_discount(product: Product, percent: float) -> Product:
    """TypedDict — ساختار dict با کلیدهای مشخص"""
    discounted = product.copy()
    discounted["price"] = int(product["price"] * (1 - percent / 100))
    return discounted


def process_items(
    items: list[int],
    transform: Callable[[int], int],
) -> list[int]:
    """Callable[[ورودی], خروجی]"""
    return [transform(x) for x in items]


product: Product = {"name": "لپ‌تاپ", "price": 20_000_000, "stock": 5}
discounted = apply_discount(product, 10)
print("با تخفیف:", discounted)

numbers = [1, 2, 3, 4, 5]
doubled = process_items(numbers, lambda x: x * 2)
squared = process_items(numbers, lambda x: x ** 2)
print("دو برابر:", doubled)
print("مربع:", squared)
