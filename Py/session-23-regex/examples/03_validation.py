# ============================================================
# جلسه ۲۳ — اعتبارسنجی با regex
# فایل: 03_validation.py
# ============================================================

import re

EMAIL_PATTERN = re.compile(r"^[\w.-]+@[\w.-]+\.\w{2,}$")
MOBILE_IR_PATTERN = re.compile(r"^09\d{9}$")
NATIONAL_CODE = re.compile(r"^\d{10}$")


def is_valid_email(email: str) -> bool:
    return bool(EMAIL_PATTERN.match(email))


def is_valid_mobile(mobile: str) -> bool:
    return bool(MOBILE_IR_PATTERN.match(mobile))


def is_valid_national_code(code: str) -> bool:
    return bool(NATIONAL_CODE.match(code))


test_cases = [
    ("saeed@example.com", is_valid_email),
    ("invalid-email", is_valid_email),
    ("09121234567", is_valid_mobile),
    ("9121234567", is_valid_mobile),
    ("1234567890", is_valid_national_code),
    ("123", is_valid_national_code),
]

print("نتایج اعتبارسنجی:")
for value, validator in test_cases:
    name = validator.__name__
    result = "✓" if validator(value) else "✗"
    print(f"  {result} {name}('{value}')")
