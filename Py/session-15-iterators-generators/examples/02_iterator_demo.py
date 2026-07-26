# ============================================================
# جلسه ۱۵ — Iterator: iter، next، generator expression
# فایل: 02_iterator_demo.py
# ============================================================
# iterable: list، str، dict — با for قابل پیمایش
# iterator: خروجی iter() — با next() یک عنصر در هر بار
# ============================================================

numbers = [10, 20, 30]

# ساخت iterator از لیست
it = iter(numbers)
print("next 1:", next(it))
print("next 2:", next(it))
print("next 3:", next(it))

# next(it) → StopIteration (خطا — iterator تمام شد)

# حلقه for در پشت صحنه همین کار را می‌کند
print("\n--- حلقه for روی رشته ---")
for char in "سلام":
    print(char, end="|")
print()

# generator expression — مثل list comp ولی با () و lazy
squares_gen = (n * n for n in range(5))
print("\n--- generator expression ---")
print("نوع:", type(squares_gen))
print("اولین مقدار:", next(squares_gen))
print("بقیه:", list(squares_gen))

# مقایسه حافظه: list همه را می‌سازد، generator نه
list_comp = [n for n in range(5)]
gen_exp = (n for n in range(5))
print("\nlist comp:", list_comp)
print("gen exp (تبدیل به list):", list(gen_exp))

# iterator یک‌بار مصرف
gen2 = (n for n in range(3))
print("بار اول:", list(gen2))
print("بار دوم (خالی!):", list(gen2))
