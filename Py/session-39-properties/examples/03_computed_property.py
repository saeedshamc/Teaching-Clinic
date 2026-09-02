# ============================================================
# جلسه ۳۹ — property محاسباتی
# فایل: 03_computed_property.py
# ============================================================


class Person:
    def __init__(self, first_name: str, last_name: str):
        self.first_name = first_name
        self.last_name = last_name

    @property
    def full_name(self) -> str:
        return f"{self.first_name} {self.last_name}"


class Rectangle:
    def __init__(self, width: float, height: float):
        self.width = width
        self.height = height

    @property
    def area(self) -> float:
        return self.width * self.height

    @property
    def perimeter(self) -> float:
        return 2 * (self.width + self.height)


person = Person("سعید", "احمدی")
print("نام کامل:", person.full_name)

rect = Rectangle(4, 5)
print(f"مستطیل {rect.width}×{rect.height} → مساحت {rect.area}، محیط {rect.perimeter}")
