# ============================================================
# جلسه ۶ — scope و docstring
# فایل: 04_scope_docstring.py
# ============================================================

def outer_message():
    msg = "داخل تابع"
    print(msg)


outer_message()
# print(msg)  # NameError — msg محلی است


def counter():
    """شمارنده با nonlocal در تابع تو در تو (پیش‌نمایش)."""
    count = 0

    def increment():
        nonlocal count
        count += 1
        return count

    return increment


next_count = counter()
print(next_count())  # 1
print(next_count())  # 2


def bmi(weight_kg, height_m):
    """BMI = weight / height²"""
    if height_m <= 0:
        return None
    return weight_kg / (height_m ** 2)


print("BMI:", bmi(70, 1.75))
print(bmi.__doc__)
