# ============================================================
# تمرین جلسه ۴۳
# هدف: باگ یک تابع را با چاپ دیباگ پیدا کنید.
# ============================================================

def average(nums):
    print("DEBUG nums:", nums)
    if not nums:
        return 0
    return sum(nums) / len(nums)

print(average([2, 4, 6]))
print(average([]))
