# تمرین‌های جلسه ۸ — مدیریت خطا

## کلاسی (۳۰ دقیقه)

### ۱. int امن
ورودی → int با except ValueError.

### ۲. تقسیم امن
دو عدد → تقسیم با handle صفر.

### ۳. فایل
خواندن فایل ناموجود — پیام دوستانه.

### ۴. validate_age
۰–۱۲۰ — raise ValueError.

---

## خانه (۴۵ دقیقه)

### ۵. ماشین‌حساب robust
حلقه تا ورودی معتبر یا خروج.

### ۶. log خطا
except → append در errors.log.

### ۷. retry
۳ بار تلاش برای int(input).

---

## معیار ارزیابی

| معیار | امتیاز |
|-------|--------|
| except مناسب | ۳۰٪ |
| پیام کاربرپسند | ۲۵٪ |
| raise/validation | ۲۵٪ |
| finally/with | ۲۰٪ |

---

پاسخ نمونه: [exercise_01.py](./exercise_01.py) | [exercise_02.py](./exercise_02.py)
