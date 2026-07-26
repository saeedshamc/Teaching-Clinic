# ============================================================
# تمرین‌های جلسه ۱۳ — Comprehensionها
# ============================================================
#
# تمرین ۱ (ساده):
#   با list comprehension از numbers فقط اعداد فرد را بسازید.
#
# تمرین ۲ (متوسط):
#   با dict comprehension دیکشنری name → طول نام بسازید.
#
# تمرین ۳ (پیشرفته):
#   از nums مربع اعداد بزرگ‌تر از ۱۵ را استخراج کنید.
#
# راهنما:
#   - list: [x for x in source if condition]
#   - dict: {key: value for x in source}
#   - شرط زوج/فرد: n % 2 == 0 (زوج) یا n % 2 != 0 (فرد)
#
# ============================================================

numbers = [1, 2, 3, 4, 5, 6, 7, 8, 9]
names = ["سعید", "مینا", "علی"]
nums = [10, 15, 20, 25, 30]

# --- راه‌حل تمرین ۱ ---
odd_numbers = [n for n in numbers if n % 2 != 0]
print("تمرین ۱ — اعداد فرد:", odd_numbers)

# --- راه‌حل تمرین ۲ ---
name_lengths = {name: len(name) for name in names}
print("تمرین ۲ — طول نام‌ها:", name_lengths)

# --- راه‌حل تمرین ۳ ---
big_squares = [n * n for n in nums if n > 15]
print("تمرین ۳ — مربع اعداد > ۱۵:", big_squares)
