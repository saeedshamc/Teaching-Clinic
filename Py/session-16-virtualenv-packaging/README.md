# جلسه ۱۶: محیط مجازی و بسته‌بندی

## اهداف یادگیری (Learning Objectives)

پس از این جلسه می‌توانید:
- یک محیط مجازی بسازید
- بسته‌های پروژه را مدیریت کنید
- با `pip` و `requirements.txt` کار کنید

---

## محیط مجازی چیست؟

محیط مجازی یک فضای جداگانه برای بسته‌های هر پروژه است.

```bash
python -m venv .venv
source .venv/bin/activate
```

## requirements.txt

```bash
pip install requests
pip freeze > requirements.txt
```

---

## فایل‌های این جلسه
- [examples/01_requirements_demo.py](./examples/01_requirements_demo.py)
- [exercises/exercise_01.py](./exercises/exercise_01.py)

## تمرین‌ها
- تمرین ساده: یک فایل requirements.txt بسازید
- تمرین متوسط: یک بسته ساده با `pip` نصب کنید
