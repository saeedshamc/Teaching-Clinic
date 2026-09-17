# ============================================================
# جلسه ۹ — وراثت
# فایل: 03_inheritance.py
# ============================================================

class Animal:
    def __init__(self, name):
        self.name = name

    def speak(self):
        return "..."

    def __str__(self):
        return self.name


class Dog(Animal):
    def speak(self):
        return f"{self.name} می‌گوید: هاپ!"


class Cat(Animal):
    def speak(self):
        return f"{self.name} می‌گوید: میو!"


class Person:
    def __init__(self, name):
        self.name = name


class Student(Person):
    def __init__(self, name, student_id, grade):
        super().__init__(name)
        self.student_id = student_id
        self.grade = grade

    def __str__(self):
        return f"Student({self.name}, {self.grade})"


animals = [Dog("رکس"), Cat("پشی"), Dog("بادی")]
for a in animals:
    print(a.speak())

s = Student("علی", "S001", 18)
print(s)
