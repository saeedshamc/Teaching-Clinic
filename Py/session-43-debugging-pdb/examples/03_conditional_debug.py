# ============================================================
# جلسه ۴۳ — دیباگ شرطی
# فایل: 03_conditional_debug.py
# ============================================================


def find_negative(numbers: list[int]) -> int | None:
    for i, value in enumerate(numbers):
        if value < 0:
            print(f"⚠️ مقدار منفی در index={i}: value={value}")
            # breakpoint()  # در تمرین: uncomment
            return value
    return None


def buggy_average(values: list[float]) -> float:
    total = 0.0
    count = 0
    for v in values:
        total += v
        count += 1
    if count == 0:
        print("DEBUG: لیست خالی — تقسیم بر صفر!")
        return 0.0
    return total / count


data = [10, 20, -5, 30]
print("اولین منفی:", find_negative(data))
print("میانگین:", buggy_average([1, 2, 3]))
print("میانگین خالی:", buggy_average([]))
