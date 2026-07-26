# جلسه ۴۰: بسته‌بندی با pyproject.toml

## اهداف یادگیری (Learning Objectives)

پس از این جلسه می‌توانید:
- ایده بسته‌بندی پروژه را درک کنید
- نقش pyproject.toml را بشناسید
- متادیتای بسته را تعریف کنید

---

## pyproject.toml چیست؟

فایل استاندارد مدرن برای تعریف اطلاعات پروژه، وابستگی‌ها و تنظیمات ساخت بسته Python است.

```python
# نمونه محتوای pyproject.toml
# [project]
# name = "teaching-clinic"
# version = "0.1.0"
# requires-python = ">=3.10"
# dependencies = ["requests"]

print("پروژه آماده بسته‌بندی است")
```

### توضیح
- `name` و `version` → هویت بسته
- `requires-python` → نسخه مورد نیاز
- `dependencies` → وابستگی‌ها

---

## نمونه pyproject.toml

```python
[project]
name = "teaching-clinic"
version = "0.1.0"
requires-python = ">=3.10"
dependencies = [
  "requests",
]
```

---

## فایل‌های این جلسه
- [examples/01_packaging_info.py](./examples/01_packaging_info.py)
- [exercises/exercise_01.py](./exercises/exercise_01.py)

## تمرین‌ها
- تمرین ساده: name و version را چاپ کنید
- تمرین متوسط: متادیتای بسته خودتان را بسازید
