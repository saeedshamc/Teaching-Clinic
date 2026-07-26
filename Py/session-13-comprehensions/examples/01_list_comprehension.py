# ============================================================
# جلسه ۱۳ — list، dict و set comprehension
# فایل: 01_list_comprehension.py
# ============================================================
# این فایل سه نوع comprehension را نشان می‌دهد:
# 1) list comprehension — ساخت لیست جدید
# 2) dict comprehension — ساخت دیکشنری
# 3) set comprehension — ساخت مجموعه بدون تکرار
# ============================================================

numbers = [1, 2, 3, 4, 5]

# --- list comprehension: مربع هر عدد ---
# [عبارت for متغیر in منبع]
squares = [n * n for n in numbers]
print("مربع‌ها:", squares)

# --- list comprehension با شرط if: فقط اعداد زوج ---
# شرط after for فیلتر می‌کند — عنصر فقط اگر شرط True باشد وارد لیست می‌شود
evens = [n for n in numbers if n % 2 == 0]
print("اعداد زوج:", evens)

# --- dict comprehension: نام → طول نام ---
names = ["سعید", "مینا", "علی"]
name_lengths = {name: len(name) for name in names}
print("طول نام‌ها:", name_lengths)

# --- set comprehension: طول‌های یکتا (بدون تکرار) ---
words = ["سلام", "دنیا", "سلام", "python"]
unique_lengths = {len(word) for word in words}
print("طول‌های یکتا:", unique_lengths)

# --- مقایسه با حلقه for (همان منطق، بیشتر خط) ---
doubled_loop = []
for n in numbers:
    doubled_loop.append(n * 2)
doubled_comp = [n * 2 for n in numbers]
print("دو برابر (حلقه):", doubled_loop)
print("دو برابر (comprehension):", doubled_comp)
