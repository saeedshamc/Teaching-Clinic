# ============================================================
# تمرین جلسه ۱۸
# هدف: برای تابع subtract دو تست بنویسید.
# ============================================================

import unittest


def subtract(a, b):
    return a - b


class TestSubtract(unittest.TestCase):
    def test_subtract_positive(self):
        self.assertEqual(subtract(10, 3), 7)

    def test_subtract_negative(self):
        self.assertEqual(subtract(3, 10), -7)


if __name__ == "__main__":
    unittest.main()
