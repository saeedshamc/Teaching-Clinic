# ============================================================
# تمرین جلسه ۱۴
# هدف: یک decorator بسازید که تعداد فراخوانی تابع را بشمارد.
# ============================================================


def count_calls(func):
    def wrapper(*args, **kwargs):
        wrapper.calls += 1
        print("فراخوانی شماره:", wrapper.calls)
        return func(*args, **kwargs)
    wrapper.calls = 0
    return wrapper


@count_calls
def greet(name):
    print("سلام", name)


greet("سعید")
greet("مینا")
