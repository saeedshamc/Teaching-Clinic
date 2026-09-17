# تمرین‌های جلسه ۱۴ — Decoratorها

## کلاسی (۳۰ دقیقه)

### ۱. decorator زمان
decorator `log_time` بسازید که **قبل** اجرا «شروع» و **بعد** «پایان» چاپ کند.

### ۲. شمارش فراخوانی
decorator `count_calls` بسازید که:
- هر بار فراخوانی، شماره را چاپ کند
- attribute `calls` روی wrapper داشته باشد

### ۳. لاگ نتیجه
تابع `multiply(a, b)` را با decoratorی decorate کنید که **مقدار بازگشتی** را چاپ کند.

---

## خانه (۴۵ دقیقه)

### ۴. decorator زمان‌سنجی
decorator `timer` با `time.perf_counter()` بنویسید و روی تابعی که حلقه دارد اعمال کنید.

### ۵. decorator factory
`@repeat(n)` بنویسید که تابع را n بار اجرا کند. روی تابع `print("سلام")` تست کنید.

---

پاسخ نمونه: [exercise_01.py](./exercise_01.py) | [exercise_02.py](./exercise_02.py)
