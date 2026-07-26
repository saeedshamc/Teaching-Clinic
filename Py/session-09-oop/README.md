# جلسه ۹: برنامه‌نویسی شیءگرا (OOP)

## اهداف یادگیری (Learning Objectives)

پس از این جلسه می‌توانید:
- مفهوم کلاس (Class) و شیء (Object) را درک کنید
- یک کلاس ساده تعریف کنید
- با وراثت (Inheritance) آشنا شوید

---

## کلاس و شیء چیست؟

کلاس یک قالب است و شیء نمونه‌ای از آن قالب است.

```python
class Dog:
    def __init__(self, name):
        self.name = name

    def bark(self):
        print(self.name, "می‌خزد")

my_dog = Dog("بکی")
my_dog.bark()
```

---

## وراثت

```python
class Animal:
    def __init__(self, name):
        self.name = name

    def speak(self):
        print("حیوان صدا می‌دهد")

class Dog(Animal):
    def speak(self):
        print("سگ می‌خزد")
```

---

## خلاصه جلسه

در این جلسه یاد گرفتید:
- کلاس و شیء چیست
- وراثت چه کاربردی دارد

---

## فایل‌های این جلسه
- [examples/01_class.py](./examples/01_class.py)
- [examples/02_inheritance.py](./examples/02_inheritance.py)
- [exercises/exercise_01.py](./exercises/exercise_01.py)

## تمرین‌ها

### تمرین ساده
کلاسی برای `Book` بسازید.

### تمرین متوسط
کلاسی برای `Student` و `Teacher` با وراثت بسازید.

---

[جلسه بعدی: ماژول‌ها و کتابخانه‌ها](../session-10-modules/)
