# ============================================================
# تمرین ۱ جلسه ۳۹ — Product و BankAccount
# ============================================================

class Product:
    def __init__(self, name: str, price: int):
        self.name = name
        self.price = price

    @property
    def price(self) -> int:
        return self._price

    @price.setter
    def price(self, value: int) -> None:
        if value < 0:
            raise ValueError("قیمت منفی مجاز نیست")
        self._price = value


class BankAccount:
    def __init__(self, owner: str, initial: int = 0):
        self.owner = owner
        self._balance = initial

    @property
    def balance(self) -> int:
        return self._balance

    def deposit(self, amount: int) -> None:
        if amount <= 0:
            raise ValueError("مبلغ واریز باید مثبت باشد")
        self._balance += amount

    def withdraw(self, amount: int) -> None:
        if amount <= 0:
            raise ValueError("مبلغ برداشت باید مثبت باشد")
        if amount > self._balance:
            raise ValueError("موجودی کافی نیست")
        self._balance -= amount


if __name__ == "__main__":
    p = Product("ماوس", 250_000)
    print("قیمت:", p.price)

    acc = BankAccount("سعید", 1000)
    acc.deposit(500)
    acc.withdraw(200)
    print(f"موجودی {acc.owner}: {acc.balance}")
