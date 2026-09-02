# ============================================================
# جلسه ۹ — حساب بانکی
# فایل: 04_bank_account.py
# ============================================================

class BankAccount:
    def __init__(self, owner, balance=0):
        self.owner = owner
        self._balance = balance  # convention: internal

    def deposit(self, amount):
        if amount <= 0:
            raise ValueError("مبلغ واریز باید مثبت باشد")
        self._balance += amount

    def withdraw(self, amount):
        if amount <= 0:
            raise ValueError("مبلغ برداشت باید مثبت باشد")
        if amount > self._balance:
            raise ValueError("موجودی کافی نیست")
        self._balance -= amount

    @property
    def balance(self):
        return self._balance

    def __str__(self):
        return f"{self.owner}: {self._balance:,} تومان"


acc = BankAccount("سعید", 100_000)
acc.deposit(50_000)
acc.withdraw(30_000)
print(acc)
