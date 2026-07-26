# ============================================================
# جلسه ۱۴ — decorator ساده
# فایل: 01_decorator.py
# ============================================================
# decorator = تابعی که تابع دیگر را می‌گیرد و رفتارش را گسترش می‌دهد
# @my_decorator معادل است با: say_hello = my_decorator(say_hello)
# ============================================================


def my_decorator(func):
    """decorator که قبل و بعد از تابع پیام چاپ می‌کند."""

    def wrapper():
        print("⏳ قبل از اجرا")
        func()  # تابع اصلی اینجا اجرا می‌شود
        print("✅ بعد از اجرا")

    return wrapper  # wrapper جایگزار func می‌شود


@my_decorator
def say_hello():
    print("سلام!")


@my_decorator
def say_goodbye():
    print("خداحافظ!")


print("--- فراخوانی say_hello ---")
say_hello()

print("\n--- فراخوانی say_goodbye ---")
say_goodbye()

# --- نمایش مکانیزم بدون @ ---
print("\n--- اعمال دستی decorator ---")


def plain_greet():
    print("سلام دستی!")


plain_greet = my_decorator(plain_greet)
plain_greet()
