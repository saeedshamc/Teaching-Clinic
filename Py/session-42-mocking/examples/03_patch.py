# ============================================================
# جلسه ۴۲ — patch
# فایل: 03_patch.py
# ============================================================

import random
from unittest.mock import patch


def pick_winner(names: list[str]) -> str:
    index = random.randint(0, len(names) - 1)
    return names[index]


@patch("random.randint")
def test_pick_winner(mock_randint):
    mock_randint.return_value = 1
    result = pick_winner(["علی", "مریم", "رضا"])
    assert result == "مریم"
    mock_randint.assert_called_once_with(0, 2)
    print("✅ test_pick_winner passed")


if __name__ == "__main__":
    test_pick_winner()
