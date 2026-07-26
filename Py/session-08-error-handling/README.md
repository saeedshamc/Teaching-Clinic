# جلسه ۸: مدیریت خطا و دیباگ

## اهداف یادگیری (Learning Objectives)

پس از این جلسه می‌توانید:
- با `try` و `except` کار کنید
- خطاهای رایج را کشف و مدیریت کنید
- کد را برای رفع خطا دیباگ کنید

---

## خطا چیست؟

هرگاه کد چیزی را به‌صورت اشتباه انجام دهد، Python یک خطا (Exception) نشان می‌دهد. مثال: تقسیم بر صفر.

```python
try:
    result = 10 / 0
except ZeroDivisionError:
    print("تقسیم بر صفر مجاز نیست")
```

---

## استفاده از `finally`

```python
try:
    file = open("missing.txt")
except FileNotFoundError:
    print("فایل پیدا نشد")
finally:
    print("این بخش همیشه اجرا می‌شود")
```

---

## خلاصه جلسه

در این جلسه یاد گرفتید:
- خطا چیست
- چگونه از `try/except` استفاده کنید

---

## فایل‌های این جلسه
- [examples/01_try_except.py](./examples/01_try_except.py)
- [examples/02_finally.py](./examples/02_finally.py)
- [exercises/exercise_01.py](./exercises/exercise_01.py)

## تمرین‌ها

### تمرین ساده
برنامه‌ای بنویسید که از کاربر عددی بگیرد و با `try/except` خطا را مدیریت کند.

### تمرین متوسط
برنامه‌ای بنویسید که یک فایل ناموجود را بخواند و خطای آن را مدیریت کند.

---

[جلسه بعدی: برنامه‌نویسی شیءگرا](../session-09-oop/)
