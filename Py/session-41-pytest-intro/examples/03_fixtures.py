# ============================================================
# جلسه ۴۱ — fixture
# فایل: 03_fixtures.py
# نیاز: pip install pytest
# ============================================================

import pytest


@pytest.fixture
def sample_numbers() -> list[int]:
    return [1, 2, 3, 4, 5]


@pytest.fixture
def user() -> dict:
    return {"name": "سعید", "age": 30, "active": True}


def total(numbers: list[int]) -> int:
    return sum(numbers)


def greet(user: dict) -> str:
    return f"سلام {user['name']}"


def test_total(sample_numbers: list[int]) -> None:
    assert total(sample_numbers) == 15


def test_greet(user: dict) -> None:
    assert greet(user) == "سلام سعید"
    assert user["active"] is True
