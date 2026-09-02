# ============================================================
# جلسه ۲۸ — dataclass: field و پیش‌فرض
# ============================================================

from dataclasses import dataclass, field


@dataclass
class Product:
    name: str
    price: float
    tags: list[str] = field(default_factory=list)
    in_stock: bool = True


p1 = Product("ماوس", 350000)
p2 = Product("کیبورد", 1200000, tags=["الکترونیک"])

p1.tags.append("جانبی")
p2.tags.append("گیمینگ")

print(p1)
print(p2)
print("تگ‌های p1:", p1.tags)
print("تگ‌های p2:", p2.tags)
