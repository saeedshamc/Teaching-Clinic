# ============================================================
# جلسه ۴۱ — تست پایه (بدون نیاز به pytest برای اجرا)
# فایل: 01_basic_tests.py
# ============================================================


def add(a: int, b: int) -> int:
    return a + b


def multiply(a: int, b: int) -> int:
    return a * b


def is_even(n: int) -> bool:
    return n % 2 == 0


def test_add() -> None:
    assert add(2, 3) == 5
    assert add(-1, 1) == 0


def test_multiply() -> None:
    assert multiply(3, 4) == 12


def test_is_even() -> None:
    assert is_even(2) is True
    assert is_even(3) is False


if __name__ == "__main__":
    test_add()
    test_multiply()
    test_is_even()
    print("✅ همه تست‌های پایه موفق بودند")
    print("برای pytest: pip install pytest && pytest 01_basic_tests.py -v")
