# ============================================================
# جلسه ۲۸ — dataclass: nested و asdict
# ============================================================

import json
from dataclasses import asdict, dataclass


@dataclass
class Address:
    city: str
    street: str
    postal_code: str = ""


@dataclass
class Customer:
    id: int
    name: str
    address: Address


customers = [
    Customer(1, "سعید", Address("تهران", "ولیعصر", "1234567890")),
    Customer(2, "مریم", Address("اصفهان", "چهارباغ")),
]

for c in customers:
    print(c)

payload = [asdict(c) for c in customers]
json_str = json.dumps(payload, ensure_ascii=False, indent=2)
print("\nJSON:")
print(json_str)
