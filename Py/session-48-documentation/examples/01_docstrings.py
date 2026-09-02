# ============================================================
# جلسه ۴۸ — docstring Google style
# فایل: 01_docstrings.py
# ============================================================


def add(a: int, b: int) -> int:
    """دو عدد صحیح را جمع می‌کند.

    Args:
        a: عدد اول
        b: عدد دوم

    Returns:
        حاصل جمع a و b
    """
    return a + b


def divide(a: float, b: float) -> float:
    """a را بر b تقسیم می‌کند.

    Args:
        a: صورت
        b: مخرج

    Returns:
        حاصل تقسیم

    Raises:
        ValueError: اگر b صفر باشد
    """
    if b == 0:
        raise ValueError("تقسیم بر صفر")
    return a / b


print(add(2, 3))
print("--- docstring add ---")
print(add.__doc__)
print("--- divide ---")
print(divide(10, 2))
