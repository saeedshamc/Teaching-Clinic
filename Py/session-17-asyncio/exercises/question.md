# تمرین‌های جلسه ۱۷ — AsyncIO

## کلاسی (۳۰ دقیقه)

### ۱. coroutine ساده
تابع async بنویسید که «سلام» چاپ کند، ۰.۲ ثانیه await کند، «دنیا» چاپ کند.

### ۲. gather
دو task با delay متفاوت — با gather هم‌زمان اجرا کنید.

### ۳. مقدار بازگشتی
coroutine که عدد برمی‌گرداند — نتیجه gather را چاپ کنید.

---

## خانه (۴۵ دقیقه)

### ۴. شبیه‌سازی download
۳ coroutine با delay 1، 2، 3 — gather — زمان کل را اندازه بگیرید.

### ۵. timeout
coroutine ۱۰ ثانیه‌ای — با wait_for و timeout=2 خطای timeout بگیرید.

---

پاسخ نمونه: [exercise_01.py](./exercise_01.py) | [exercise_02.py](./exercise_02.py)
