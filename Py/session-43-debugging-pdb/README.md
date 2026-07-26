# جلسه ۴۳: دیباگ با pdb

## اهداف یادگیری (Learning Objectives)

پس از این جلسه می‌توانید:
- ایده breakpoint را بفهمید
- اجرای برنامه را گام‌به‌گام بررسی کنید
- با چاپ هدفمند متغیرها دیباگ کنید

---

## pdb چیست؟

pdb دیباگر داخلی Python است. در عمل می‌توانید با breakpoint() برنامه را متوقف و متغیرها را بررسی کنید. اینجا نسخه آموزشی بدون توقف تعاملی می‌بینید.

```python
def divide(a, b):
    # breakpoint()  # در دیباگ واقعی فعال کنید
    if b == 0:
        print("DEBUG: b برابر صفر است")
        return None
    return a / b

print(divide(10, 2))
print(divide(10, 0))
```

### توضیح
- `breakpoint()` → ورود به دیباگر
- قبل از فرض، متغیرها را بررسی کنید
- لاگ و assert هم بخشی از دیباگ هستند

---

## فایل‌های این جلسه
- [examples/01_debugging.py](./examples/01_debugging.py)
- [exercises/exercise_01.py](./exercises/exercise_01.py)

## تمرین‌ها
- تمرین ساده: مقدار متغیر را قبل از خطا چاپ کنید
- تمرین متوسط: average را برای لیست خالی امن کنید
