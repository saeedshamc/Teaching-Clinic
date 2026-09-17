# ============================================================
# تمرین جلسه ۹ — پاسخ نمونه (کلاسی)
# ============================================================

class Book:
    def __init__(self, title, author, pages):
        self.title = title
        self.author = author
        self.pages = pages

    def info(self):
        return f"{self.title} — {self.author} ({self.pages} ص)"


class Person:
    def __init__(self, name):
        self.name = name


class Student(Person):
    def __init__(self, name, sid, grade):
        super().__init__(name)
        self.sid = sid
        self.grade = grade


students = [Student("علی", "1", 18), Student("مریم", "2", 19)]
avg = sum(s.grade for s in students) / len(students)
print(Book("Python", "سعید", 200).info())
print("میانگین:", avg)
