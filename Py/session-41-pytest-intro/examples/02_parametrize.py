# ============================================================
# جلسه ۴۱ — parametrize
# فایل: 02_parametrize.py
# نیاز: pip install pytest
# ============================================================

import pytest


def is_palindrome(text: str) -> bool:
    cleaned = text.lower().replace(" ", "")
    return cleaned == cleaned[::-1]


@pytest.mark.parametrize(
    "text,expected",
    [
        ("radar", True),
        ("hello", False),
        ("aba", True),
        ("", True),
    ],
)
def test_is_palindrome(text: str, expected: bool) -> None:
    assert is_palindrome(text) == expected


@pytest.mark.parametrize("a,b,expected", [(1, 2, 3), (0, 0, 0), (-5, 5, 0)])
def test_add_param(a: int, b: int, expected: int) -> None:
    assert a + b == expected
