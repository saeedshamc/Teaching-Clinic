# ============================================================
# جلسه ۴ — جدول ضرب
# فایل: 04_multiplication_table.py
# ============================================================

size = 5
print(f"جدول ضرب {size}×{size}:")
print("-" * 30)

for i in range(1, size + 1):
    for j in range(1, size + 1):
        print(f"{i*j:3}", end=" ")
    print()

print()
print("جمع ۱ تا ۱۰۰:", sum(range(1, 101)))

# اولین عدد بخش‌پذیر بر ۷
for n in range(1, 51):
    if n % 7 == 0:
        print(f"اولین مضرب ۷ در ۱–۵۰: {n}")
        break
