# ============================================================
# جلسه ۱۳ — تبدیل شرطی با if-else در comprehension
# فایل: 03_conditional_transform.py
# ============================================================
# تفاوت if بعد از for (فیلتر) و if-else قبل از for (تبدیل)
# ============================================================

numbers = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

# --- فیلتر: if بعد از for — عنصر حذف می‌شود ---
# فقط اعداد بزرگ‌تر از ۵
big = [n for n in numbers if n > 5]
print("بزرگ‌تر از ۵:", big)

# --- تبدیل: if-else قبل از for — همه عناصر می‌مانند ---
# برچسب «زوج» یا «فرد» برای هر عدد
parity_labels = ["زوج" if n % 2 == 0 else "فرد" for n in numbers]
print("برچسب زوج/فرد:", parity_labels)

# --- ترکیب: فیلتر + تبدیل ---
# مربع اعداد زوج (فیلتر با if، سپس تبدیل با عبارت)
even_squares = [n * n for n in numbers if n % 2 == 0]
print("مربع اعداد زوج:", even_squares)

# --- dict comprehension با شرط ---
students = [
    {"name": "علی", "score": 18},
    {"name": "مریم", "score": 9},
    {"name": "رضا", "score": 15},
    {"name": "سارا", "score": 7},
]

# فقط قبول‌شده‌ها: نام → نمره
passed = {s["name"]: s["score"] for s in students if s["score"] >= 10}
print("قبول‌شده‌ها:", passed)

# همه با وضعیت قبول/مردود
status = {
    s["name"]: "قبول" if s["score"] >= 10 else "مردود"
    for s in students
}
print("وضعیت:", status)
