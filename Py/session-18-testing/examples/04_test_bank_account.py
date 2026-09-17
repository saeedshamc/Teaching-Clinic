# ============================================================
# جلسه ۱۸ — تست کلاس BankAccount
# فایل: 04_test_bank_account.py
# ============================================================

import unittest


class BankAccount:
    def __init__(self, owner: str, balance: float = 0):
        self.owner = owner
        self.balance = balance

    def deposit(self, amount: float) -> None:
        if amount <= 0:
            raise ValueError("مبلغ واریز باید مثبت باشد")
        self.balance += amount

    def withdraw(self, amount: float) -> bool:
        if amount <= 0:
            raise ValueError("مبلغ برداشت باید مثبت باشد")
        if amount > self.balance:
            return False
        self.balance -= amount
        return True


class TestBankAccount(unittest.TestCase):
    def setUp(self):
        self.account = BankAccount("علی", 1000)

    def test_initial_balance(self):
        self.assertEqual(self.account.balance, 1000)

    def test_deposit(self):
        self.account.deposit(500)
        self.assertEqual(self.account.balance, 1500)

    def test_deposit_negative_raises(self):
        with self.assertRaises(ValueError):
            self.account.deposit(-100)

    def test_withdraw_success(self):
        result = self.account.withdraw(300)
        self.assertTrue(result)
        self.assertEqual(self.account.balance, 700)

    def test_withdraw_insufficient_funds(self):
        result = self.account.withdraw(2000)
        self.assertFalse(result)
        self.assertEqual(self.account.balance, 1000)


if __name__ == "__main__":
    unittest.main(verbosity=2)
