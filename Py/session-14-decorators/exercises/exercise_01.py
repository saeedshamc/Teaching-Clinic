# ============================================================
# تمرین ۱–۳ جلسه ۱۴ — Decoratorها (کلاسی)
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
        print(f"فراخوانی شماره {wrapper.calls}")
        return func(*args, **kwargs)

    wrapper.calls = 0
    return wrapper


def log_result(func):
    @wraps(func)
    def wrapper(*args, **kwargs):
        result = func(*args, **kwargs)
        print(f"نتیجه: {result}")
        return result

    return wrapper


@log_time
def say_hello():
    print("سلام!")


@count_calls
def greet(name):
    print(f"سلام {name}")


@log_result
def multiply(a, b):
    return a * b


say_hello()
greet("سعید")
greet("مینا")
print(f"تعداد فراخوانی greet: {greet.calls}")
multiply(4, 5)
