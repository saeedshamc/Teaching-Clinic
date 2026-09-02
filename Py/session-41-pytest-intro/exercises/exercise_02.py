# ============================================================
# تمرین ۲ جلسه ۴۱ — fixture و validation (pytest)
# نیاز: pip install pytest
# ============================================================

import pytest


def require_non_empty(text: str) -> str:
    if not text.strip():
        raise ValueError("ورودی خالی مجاز نیست")
    return text.strip()


def cart_total(items: list[tuple[str, int]]) -> int:
    return sum(price for _, price in items)


@pytest.fixture
def sample_cart() -> list[tuple[str, int]]:
    return [("کتاب", 100), ("خودکار", 20)]


def test_require_non_empty_ok() -> None:
    assert require_non_empty("  سلام  ") == "سلام"


def test_require_non_empty_fail() -> None:
    with pytest.raises(ValueError, match="خالی"):
        require_non_empty("   ")


def test_cart_total(sample_cart: list[tuple[str, int]]) -> None:
    assert cart_total(sample_cart) == 120
