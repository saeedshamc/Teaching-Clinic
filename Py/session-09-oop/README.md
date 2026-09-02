# جلسه ۹: برنامه‌نویسی شیءگرا (OOP) (۱.۵ ساعت)

## 🎯 اهداف یادگیری

پس از این جلسه می‌توانید:
- مفهوم class و object را توضیح دهید
- `__init__` و attribute/method تعریف کنید
- `self` را درک کنید
- encapsulation ساده — public attribute و convention `_private`
- inheritance و `super()` بنویسید
- override متد در کلاس فرزند

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | چرا OOP؟ مدل واقعی |
| 10–30 | class، `__init__`، self |
| 30–45 | متدها و attribute |
| 45–60 | چند شیء، list of objects |
| 60–75 | inheritance |
| 75–85 | override و super |
| 85–90 | تکلیف |

---

## ۱. کلاس و شیء

```python
class Dog:
    def __init__(self, name, age):
        self.name = name
        self.age = age

    def bark(self):
        print(f"{self.name} می‌گوید: هاپ!")

    def info(self):
        return f"{self.name}, {self.age} سال"


dog1 = Dog("بادی", 3)
dog2 = Dog("لوسی", 5)
dog1.bark()
print(dog2.info())
```

**خط به خط:**
- `class Dog` — قالب
- `__init__` — سازنده — هنگام `Dog(...)` فراخوانی می‌شود
- `self` — اشاره به **همان شیء**
- `dog1`, `dog2` — دو instance مستقل

---

## ۲. attribute و method

```python
class BankAccount:
    def __init__(self, owner, balance=0):
        self.owner = owner
        self.balance = balance

    def deposit(self, amount):
        if amount > 0:
            self.balance += amount

    def withdraw(self, amount):
        if 0 < amount <= self.balance:
            self.balance -= amount
            return True
        return False

    def __str__(self):
        return f"{self.owner}: {self.balance:,} تومان"
```

---

## ۳. `__str__` و `__repr__`

```python
def __str__(self):
    return f"Book({self.title})"  # برای print(user)

def __repr__(self):
    return f"Book(title={self.title!r})"  # برای دیباگ
```

---

## ۴. وراثت (Inheritance)

```python
class Animal:
    def __init__(self, name):
        self.name = name

    def speak(self):
        return "..."

class Dog(Animal):
    def speak(self):
        return f"{self.name} می‌گوید: هاپ!"

class Cat(Animal):
    def speak(self):
        return f"{self.name} می‌گوید: میو!"
```

### ۴.۱. super()

```python
class Employee:
    def __init__(self, name, salary):
        self.name = name
        self.salary = salary

class Manager(Employee):
    def __init__(self, name, salary, team_size):
        super().__init__(name, salary)
        self.team_size = team_size
```

---

## ۵. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_class_basics.py](./examples/01_class_basics.py) | class و __init__ |
| [02_methods.py](./examples/02_methods.py) | متدها |
| [03_inheritance.py](./examples/03_inheritance.py) | وراثت |
| [04_bank_account.py](./examples/04_bank_account.py) | مثال واقعی |

---

## ۶. اشتباهات رایج

| اشتباه | راه‌حل |
|--------|--------|
| فراموش `self` در def | پارامتر اول self |
| `Dog.bark()` بدون instance | `dog.bark()` |
| attribute typo | IDE و type hint بعداً |
| deep hierarchy زود | ترجیح composition |

---

## ۷. تمرین کلاسی

1. class Book
2. class Rectangle — area/perimeter
3. Student extends Person
4. list of Student — میانگین نمره

**تکلیف:** [question.md](./exercises/question.md)

### ۷.۱. composition vs inheritance

گاهی به‌جای وراثت، **composition** بهتر است:

```python
class Engine:
    def start(self):
        print("موتور روشن")

class Car:
    def __init__(self):
        self.engine = Engine()
```

### ۷.۲. dataclass (پیش‌نمایش جلسه ۲۸)

```python
# @dataclass — boilerplate کمتر برای کلاس داده
```

---

## ۱۰. تکلیف خانه

1. **Vehicle hierarchy** — Car, Bike
2. **Library system** — Book + Member
3. **__eq__** برای مقایسه دو Book

---

## ۱۲. توضیح خط‌به‌خط مثال‌ها

### 📄 `01_class_basics.py`

```python
class Dog:
    def __init__(self, name, age):
        self.name = name
```

- `__init__` constructor — self همیشه اول
- `dog1.name` — attribute access

### 📄 `03_inheritance.py`

- `class Dog(Animal)` — Dog IS-A Animal
- override: متد هم‌نام در child جایگزین parent
- `super().__init__(name)` — فراخوانی constructor والد

### 📄 `04_bank_account.py`

- `_balance` — convention private
- `@property` — getter برای balance

---

## ۱۴. OOP vocabulary

| اصطلاح | معنی |
|--------|------|
| Class | قالب |
| Object / Instance | نمونه |
| Attribute | داده روی object |
| Method | تابع روی object |
| Inheritance | IS-A |
| Encapsulation | پنهان‌سازی داده |

---

## ۱۵. مطالعه بیشتر

- [docs — classes](https://docs.python.org/3/tutorial/classes.html)
- dunder methods: `__str__`, `__repr__`, `__eq__`
- تمرین: class Product با discount method

---

## ۱۷. تکلیف خانه (جزئیات)

** class Product:** name, price, `apply_discount(percent)`

** class Cart:** `add(product)`, `total()`

** polymorphism:** list of Shape — print area همه

---

## ۱۹. پرسش‌های کلاسی (Q&A)

**س: self چیست؟**  
ج: reference به instance فعلی.

**س: __init__ vs __new__؟**  
ج: __new__ ساخت object — __init__ initialize (مقدماتی: فقط __init__).

**س: inheritance vs composition؟**  
ج: IS-A vs HAS-A.

---

## ۲۰. خلاصه

- ✅ class blueprint — object instance
- ✅ `__init__(self, ...)`
- ✅ inheritance — `class Child(Parent)`
- ✅ override + super()

---

**جلسه قبل:** [۸ — خطا](../session-08-error-handling/) | **بعد:** [۱۰ — ماژول‌ها](../session-10-modules/)
