# ============================================================
# تمرین جلسه ۹ — پاسخ نمونه (خانه)
# ============================================================

class Shape:
    def area(self):
        raise NotImplementedError


class Circle(Shape):
    def __init__(self, r):
        self.r = r

    def area(self):
        return 3.14159 * self.r ** 2


class Rectangle(Shape):
    def __init__(self, w, h):
        self.w, self.h = w, h

    def area(self):
        return self.w * self.h


shapes = [Circle(3), Rectangle(4, 5)]
for s in shapes:
    print(type(s).__name__, "→", round(s.area(), 2))
