# ============================================================
# جلسه ۴۳ — Debugging
# فایل: 01_debugging.py
# ============================================================

def divide(a, b):
    print(f"DEBUG: a={a}, b={b}")
    if b == 0:
        print("DEBUG: تقسیم بر صفر")
        return None
    result = a / b
    print(f"DEBUG: result={result}")
    return result

print(divide(10, 2))
print(divide(10, 0))
print("نکته: در کد واقعی می‌توانید breakpoint() بگذارید")
