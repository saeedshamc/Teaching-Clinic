# ============================================================
# تمرین‌های جلسه ۱۴ — Decoratorها
# ============================================================
#
# تمرین ۱ (ساده):
#   decorator log_time بسازید: قبل «شروع»، بعد «پایان».
#
# تمرین ۲ (متوسط):
#   decorator count_calls — شمارش و چاپ هر فراخوانی.
#
# تمرین ۳ (پیشرفته):
#   تابع add را decorate کنید و مقدار بازگشتی را چاپ کنید.
#
# راهنما:
#   - از *args, **kwargs در wrapper استفاده کنید
#   - return func(...) را فراموش نکنید
#   - state روی wrapper: wrapper.calls = 0
#
# ============================================================

from functools import wraps


def log_time(func):
    @wraps(func)
    def wrapper(*args, **kwargs):
        print("شروع")
        result = func(*args, **kwargs)
        print("پایان")
        return result

    return wrapper


def count_calls(func):
    @wraps(func)
    def wrapper(*args, **kwargs):
        wrapper.calls += 1
        print("فراخوانی شماره:", wrapper.calls)
        return func(*args, **kwargs)

    wrapper.calls = 0
    return wrapper


def log_result(func):
    @wraps(func)
    def wrapper(*args, **kwargs):
        result = func(*args, **kwargs)
        print("نتیجه:", result)
        return result

    return wrapper


# --- تمرین ۱ ---
@log_time
def say_hello():
    print("سلام!")


# --- تمرین ۲ ---
@count_calls
def greet(name):
    print("سلام", name)


# --- تمرین ۳ ---
@log_result
def add(a, b):
    return a + b


print("=== تمرین ۱ ===")
say_hello()

print("\n=== تمرین ۲ ===")
greet("سعید")
greet("مینا")

print("\n=== تمرین ۳ ===")
add(5, 7)
