# ============================================================
# جلسه ۹ — کلاس پایه
# فایل: 01_class_basics.py
# ============================================================

class Dog:
    """نماینده یک سگ."""

    def __init__(self, name, age):
        self.name = name
        self.age = age

    def bark(self):
        print(f"{self.name} می‌گوید: هاپ!")

    def info(self):
        return f"سگ {self.name}، {self.age} ساله"


dog1 = Dog("بادی", 3)
dog2 = Dog("لوسی", 5)

dog1.bark()
print(dog2.info())
print("نام dog1:", dog1.name)
