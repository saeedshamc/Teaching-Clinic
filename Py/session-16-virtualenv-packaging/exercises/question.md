# تمرین‌های جلسه ۱۶ — محیط مجازی و بسته‌بندی

## کلاسی (۳۰ دقیقه)

### ۱. لیست requirements
لیستی از ۳ بسته برای پروژه «تحلیل داده» بنویسید (با محدودیت نسخه).

### ۲. پارس requirements
رشته چندخطی شبیه `requirements.txt` را بخوانید و نام بسته‌ها را استخراج کنید.

### ۳. فیلتر کامنت
خطوط `#` و خالی را از requirements حذف کنید.

---

## خانه (۴۵ دقیقه)

### ۴. تجزیه نسخه
تابع `parse_package_line("requests>=2.28")` → `{"name": "requests", "min": "2.28"}`

### ۵. venv عملی
در ترمینال: `python -m venv .venv`، فعال‌سازی، `pip install requests`، `pip freeze`

---

پاسخ نمونه: [exercise_01.py](./exercise_01.py) | [exercise_02.py](./exercise_02.py)
