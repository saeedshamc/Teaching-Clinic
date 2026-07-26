# جلسه ۴۵: الگوهای طراحی ساده

## اهداف یادگیری (Learning Objectives)

پس از این جلسه می‌توانید:
- ایده design pattern را بشناسید
- الگوی Factory و Singleton ساده را پیاده کنید
- برای مسئله مناسب، الگوی درست انتخاب کنید

---

## Design Pattern چیست؟

الگوهای طراحی راه‌حل‌های تکرارپذیر برای مسائل رایج طراحی نرم‌افزار هستند.

```python
class Logger:
    _instance = None
    def __new__(cls):
        if cls._instance is None:
            cls._instance = super().__new__(cls)
        return cls._instance

a = Logger(); b = Logger()
print(a is b)
```

### توضیح
- `Singleton` → فقط یک نمونه
- `Factory` → ساخت شیء را متمرکز می‌کند
- الگو را فقط وقتی نیاز دارید استفاده کنید

---

## فایل‌های این جلسه
- [examples/01_patterns.py](./examples/01_patterns.py)
- [exercises/exercise_01.py](./exercises/exercise_01.py)

## تمرین‌ها
- تمرین ساده: Singleton لاگر بسازید
- تمرین متوسط: factory نقش کاربری بنویسید
