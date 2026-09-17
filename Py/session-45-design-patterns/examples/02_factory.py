# ============================================================
# جلسه ۴۵ — Factory
# فایل: 02_factory.py
# ============================================================

from dataclasses import dataclass


@dataclass
class Circle:
    radius: float

    def area(self) -> float:
        return 3.14159 * self.radius ** 2


@dataclass
class Square:
    side: float

    def area(self) -> float:
        return self.side ** 2


def shape_factory(kind: str, size: float):
    if kind == "circle":
        return Circle(size)
    if kind == "square":
        return Square(size)
    raise ValueError(f"نوع ناشناخته: {kind}")


shapes = [shape_factory("circle", 3), shape_factory("square", 4)]
for s in shapes:
    print(f"{type(s).__name__} area = {s.area():.2f}")
