# ============================================================
# تمرین ۲ جلسه ۴۸ — doctest و README
# ============================================================

"""Utilities module — توابع ریاضی ساده."""


def multiply(a: int, b: int) -> int:
    """ضرب دو عدد.

    >>> multiply(3, 4)
    12
    >>> multiply(0, 100)
    0
    """
    return a * b


README = """# math-utils

## نصب
pip install -e .

## استفاده
```python
from math_utils import multiply
multiply(2, 5)
```
"""


if __name__ == "__main__":
    print(multiply(6, 7))
    print(README)
