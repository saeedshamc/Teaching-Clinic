# ============================================================
# تمرین ۱ جلسه ۴۱ — تست‌های پایه
# ============================================================


def add(a: int, b: int) -> int:
    return a + b


def subtract(a: int, b: int) -> int:
    return a - b


def abs_value(n: int) -> int:
    return abs(n)


def test_add() -> None:
    assert add(2, 3) == 5
    assert add(0, 0) == 0


def test_subtract() -> None:
    assert subtract(10, 3) == 7
    assert subtract(5, 5) == 0


def test_abs() -> None:
    assert abs_value(5) == 5
    assert abs_value(-5) == 5
    assert abs_value(0) == 0


if __name__ == "__main__":
    test_add()
    test_subtract()
    test_abs()
    print("✅ تمرین ۱ موفق")
