# ============================================================
# جلسه ۱۳ — comprehension تو در تو
# فایل: 02_nested_comprehension.py
# ============================================================
# وقتی منبع دوبعدی است (مثلاً ماتریس)، دو for پشت سر هم می‌نویسیم.
# ترتیب: for بیرونی → for درونی → عبارت
# ============================================================

matrix = [[1, 2, 3], [4, 5, 6], [7, 8, 9]]

# تبدیل ماتریس ۳×۳ به لیست تخت (۹ عنصر)
flat = [num for row in matrix for num in row]
print("لیست تخت:", flat)

# فقط اعداد زوج از ماتریس
evens_in_matrix = [num for row in matrix for num in row if num % 2 == 0]
print("زوج‌های داخل ماتریس:", evens_in_matrix)

# ساخت ماتریس جدید: هر عدد × ۱۰
scaled = [[num * 10 for num in row] for row in matrix]
print("ماتریس ×۱۰:", scaled)

# فیلتر رشته‌ها: فقط کلمات با بیش از ۳ حرف
words = ["سلام", "به", "دوره", "پایتون", "من"]
long_words = [w for w in words if len(w) > 3]
print("کلمات بلند:", long_words)
