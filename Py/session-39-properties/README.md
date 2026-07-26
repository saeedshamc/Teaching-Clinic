# جلسه ۳۹: Property و Encapsulation

## اهداف یادگیری (Learning Objectives)

پس از این جلسه می‌توانید:
- با @property دسترسی کنترل‌شده بسازید
- getter و setter تعریف کنید
- اعتبارسنجی هنگام مقداردهی انجام دهید

---

## property چیست؟

property اجازه می‌دهد مثل ویژگی ساده به داده دسترسی داشته باشید، اما در پشت صحنه منطق و اعتبارسنجی اجرا شود.

```python
class User:
    def __init__(self, age: int):
        self._age = age

    @property
    def age(self) -> int:
        return self._age

    @age.setter
    def age(self, value: int) -> None:
        if value < 0:
            raise ValueError("سن منفی مجاز نیست")
        self._age = value

user = User(30)
user.age = 31
print(user.age)
```

### توضیح
- `@property` → خواندن کنترل‌شده
- `@x.setter` → نوشتن کنترل‌شده
- می‌توانید اعتبارسنجی بگذارید

---

## فایل‌های این جلسه
- [examples/01_property.py](./examples/01_property.py)
- [exercises/exercise_01.py](./exercises/exercise_01.py)

## تمرین‌ها
- تمرین ساده: getter برای age بنویسید
- تمرین متوسط: setter قیمت غیرمنفی بسازید
