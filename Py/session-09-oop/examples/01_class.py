# ============================================================
# جلسه ۹ — کلاس و شیء
# فایل: 01_class.py
# ============================================================

class Dog:
    def __init__(self, name):
        self.name = name

    def bark(self):
        print(self.name, "می‌خزد")


my_dog = Dog("بکی")
my_dog.bark()
