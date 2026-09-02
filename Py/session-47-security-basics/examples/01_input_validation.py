# ============================================================
# جلسه ۴۷ — اعتبارسنجی ورودی
# فایل: 01_input_validation.py
# ============================================================

import re


def is_safe_username(name: str) -> bool:
    return bool(re.fullmatch(r"[A-Za-z0-9_]{3,20}", name))


def is_valid_email(email: str) -> bool:
    return bool(re.fullmatch(r"[^@]+@[^@]+\.[^@]+", email)) and len(email) <= 100


def sanitize_display(text: str, max_len: int = 50) -> str:
    cleaned = text.strip().replace("\n", " ")
    return cleaned[:max_len]


tests = [
    ("saeed", is_safe_username("saeed")),
    ("a OR 1=1", is_safe_username("a OR 1=1")),
    ("user@example.com", is_valid_email("user@example.com")),
    ("bad@", is_valid_email("bad@")),
]

for label, ok in tests:
    print(f"{label!r:25} → {'✅' if ok else '❌'}")

print("sanitize:", sanitize_display("  hello\nworld  ", 10))
