# ============================================================
# جلسه ۹ — وراثت
# فایل: 02_inheritance.py
# ============================================================

class Animal:
    def __init__(self, name):
        self.name = name

    def speak(self):
        print(self.name, "صدا می‌دهد")


class Dog(Animal):
    def speak(self):
        print(self.name, "می‌خزد")


animal = Animal("جانور")
animal.speak()

dog = Dog("بکی")
dog.speak()
