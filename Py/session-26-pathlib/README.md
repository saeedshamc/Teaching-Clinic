# جلسه ۲۶: Pathlib و مسیر فایل‌ها

## اهداف یادگیری (Learning Objectives)

پس از این جلسه می‌توانید:
- به‌جای رشته خام از Path استفاده کنید
- مسیر فایل و پوشه را بسازید و بررسی کنید
- عملیات رایج فایل‌سیستم را با pathlib انجام دهید

---

## pathlib چیست؟

ماژول pathlib راه مدرن و خوانا برای کار با مسیر فایل‌هاست و تفاوت ویندوز و لینوکس را کمتر دردسرساز می‌کند.

```python
from pathlib import Path

base = Path(__file__).parent
file_path = base / "notes.txt"
print(file_path.name)
print(file_path.exists())
```

### توضیح
- `Path` → شیء مسیر می‌سازد
- `/` → اتصال امن بخش‌های مسیر
- `exists()` → وجود فایل/پوشه را بررسی می‌کند

---

## فایل‌های این جلسه
- [examples/01_pathlib.py](./examples/01_pathlib.py)
- [exercises/exercise_01.py](./exercises/exercise_01.py)

## تمرین‌ها
- تمرین ساده: نام و پسوند یک مسیر را چاپ کنید
- تمرین متوسط: مسیر پوشه data را بسازید
