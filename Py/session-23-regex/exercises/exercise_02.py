# ============================================================
# تمرین ۴–۵ جلسه ۲۳ — Regex (خانه)
# ============================================================

import re

text = "تماس 09121234567 یا 09351234567 — ایمیل test@mail.com"

# --- ۴. موبایل ---
phones = re.findall(r"09\d{9}", text)
print("موبایل‌ها:", phones)

# --- ۵. اعتبارسنجی ایمیل ---
EMAIL = re.compile(r"^[\w.-]+@[\w.-]+\.\w{2,}$")


def is_valid_email(email: str) -> bool:
    return bool(EMAIL.match(email))


for addr in ["test@mail.com", "bad@", "user@domain.co"]:
    print(f"  {addr}: {'✓' if is_valid_email(addr) else '✗'}")
