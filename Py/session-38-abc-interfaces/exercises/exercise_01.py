# ============================================================
# پاسخ نمونه — تمرین جلسه ۳۸ (ساده)
# ============================================================

from abc import ABC, abstractmethod
import math


class Shape(ABC):
    @abstractmethod
    def area(self) -> float:
        pass


class Square(Shape):
    def __init__(self, s: float):
        self.s = s

    def area(self) -> float:
        return self.s ** 2


class Circle(Shape):
    def __init__(self, r: float):
        self.r = r

    def area(self) -> float:
        return math.pi * self.r ** 2


shapes: list[Shape] = [Square(3), Circle(2)]
for s in shapes:
    print(type(s).__name__, "area =", f"{s.area():.2f}")

try:
    Shape()
except TypeError as e:
    print("ABC:", e)
