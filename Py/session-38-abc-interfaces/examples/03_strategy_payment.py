# ============================================================
# جلسه ۳۸ — Strategy: Payment
# ============================================================

from abc import ABC, abstractmethod
from dataclasses import dataclass


class PaymentStrategy(ABC):
    @abstractmethod
    def pay(self, amount: float) -> bool:
        pass

    @abstractmethod
    def name(self) -> str:
        pass


class CreditCardPayment(PaymentStrategy):
    def __init__(self, last4: str):
        self.last4 = last4

    def name(self) -> str:
        return f"کارت ****{self.last4}"

    def pay(self, amount: float) -> bool:
        print(f"  پرداخت {amount:,.0f} تومان با {self.name()}")
        return True


class WalletPayment(PaymentStrategy):
    def __init__(self, balance: float):
        self.balance = balance

    def name(self) -> str:
        return "کیف پول"

    def pay(self, amount: float) -> bool:
        if amount > self.balance:
            print(f"  موجودی ناکافی: {self.balance:,.0f}")
            return False
        self.balance -= amount
        print(f"  پرداخت {amount:,.0f} — باقیمانده {self.balance:,.0f}")
        return True


@dataclass
class Checkout:
    strategy: PaymentStrategy

    def process(self, amount: float) -> bool:
        print(f"Checkout با {self.strategy.name()}:")
        return self.strategy.pay(amount)


Checkout(CreditCardPayment("1234")).process(500_000)
Checkout(WalletPayment(300_000)).process(200_000)
Checkout(WalletPayment(300_000)).process(400_000)
