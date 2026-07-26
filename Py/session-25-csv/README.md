# جلسه ۲۵: کار با CSV

## اهداف یادگیری (Learning Objectives)

پس از این جلسه می‌توانید:
- با فرمت CSV آشنا شوید
- فایل CSV را بخوانید و بنویسید
- از DictReader و DictWriter استفاده کنید

---

## CSV چیست؟

CSV فایلی متنی است که داده‌ها را در ستون‌های جدا‌شده با ویرگول نگه می‌دارد؛ برای جدول‌ها و اکسل بسیار رایج است.

```python
import csv
from pathlib import Path

path = Path("people.csv")
with path.open("w", encoding="utf-8", newline="") as file:
    writer = csv.DictWriter(file, fieldnames=["name", "age"])
    writer.writeheader()
    writer.writerow({"name": "سعید", "age": 30})
```

### توضیح
- `DictWriter` → ردیف‌ها را از دیکشنری می‌نویسد
- `writeheader` → عنوان ستون‌ها را می‌نویسد
- `newline=""` → در ویندوز از خطوط خالی اضافه جلوگیری می‌کند

---

## فایل‌های این جلسه
- [examples/01_csv.py](./examples/01_csv.py)
- [exercises/exercise_01.py](./exercises/exercise_01.py)

## تمرین‌ها
- تمرین ساده: یک ردیف CSV بنویسید
- تمرین متوسط: میانگین سن را از داده‌ها حساب کنید
