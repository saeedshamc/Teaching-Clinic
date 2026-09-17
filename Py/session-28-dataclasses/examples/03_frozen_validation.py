# ============================================================
# جلسه ۲۸ — dataclass: frozen و validation
# ============================================================

import math
from dataclasses import dataclass


@dataclass(frozen=True)
class Point:
    x: float
    y: float

    def distance_from_origin(self) -> float:
        return math.hypot(self.x, self.y)


@dataclass
class BankAccount:
    owner: str
    balance: float = 0.0

    def __post_init__(self):
        if self.balance < 0:
            raise ValueError("موجودی نمی‌تواند منفی باشد")

    def deposit(self, amount: float) -> None:
        if amount <= 0:
            raise ValueError("مبلغ باید مثبت باشد")
        object.__setattr__(self, "balance", self.balance + amount)


p = Point(3, 4)
print("فاصله:", p.distance_from_origin())

acc = BankAccount("سعید", 1000)
acc.deposit(500)
print(acc)

try:
    BankAccount("بد", -100)
except ValueError as e:
    print("خطا:", e)
