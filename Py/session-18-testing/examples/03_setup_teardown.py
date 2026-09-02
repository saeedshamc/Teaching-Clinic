# ============================================================
# جلسه ۱۸ — setUp و tearDown
# فایل: 03_setup_teardown.py
# ============================================================

import unittest


class ShoppingCart:
    def __init__(self):
        self.items: list[str] = []

    def add(self, item: str) -> None:
        self.items.append(item)

    def count(self) -> int:
        return len(self.items)

    def clear(self) -> None:
        self.items.clear()


class TestShoppingCart(unittest.TestCase):
    def setUp(self):
        """قبل از هر test — سبد تازه"""
        self.cart = ShoppingCart()
        print(f"\n  [setUp] سبد جدید")

    def tearDown(self):
        """بعد از هر test"""
        self.cart.clear()
        print(f"  [tearDown] سبد پاک شد")

    def test_empty_cart(self):
        self.assertEqual(self.cart.count(), 0)

    def test_add_one_item(self):
        self.cart.add("لپ‌تاپ")
        self.assertEqual(self.cart.count(), 1)
        self.assertIn("لپ‌تاپ", self.cart.items)

    def test_add_multiple(self):
        self.cart.add("ماوس")
        self.cart.add("کیبورد")
        self.assertEqual(self.cart.count(), 2)


if __name__ == "__main__":
    unittest.main(verbosity=2)
