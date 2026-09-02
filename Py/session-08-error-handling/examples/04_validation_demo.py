# ============================================================
# جلسه ۸ — اعتبارسنجی ورودی
# فایل: 04_validation_demo.py
# ============================================================

def read_positive_int(prompt, default=None):
    """عدد صحیح مثبت از کاربر — حالت دمو با default."""
    raw = str(default) if default is not None else "10"
    try:
        value = int(raw)
        if value <= 0:
            raise ValueError("باید مثبت باشد")
        return value
    except ValueError as e:
        print(f"ورودی نامعتبر: {e}")
        return None


def read_int_in_range(prompt, low, high, default=50):
    raw = str(default)
    try:
        n = int(raw)
        if not low <= n <= high:
            raise ValueError(f"باید بین {low} و {high} باشد")
        return n
    except ValueError as e:
        print(e)
        return None


print("مثبت:", read_positive_int("", default=5))
print("نمره:", read_int_in_range("", 0, 100, default=85))
