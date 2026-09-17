# تمرین‌های جلسه ۱۱ — کتابخانه بیرونی

## کلاسی (۳۰ دقیقه)

### ۱. نصب
`pip install requests` در venv.

### ۲. GET
httpbin.org/get — status و headers.

### ۳. params
GET با `?name=python`.

### ۴. requirements
فایل requirements.txt بسازید.

---

## خانه (۴۵ دقیقه)

### ۵. status codes
httpbin.org/status/200 و /404 — handle.

### ۶. mini client
تابع `fetch_json(url)` → dict یا None.

### ۷. offline
MockResponse برای تست بدون شبکه.

---

## معیار ارزیابی

| معیار | امتیاز |
|-------|--------|
| pip/venv | ۲۰٪ |
| requests صحیح | ۳۰٪ |
| error handling | ۳۰٪ |
| requirements.txt | ۲۰٪ |

---

پاسخ نمونه: [exercise_01.py](./exercise_01.py) | [exercise_02.py](./exercise_02.py)
