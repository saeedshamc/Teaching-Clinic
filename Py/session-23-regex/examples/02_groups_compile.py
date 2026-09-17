# ============================================================
# جلسه ۲۳ — Groups و compile
# فایل: 02_groups_compile.py
# ============================================================

import re

# --- named groups ---
text = "علی 25 ساله از تهران"
pattern = r"(?P<name>\w+)\s+(?P<age>\d+)\s+ساله"
match = re.search(pattern, text)
if match:
    print("نام:", match.group("name"))
    print("سن:", match.group("age"))
    print("dict:", match.groupdict())

# --- compile برای استفاده مکرر ---
PHONE = re.compile(r"09\d{9}")
EMAIL = re.compile(r"[\w.-]+@[\w.-]+\.\w+")

sample = "تماس: 09121234567 ایمیل: user@test.com"
phones = PHONE.findall(sample)
emails = EMAIL.findall(sample)
print("\nموبایل‌ها:", phones)
print("ایمیل‌ها:", emails)

# --- split با regex ---
parts = re.split(r"[,;]\s*", "سیب, پرتقال; موز")
print("split:", parts)
