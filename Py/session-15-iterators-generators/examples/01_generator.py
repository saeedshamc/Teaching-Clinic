# ============================================================
# جلسه ۱۵ — generator با yield
# فایل: 01_generator.py
# ============================================================
# yield به‌جای return: هر بار یک مقدار برمی‌گرداند و اجرا متوقف می‌شود
# دفعه بعد از همان نقطه ادامه می‌یابد — state (مثل current) حفظ می‌شود
# ============================================================


def count_up(limit):
    """از ۱ تا limit یکی‌یکی yield می‌کند."""
    current = 1
    while current <= limit:
        yield current  # مقدار را می‌دهد، تابع «مکث» می‌کند
        current += 1   # دفعه بعد از اینجا ادامه


def even_numbers(limit):
    """اعداد زوج از ۰ تا limit."""
    number = 0
    while number <= limit:
        yield number
        number += 2


def fibonacci(count):
    """count عدد اول فیبوناچی."""
    a, b = 0, 1
    for _ in range(count):
        yield a
        a, b = b, a + b


print("--- count_up(5) ---")
for value in count_up(5):
    print(value, end=" ")
print()

print("\n--- even_numbers(10) ---")
for number in even_numbers(10):
    print(number, end=" ")
print()

print("\n--- fibonacci(8) ---")
for num in fibonacci(8):
    print(num, end=" ")
print()

# generator object — lazy؛ تا for/next اجرا نمی‌شود
gen = count_up(3)
print("\n--- next دستی ---")
print(next(gen))
print(next(gen))
print(next(gen))
