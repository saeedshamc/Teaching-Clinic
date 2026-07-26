# ============================================================
# جلسه ۳۸ — ABC
# فایل: 01_abc.py
# ============================================================

from abc import ABC, abstractmethod

class Shape(ABC):
    @abstractmethod
    def area(self) -> float:
        pass

class Square(Shape):
    def __init__(self, side: float):
        self.side = side
    def area(self) -> float:
        return self.side * self.side

class Circle(Shape):
    def __init__(self, radius: float):
        self.radius = radius
    def area(self) -> float:
        return 3.14 * self.radius * self.radius

print("مربع:", Square(4).area())
print("دایره:", Circle(3).area())
