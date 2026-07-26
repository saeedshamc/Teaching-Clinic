# ============================================================
# جلسه ۱۵ — generator ساده
# فایل: 01_generator.py
# ============================================================


def count_up(limit):
    current = 1
    while current <= limit:
        yield current
        current += 1


for value in count_up(5):
    print(value)
