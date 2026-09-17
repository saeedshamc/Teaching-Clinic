# ============================================================
# تمرین ۴–۵ جلسه ۱۴ — Decoratorها (خانه)
# ============================================================

import time
from functools import wraps


def timer(func):
    @wraps(func)
    def wrapper(*args, **kwargs):
        start = time.perf_counter()
        result = func(*args, **kwargs)
        elapsed = time.perf_counter() - start
        print(f"{func.__name__}: {elapsed:.4f} ثانیه")
        return result

    return wrapper


def repeat(times):
    def decorator(func):
        @wraps(func)
        def wrapper(*args, **kwargs):
            result = None
            for _ in range(times):
                result = func(*args, **kwargs)
            return result

        return wrapper

    return decorator


@timer
def sum_range(n):
    return sum(range(n))


@repeat(3)
def announce(msg):
    print(msg)


print("--- timer ---")
print("نتیجه:", sum_range(1_000_000))

print("\n--- repeat(3) ---")
announce("سلام!")
