# جلسه ۱۴: Decoratorها

## اهداف یادگیری (Learning Objectives)

پس از این جلسه می‌توانید:
- مفهوم decorator را درک کنید
- یک decorator ساده بسازید
- کاربردهای آن را در کدنویسی واقعی ببینید

---

## decorator چیست؟

Decorator یک تابع است که روی تابع دیگر اعمال می‌شود و رفتار آن را تغییر می‌دهد.

```python
def my_decorator(func):
    def wrapper():
        print("قبل از اجرا")
        func()
        print("بعد از اجرا")
    return wrapper

@my_decorator
def say_hello():
    print("سلام")

say_hello()
```

---

## فایل‌های این جلسه
- [examples/01_decorator.py](./examples/01_decorator.py)
- [exercises/exercise_01.py](./exercises/exercise_01.py)

## تمرین‌ها
- تمرین ساده: یک decorator که زمان اجرا را چاپ کند
- تمرین متوسط: یک decorator برای شمارش تعداد فراخوانی‌ها
