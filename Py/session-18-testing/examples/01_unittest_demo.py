# ============================================================
# جلسه ۱۸ — تست با unittest
# فایل: 01_unittest_demo.py
# ============================================================

import unittest


def add(a, b):
    return a + b


def multiply(a, b):
    return a * b


class TestMath(unittest.TestCase):
    def test_add(self):
        self.assertEqual(add(2, 3), 5)

    def test_multiply(self):
        self.assertEqual(multiply(3, 4), 12)


if __name__ == "__main__":
    unittest.main()
