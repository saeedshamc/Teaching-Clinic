# ============================================================
# جلسه ۱۸ — تست exception با assertRaises
# فایل: 02_assert_raises.py
# ============================================================

import unittest


def divide(a: float, b: float) -> float:
    if b == 0:
        raise ValueError("تقسیم بر صفر مجاز نیست")
    return a / b


def parse_age(text: str) -> int:
    age = int(text)
    if age < 0 or age > 150:
        raise ValueError("سن نامعتبر")
    return age


class TestDivide(unittest.TestCase):
    def test_normal(self):
        self.assertEqual(divide(10, 2), 5)

    def test_float_result(self):
        self.assertAlmostEqual(divide(1, 3), 0.333, places=2)

    def test_divide_by_zero(self):
        with self.assertRaises(ValueError) as ctx:
            divide(10, 0)
        self.assertIn("صفر", str(ctx.exception))


class TestParseAge(unittest.TestCase):
    def test_valid(self):
        self.assertEqual(parse_age("25"), 25)

    def test_invalid_format(self):
        with self.assertRaises(ValueError):
            parse_age("abc")

    def test_out_of_range(self):
        with self.assertRaises(ValueError):
            parse_age("200")


if __name__ == "__main__":
    unittest.main(verbosity=2)
