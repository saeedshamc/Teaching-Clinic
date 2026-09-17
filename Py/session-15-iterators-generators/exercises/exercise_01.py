# ============================================================
# تمرین ۱–۳ جلسه ۱۵ — Iterator و Generator (کلاسی)
# ============================================================


def even_numbers(limit):
    number = 0
    while number <= limit:
        yield number
        number += 2


def char_stream(text):
    for char in text:
        yield char


def fibonacci(count):
    a, b = 0, 1
    for _ in range(count):
        yield a
        a, b = b, a + b


print("اعداد زوج:", list(even_numbers(10)))
print("کاراکترها:", list(char_stream("سلام")))
print("فیبوناچی:", list(fibonacci(8)))
