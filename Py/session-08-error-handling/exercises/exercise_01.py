# ============================================================
# تمرین جلسه ۸ — پاسخ نمونه (کلاسی)
# ============================================================

def safe_int(raw):
    try:
        return int(raw)
    except ValueError:
        print("عدد نامعتبر")
        return None


def safe_div(a, b):
    try:
        return a / b
    except ZeroDivisionError:
        print("تقسیم بر صفر")
        return None


def validate_age(age):
    if age < 0 or age > 120:
        raise ValueError("سن باید ۰–۱۲۰ باشد")
    return age


print(safe_int("42"))
print(safe_int("abc"))
print(safe_div(10, 2))
print(safe_div(10, 0))
print(validate_age(25))
