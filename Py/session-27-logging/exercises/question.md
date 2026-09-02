# تمرین‌های جلسه ۲۷ — Logging

## کلاسی (۳۰ دقیقه)

1. **سطوح:** با `basicConfig` سطح INFO تنظیم کنید؛ DEBUG, INFO, WARNING را امتحان کنید.
2. **Format:** timestamp + levelname + message در format بگذارید.
3. **Exception:** تابع `safe_sqrt(x)` بنویسید؛ برای x<0 با `logging.exception` خطا لاگ کنید.
4. **Logger نام‌دار:** logger با نام `math_ops` بسازید.

## خانه (۴۵ دقیقه)

5. **FileHandler:** همه INFO+ در `app.log` ذخیره شود.
6. **دو handler:** DEBUG→فایل، INFO→کنسول.
7. **RotatingFile:** با `RotatingFileHandler` log بچرخد (maxBytes=500).
8. **ماژول:** دو فایل فرضی `main` و `utils` — هر کدام `getLogger(__name__)`.

## نکات

- `logging.exception` فقط داخل `except`
- `%s` formatting بهتر از f-string برای performance
- `encoding="utf-8"` برای فایل فارسی
- handler تکراری = log duplicate — `handlers.clear()`

پاسخ نمونه: [exercise_01.py](./exercise_01.py) | [exercise_02.py](./exercise_02.py)
