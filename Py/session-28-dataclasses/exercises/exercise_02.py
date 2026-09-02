# ============================================================
# پاسخ نمونه — تمرین جلسه ۲۸ (پیشرفته)
# ============================================================

import json
from dataclasses import asdict, dataclass, field


@dataclass(order=True)
class Score:
    value: int
    player: str = field(compare=False)


@dataclass
class Order:
    id: int
    items: list[str] = field(default_factory=list)
    total: float = 0.0

    def __post_init__(self):
        if self.total < 0:
            raise ValueError("total نامعتبر")
        if not self.items:
            raise ValueError("سفارش خالی")


@dataclass
class Address:
    city: str
    street: str


@dataclass
class Customer:
    name: str
    address: Address
    skills: set[str] = field(default_factory=set)


order = Order(1, ["کتاب", "ماوس"], 1500000)
print(order)

scores = sorted([Score(85, "علی"), Score(92, "مریم"), Score(78, "رضا")])
print("رتبه:", scores)

customer = Customer("سعید", Address("تهران", "ولیعصر"), {"python", "sql"})
print(json.dumps(asdict(customer), ensure_ascii=False, indent=2))
