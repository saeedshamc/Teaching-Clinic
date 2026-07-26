# ============================================================
# جلسه ۲۳ — Regex
# فایل: 01_regex.py
# ============================================================

import re

text = "ایمیل من: saeed@example.com و شماره ۱۲۳۴۵"
email = re.search(r"[\w.-]+@[\w.-]+", text)
print("ایمیل:", email.group() if email else "یافت نشد")

numbers = re.findall(r"\d+", text)
print("اعداد:", numbers)

cleaned = re.sub(r"\s+", " ", "سلام   دنیا")
print("متن تمیز:", cleaned)
