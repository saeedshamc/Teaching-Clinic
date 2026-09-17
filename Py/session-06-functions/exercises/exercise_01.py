# ============================================================
# تمرین جلسه ۶ — پاسخ نمونه (کلاسی)
# ============================================================

def greet(name):
    print(f"سلام {name}!")


def average(a, b, c):
    return (a + b + c) / 3


def is_even(n):
    return n % 2 == 0


def rectangle(w, h):
    return w * h, 2 * (w + h)


greet("سعید")
print("میانگین:", average(10, 20, 30))
print("زوج:", is_even(4))
print("مستطیل:", rectangle(5, 3))
