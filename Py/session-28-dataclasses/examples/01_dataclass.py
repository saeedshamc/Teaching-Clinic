# ============================================================
# جلسه ۲۸ — dataclass
# فایل: 01_dataclass.py
# ============================================================

from dataclasses import dataclass


@dataclass
class User:
    name: str
    age: int
    active: bool = True


user = User("سعید", 30)
print(user)
print("فعال؟", user.active)
