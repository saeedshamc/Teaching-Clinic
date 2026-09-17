# ============================================================
# جلسه ۱۴ — زمان‌سنجی و لاگ با decorator
# فایل: 03_timer_logging.py
# ============================================================

import time
from functools import wraps


def timer(func):
    """مدت اجرای تابع را اندازه می‌گیرد."""

    @wraps(func)
    def wrapper(*args, **kwargs):
        start = time.perf_counter()
        result = func(*args, **kwargs)
        elapsed = time.perf_counter() - start
        print(f"⏱ {func.__name__}: {elapsed:.4f} ثانیه")
        return result

    return wrapper


def log_args(func):
    """آرگومان‌های ورودی را قبل از اجرا چاپ می‌کند."""

    @wraps(func)
    def wrapper(*args, **kwargs):
        print(f"📥 {func.__name__}(args={args}, kwargs={kwargs})")
        return func(*args, **kwargs)

    return wrapper


@timer
@log_args
def slow_sum(n):
    """جمع اعداد ۰ تا n-1"""
    total = 0
    for i in range(n):
        total += i
    return total


@timer
def search_name(names, target):
    """جستجوی خطی نام در لیست"""
    for name in names:
        if name == target:
            return True
    return False


print("--- slow_sum ---")
print("نتیجه:", slow_sum(500_000))

print("\n--- search_name ---")
names = ["علی", "مریم", "رضا", "سارا"]
print("یافت شد:", search_name(names, "رضا"))
