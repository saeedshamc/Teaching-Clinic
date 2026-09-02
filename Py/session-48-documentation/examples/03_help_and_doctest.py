# ============================================================
# جلسه ۴۸ — help و doctest
# فایل: 03_help_and_doctest.py
# ============================================================


def double(x: int) -> int:
    """عدد را دو برابر می‌کند.

    Args:
        x: عدد ورودی

    Returns:
        دو برابر x

    Examples:
        >>> double(3)
        6
        >>> double(0)
        0
    """
    return x * 2


def is_palindrome(text: str) -> bool:
    """بررسی palindrome بودن (بدون فاصله، case-insensitive).

    >>> is_palindrome("aba")
    True
    >>> is_palindrome("hello")
    False
    """
    cleaned = text.lower().replace(" ", "")
    return cleaned == cleaned[::-1]


if __name__ == "__main__":
    print("double(5) =", double(5))
    print("\n--- help(double) ---")
    help(double)
