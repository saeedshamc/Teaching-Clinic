# ============================================================
# جلسه ۴۱ — تست exception
# فایل: 04_exceptions.py
# نیاز: pip install pytest
# ============================================================

import pytest


def divide(a: float, b: float) -> float:
    if b == 0:
        raise ValueError("تقسیم بر صفر")
    return a / b


def parse_age(text: str) -> int:
    age = int(text)
    if age < 0:
        raise ValueError("سن منفی")
    return age


def test_divide_by_zero() -> None:
    with pytest.raises(ValueError, match="تقسیم بر صفر"):
        divide(10, 0)


def test_divide_ok() -> None:
    assert divide(10, 2) == 5.0


def test_parse_age_invalid() -> None:
    with pytest.raises(ValueError):
        parse_age("-1")
