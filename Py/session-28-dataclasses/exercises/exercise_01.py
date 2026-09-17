# ============================================================
# پاسخ نمونه — تمرین جلسه ۲۸ (ساده)
# ============================================================

import math
from dataclasses import dataclass, field


@dataclass
class Book:
    title: str
    author: str
    pages: int


@dataclass
class Product:
    name: str
    price: float
    tags: list[str] = field(default_factory=list)


@dataclass(frozen=True)
class Point:
    x: float
    y: float

    def distance(self) -> float:
        return math.hypot(self.x, self.y)


book = Book("پایتون برای همه", "سعید", 320)
product = Product("ماوس", 350000, tags=["الکترونیک"])
p = Point(3, 4)

print(book)
print(product)
print("فاصله:", p.distance())

u1 = Book("A", "B", 100)
u2 = Book("A", "B", 100)
print("کتاب‌ها برابر؟", u1 == u2)
