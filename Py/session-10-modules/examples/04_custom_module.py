# ============================================================
# جلسه ۱۰ — ماژول محلی و __main__
# فایل: 04_custom_module.py
# ============================================================
# این فایل توابع helper را import می‌کند.
# برای دمو، توابع اینجا inline تعریف شده‌اند (معادل my_utils.py)

def celsius_to_f(c):
    return c * 9 / 5 + 32


def format_price(amount):
    return f"{amount:,.0f} تومان"


def demo():
    print("°C → °F:", celsius_to_f(25))
    print(format_price(1_250_000))


if __name__ == "__main__":
    print("اجرای مستقیم 04_custom_module.py")
    demo()
else:
    print("import شده به‌عنوان ماژول")
