# ============================================================
# جلسه ۴۳ — breakpoint (commented برای اجرای خودکار)
# فایل: 01_breakpoint_demo.py
# ============================================================
# برای تمرین: خط breakpoint را uncomment کنید
# ============================================================


def divide(a: float, b: float) -> float | None:
    print(f"DEBUG: divide({a}, {b})")
    if b == 0:
        print("DEBUG: تقسیم بر صفر")
        return None
    # breakpoint()  # ← uncomment: n, p a, p b, c
    return a / b


def sum_until(n: int) -> int:
    total = 0
    for i in range(n):
        total += i
        # breakpoint()  # ← inspect i و total
    return total


print("10 / 2 =", divide(10, 2))
print("10 / 0 =", divide(10, 0))
print("sum_until(5) =", sum_until(5))
print("\n💡 breakpoint() را uncomment کنید و دوباره اجرا کنید")
