# ============================================================
# جلسه ۱۴ — decorator پیشرفته: args، return، wraps
# فایل: 02_decorator_advanced.py
# ============================================================
# وقتی تابع اصلی پارامتر یا مقدار بازگشتی دارد:
# - wrapper باید *args, **kwargs بپذیرد
# - return func(...) مقدار بازگشتی را حفظ می‌کند
# - @wraps(func) نام و docstring تابع اصلی را نگه می‌دارد
# ============================================================

from functools import wraps


def count_calls(func):
    """هر بار فراخوانی را می‌شمارد و چاپ می‌کند."""

    @wraps(func)
    def wrapper(*args, **kwargs):
        wrapper.calls += 1
        print(f"📞 فراخوانی شماره {wrapper.calls} از {func.__name__}")
        result = func(*args, **kwargs)
        return result

    wrapper.calls = 0
    return wrapper


def log_result(func):
    """مقدار بازگشتی تابع را چاپ می‌کند."""

    @wraps(func)
    def wrapper(*args, **kwargs):
        result = func(*args, **kwargs)
        print(f"🔢 نتیجه {func.__name__}: {result}")
        return result

    return wrapper


@count_calls
@log_result
def add(a, b):
    """جمع دو عدد."""
    return a + b


@count_calls
def greet(name, greeting="سلام"):
    print(f"{greeting}، {name}!")


print(add(2, 3))
print(add(10, 20))
print(f"نام تابع add: {add.__name__}")  # با wraps → "add" نه "wrapper"

greet("سعید")
greet("مینا", greeting="درود")
