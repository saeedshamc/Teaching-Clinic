# ============================================================
# تمرین جلسه ۱۵
# هدف: یک generator برای اعداد زوج بسازید.
# ============================================================


def even_numbers(limit):
    number = 0
    while number <= limit:
        yield number
        number += 2


for number in even_numbers(10):
    print(number)
