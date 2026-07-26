# جلسه ۴۹: ساختار حرفه‌ای پروژه

## اهداف یادگیری (Learning Objectives)

پس از این جلسه می‌توانید:
- پوشه‌بندی استاندارد پروژه را بشناسید
- منطق، تست و مستند را جدا کنید
- پروژه قابل رشد طراحی کنید

---

## ساختار خوب چرا مهم است؟

ساختار منظم باعث می‌شود پیدا کردن فایل، تست و توسعه تیمی خیلی ساده‌تر شود.

```python
# نمونه ساختار
# myapp/
#   src/myapp/
#     __init__.py
#     main.py
#     services/
#   tests/
#   README.md
#   pyproject.toml

structure = ["src/myapp/main.py", "tests/test_main.py", "README.md"]
for item in structure:
    print(item)
```

### توضیح
- `src/` → کد اصلی
- `tests/` → تست‌ها
- `README.md` → راهنمای ورود

---

## فایل‌های این جلسه
- [examples/01_project_layout.py](./examples/01_project_layout.py)
- [exercises/exercise_01.py](./exercises/exercise_01.py)

## تمرین‌ها
- تمرین ساده: سه بخش اصلی پروژه را نام ببرید
- تمرین متوسط: ساختار یک CLI را طراحی کنید
