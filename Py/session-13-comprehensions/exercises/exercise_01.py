# ============================================================
# تمرین ۱–۳ جلسه ۱۳ — Comprehensionها (کلاسی)
# ============================================================

# --- ۱. اعداد فرد ---
numbers = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
odds = [n for n in numbers if n % 2 != 0]
print("اعداد فرد:", odds)

# --- ۲. dict comprehension: نام → طول ---
names = ["سعید", "مینا", "علی", "رضا"]
name_lengths = {name: len(name) for name in names}
print("طول نام‌ها:", name_lengths)

# --- ۳. flatten ماتریس ---
matrix = [[1, 2, 3], [4, 5, 6], [7, 8, 9]]
flat = [num for row in matrix for num in row]
print("لیست تخت:", flat)
