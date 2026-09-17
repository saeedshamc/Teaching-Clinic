# ============================================================
# تمرین ۴–۵ جلسه ۱۸ — تست‌نویسی (خانه)
# ============================================================

import unittest


class BankAccount:
    def __init__(self, balance=0):
        self.balance = balance

    def deposit(self, amount):
        if amount <= 0:
            raise ValueError("مبلغ نامعتبر")
        self.balance += amount

    def withdraw(self, amount):
        if amount > self.balance:
            return False
        self.balance -= amount
        return True


class ShoppingCart:
    def __init__(self):
        self.items = []

    def add(self, item):
        self.items.append(item)

    def count(self):
        return len(self.items)


class TestBankAccount(unittest.TestCase):
    def setUp(self):
        self.acc = BankAccount(1000)

    def test_deposit(self):
        self.acc.deposit(500)
        self.assertEqual(self.acc.balance, 1500)

    def test_withdraw_success(self):
        self.assertTrue(self.acc.withdraw(300))
        self.assertEqual(self.acc.balance, 700)

    def test_withdraw_fail(self):
        self.assertFalse(self.acc.withdraw(2000))


class TestShoppingCart(unittest.TestCase):
    def setUp(self):
        self.cart = ShoppingCart()

    def test_add_and_count(self):
        self.cart.add("ماوس")
        self.cart.add("کیبورد")
        self.assertEqual(self.cart.count(), 2)


if __name__ == "__main__":
    unittest.main(verbosity=2)
