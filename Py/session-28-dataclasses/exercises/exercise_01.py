# ============================================================
# تمرین جلسه ۲۸
# هدف: یک dataclass برای محصول بسازید.
# ============================================================

from dataclasses import dataclass


@dataclass
class Product:
    title: str
    price: int


item = Product("کتاب Python", 250000)
print(item.title, item.price)
