# جلسه ۱۹: Type Hints

## اهداف یادگیری (Learning Objectives)

پس از این جلسه می‌توانید:
- با Type Hints در Python آشنا شوید
- نوع پارامترها و مقدار بازگشتی را مشخص کنید
- خوانایی و نگهداری کد را با تایپ بهتر کنید
- از تایپ‌های ترکیبی مثل `list[str]` استفاده کنید

---

## Type Hint چیست؟

Type Hint روشی برای مشخص کردن نوع داده متغیرها و توابع است. Python همچنان dynamic است، اما این نشانه‌ها به خواننده کد و ابزارهایی مثل VS Code کمک می‌کنند.

```python
def greet(name: str) -> str:
    return f"سلام {name}"


message: str = greet("سعید")
print(message)
```

### توضیح
- `name: str` → پارامتر باید رشته باشد
- `-> str` → تابع یک رشته برمی‌گرداند
- `message: str` → متغیر از نوع رشته است

---

## تایپ‌های پرکاربرد

```python
def average(numbers: list[float]) -> float:
    return sum(numbers) / len(numbers)


def find_age(people: dict[str, int], name: str) -> int:
    return people[name]


ages = {"سعید": 30, "مینا": 25}
print(average([10.0, 20.0, 30.0]))
print(find_age(ages, "مینا"))
```

### توضیح
- `list[float]` → لیستی از اعداد اعشاری
- `dict[str, int]` → دیکشنری با کلید رشته و مقدار عدد صحیح

---

## فایل‌های این جلسه
- [examples/01_type_hints.py](./examples/01_type_hints.py)
- [exercises/exercise_01.py](./exercises/exercise_01.py)

## تمرین‌ها
- تمرین ساده: برای یک تابع جمع، type hint بنویسید
- تمرین متوسط: تابعی بنویسید که لیست رشته‌ها را بگیرد و طول کل را برگرداند
