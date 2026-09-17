# ============================================================
# جلسه ۹ — متدها
# فایل: 02_methods.py
# ============================================================

class Rectangle:
    def __init__(self, width, height):
        self.width = width
        self.height = height

    def area(self):
        return self.width * self.height

    def perimeter(self):
        return 2 * (self.width + self.height)

    def scale(self, factor):
        self.width *= factor
        self.height *= factor

    def __str__(self):
        return f"Rectangle({self.width}x{self.height})"


r = Rectangle(4, 5)
print(r, "مساحت:", r.area())
r.scale(2)
print("بعد از scale:", r)
