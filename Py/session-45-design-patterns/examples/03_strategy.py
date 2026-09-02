# ============================================================
# جلسه ۴۵ — Strategy
# فایل: 03_strategy.py
# ============================================================

from typing import Protocol


class DiscountStrategy(Protocol):
    def apply(self, amount: int) -> int: ...


class NoDiscount:
    def apply(self, amount: int) -> int:
        return amount


class PercentDiscount:
    def __init__(self, percent: int):
        self.percent = percent

    def apply(self, amount: int) -> int:
        return amount - amount * self.percent // 100


class FixedDiscount:
    def __init__(self, fixed: int):
        self.fixed = fixed

    def apply(self, amount: int) -> int:
        return max(0, amount - self.fixed)


class Checkout:
    def __init__(self, strategy: DiscountStrategy):
        self.strategy = strategy

    def total(self, amount: int) -> int:
        return self.strategy.apply(amount)


price = 100_000
for strategy in [NoDiscount(), PercentDiscount(10), FixedDiscount(15_000)]:
    checkout = Checkout(strategy)
    print(f"{type(strategy).__name__:16} → {checkout.total(price):,} تومان")
