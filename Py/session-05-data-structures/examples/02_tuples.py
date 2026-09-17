# ============================================================
# جلسه ۵ — تاپل
# فایل: 02_tuples.py
# ============================================================

point = (10, 20)
print("مختصات:", point)
print("x =", point[0])

# unpacking
x, y = point
print(f"({x}, {y})")

# تاپل تک‌عضو — کاما الزامی
t = (42,)
print(type(t), t)

# تغییرناپذیر — خط زیر خطا می‌دهد:
# point[0] = 5  # TypeError

# بازگشت چند مقدار (شبیه‌سازی)
def min_max(nums):
    return min(nums), max(nums)

lo, hi = min_max([3, 1, 9, 4])
print("min:", lo, "max:", hi)
