# ============================================================
# جلسه ۱۴ — Decorator Factory و lru_cache
# فایل: 04_decorator_factory.py
# ============================================================
# decorator factory = تابعی که decorator برمی‌گرداند
# ساختار: factory(arg) → decorator → wrapper
# ============================================================

from functools import wraps, lru_cache


def repeat(times):
    """decorator factory — تابع را times بار اجرا می‌کند."""

    def decorator(func):
        @wraps(func)
        def wrapper(*args, **kwargs):
            result = None
            for _ in range(times):
                result = func(*args, **kwargs)
            return result

        return wrapper

    return decorator


def validate_positive(func):
    """همه آرگومان‌های عددی باید مثبت باشند."""

    @wraps(func)
    def wrapper(*args, **kwargs):
        for arg in args:
            if isinstance(arg, (int, float)) and arg < 0:
                raise ValueError(f"آرگومان منفی مجاز نیست: {arg}")
        return func(*args, **kwargs)

    return wrapper


@repeat(3)
def announce(message):
    print(message)


@validate_positive
def divide(a, b):
    return a / b


@lru_cache(maxsize=128)
def fibonacci(n):
    """فیبوناچی با cache — decorator داخلی Python"""
    if n < 2:
        return n
    return fibonacci(n - 1) + fibonacci(n - 2)


print("--- repeat(3) ---")
announce("سلام!")

print("\n--- validate_positive ---")
print("10 / 2 =", divide(10, 2))
try:
    divide(-5, 2)
except ValueError as e:
    print("خطا:", e)

print("\n--- lru_cache ---")
print("fib(30) =", fibonacci(30))
print("cache info:", fibonacci.cache_info())
