# ============================================================
# تمرین‌های جلسه ۱۵ — Iterator و Generator
# ============================================================
#
# تمرین ۱ (ساده):
#   even_numbers(limit) — اعداد زوج 0 تا limit.
#
# تمرین ۲ (متوسط):
#   char_stream(text) — هر کاراکتر را yield کند.
#
# تمرین ۳ (پیشرفته):
#   generator expression: مربع اعداد زوج 0..10 → list.
#
# راهنما:
#   - yield به‌جای return
#   - (n*n for n in range(11) if n % 2 == 0)
#
# ============================================================


def even_numbers(limit):
    number = 0
    while number <= limit:
        yield number
        number += 2


def char_stream(text):
    for char in text:
        yield char


print("=== تمرین ۱ ===")
for number in even_numbers(10):
    print(number, end=" ")
print()

print("\n=== تمرین ۲ ===")
for char in char_stream("Python"):
    print(char, end="-")
print()

print("\n=== تمرین ۳ ===")
even_squares = [n * n for n in range(11) if n % 2 == 0]
# یا: list(n * n for n in range(11) if n % 2 == 0)
print("مربع اعداد زوج:", even_squares)
