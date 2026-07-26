# ============================================================
# تمرین جلسه ۴۱
# هدف: برای تابع is_even تست بنویسید.
# ============================================================

def is_even(n):
    return n % 2 == 0

def test_is_even():
    assert is_even(4) is True
    assert is_even(5) is False

test_is_even()
print("OK")
