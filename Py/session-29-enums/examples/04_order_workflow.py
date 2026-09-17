# ============================================================
# جلسه ۲۹ — Enum: workflow سفارش
# ============================================================

from dataclasses import dataclass, field
from enum import Enum
from typing import Self


class OrderStatus(Enum):
    NEW = "new"
    PAID = "paid"
    SHIPPED = "shipped"
    DELIVERED = "delivered"
    CANCELLED = "cancelled"


ALLOWED = {
    OrderStatus.NEW: {OrderStatus.PAID, OrderStatus.CANCELLED},
    OrderStatus.PAID: {OrderStatus.SHIPPED, OrderStatus.CANCELLED},
    OrderStatus.SHIPPED: {OrderStatus.DELIVERED},
}


@dataclass
class Order:
    id: int
    item: str
    status: OrderStatus = field(default=OrderStatus.NEW)

    def transition(self, new_status: OrderStatus) -> Self:
        allowed = ALLOWED.get(self.status, set())
        if new_status not in allowed:
            raise ValueError(
                f"انتقال {self.status.value} → {new_status.value} مجاز نیست"
            )
        self.status = new_status
        return self


order = Order(1, "کتاب پایتون")
order.transition(OrderStatus.PAID)
order.transition(OrderStatus.SHIPPED)
print(order)

try:
    order.transition(OrderStatus.NEW)
except ValueError as e:
    print("خطا:", e)
