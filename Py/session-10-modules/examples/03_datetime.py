# ============================================================
# جلسه ۱۰ — datetime
# فایل: 03_datetime.py
# ============================================================
from datetime import datetime, timedelta, date

now = datetime.now()
print("الان:", now)
print("فرمت:", now.strftime("%Y/%m/%d %H:%M"))

today = date.today()
print("تاریخ:", today)

birth = date(2000, 5, 15)
age_days = (today - birth).days
print(f"حدود {age_days // 365} سال")

deadline = now + timedelta(days=7)
print("یک هفته بعد:", deadline.strftime("%Y-%m-%d"))
