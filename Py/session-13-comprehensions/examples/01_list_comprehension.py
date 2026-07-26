# ============================================================
# جلسه ۱۳ — list comprehension
# فایل: 01_list_comprehension.py
# ============================================================

numbers = [1, 2, 3, 4, 5]
squares = [n * n for n in numbers]
print("مربع‌ها:", squares)

evens = [n for n in numbers if n % 2 == 0]
print("اعداد زوج:", evens)
