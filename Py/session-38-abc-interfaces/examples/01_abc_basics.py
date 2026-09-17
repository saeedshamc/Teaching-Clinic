# ============================================================
# جلسه ۳۸ — ABC: Shape
# ============================================================

from abc import ABC, abstractmethod
import math


class Shape(ABC):
    @abstractmethod
    def area(self) -> float:
        pass

    @abstractmethod
    def perimeter(self) -> float:
        pass

    def describe(self) -> str:
        return f"{type(self).__name__}: area={self.area():.2f}"


class Square(Shape):
    def __init__(self, side: float):
        self.side = side

    def area(self) -> float:
        return self.side ** 2

    def perimeter(self) -> float:
        return 4 * self.side


class Circle(Shape):
    def __init__(self, radius: float):
        self.radius = radius

    def area(self) -> float:
        return math.pi * self.radius ** 2

    def perimeter(self) -> float:
        return 2 * math.pi * self.radius


shapes: list[Shape] = [Square(4), Circle(3)]
for s in shapes:
    print(s.describe())

try:
    Shape()
except TypeError as e:
    print("\nTypeError (انتظار):", e)
