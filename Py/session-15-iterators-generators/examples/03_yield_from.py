# ============================================================
# جلسه ۱۵ — yield from و generator بی‌نهایت
# فایل: 03_yield_from.py
# ============================================================

def sub_range(start, end):
    """generator فرعی — اعداد start تا end"""
    for n in range(start, end):
        yield n


def combined():
    """yield from — واگذاری به generator دیگر"""
    yield from sub_range(1, 4)
    yield from ["الف", "ب", "ج"]
    yield from (x * 10 for x in range(1, 4))


def infinite_counter(start=0, step=1):
    """generator بی‌نهایت — با احتیاط استفاده کنید"""
    n = start
    while True:
        yield n
        n += step


print("--- combined ---")
print(list(combined()))

print("\n--- infinite_counter (۵ بار next) ---")
counter = infinite_counter(100, 5)
for _ in range(5):
    print(next(counter), end=" ")
print()

print("\n--- yield from range ---")
def all_in_one():
    yield from range(3)
    yield "پایان"

print(list(all_in_one()))
