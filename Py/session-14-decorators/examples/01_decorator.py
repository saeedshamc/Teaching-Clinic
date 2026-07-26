# ============================================================
# جلسه ۱۴ — decorator ساده
# فایل: 01_decorator.py
# ============================================================


def my_decorator(func):
    def wrapper():
        print("قبل از اجرا")
        func()
        print("بعد از اجرا")
    return wrapper


@my_decorator
def say_hello():
    print("سلام")


say_hello()
