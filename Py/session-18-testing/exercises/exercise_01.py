# ============================================================
# تمرین ۱–۳ جلسه ۱۸ — تست‌نویسی (کلاسی)
# ============================================================

import unittest


def subtract(a, b):
    return a - b


def divide(a, b):
    if b == 0:
        raise ValueError("تقسیم بر صفر")
    return a / b


# --- ۱. assert ---
assert subtract(5, 3) == 2
assert subtract(0, 5) == -5
print("assert tests: OK")


# --- ۲. unittest ---
class TestSubtract(unittest.TestCase):
    def test_positive(self):
        self.assertEqual(subtract(10, 4), 6)

    def test_negative(self):
        self.assertEqual(subtract(3, 8), -5)

    # --- ۳. assertRaises ---
    def test_divide_by_zero(self):
        with self.assertRaises(ValueError):
            divide(1, 0)


if __name__ == "__main__":
    unittest.main(verbosity=2)
