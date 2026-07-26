# ============================================================
# جلسه ۴۱ — سبک pytest
# فایل: 01_pytest_style.py
# ============================================================

def add(a, b):
    return a + b

def multiply(a, b):
    return a * b

def test_add():
    assert add(2, 3) == 5

def test_multiply():
    assert multiply(3, 4) == 12

test_add()
test_multiply()
print("همه تست‌های pytest-style موفق بودند")
