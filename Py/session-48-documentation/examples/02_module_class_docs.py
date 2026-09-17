# ============================================================
# جلسه ۴۸ — module و class documentation
# فایل: 02_module_class_docs.py
# ============================================================
"""نمونه module docstring — توضیح کلی ماژول."""

from dataclasses import dataclass


@dataclass
class Product:
    """مدل محصول فروشگاه.

    Attributes:
        name: نام محصول
        price: قیمت به تومان (غیرمنفی)
    """

    name: str
    price: int

    def __post_init__(self) -> None:
        if self.price < 0:
            raise ValueError("قیمت منفی مجاز نیست")


class Inventory:
    """مدیریت موجودی محصولات."""

    def __init__(self) -> None:
        self._items: list[Product] = []

    def add(self, product: Product) -> None:
        """محصول را به موجودی اضافه می‌کند."""
        self._items.append(product)

    def count(self) -> int:
        """تعداد محصولات را برمی‌گرداند."""
        return len(self._items)


inv = Inventory()
inv.add(Product("ماوس", 350_000))
print("موجودی:", inv.count())
print("Product doc:", Product.__doc__[:50], "...")
