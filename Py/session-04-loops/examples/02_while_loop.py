# ============================================================
# جلسه ۴ — حلقه while
# فایل: 02_while_loop.py
# ============================================================

count = 0
while count < 3:
    print("تکرار", count)
    count += 1

print("---")

number = 1
while number <= 5:
    if number % 2 == 0:
        print(number, "زوج")
    else:
        print(number, "فرد")
    number += 1
