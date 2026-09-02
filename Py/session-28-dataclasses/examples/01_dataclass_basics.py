# ============================================================
# جلسه ۲۸ — dataclass: پایه
# ============================================================

from dataclasses import dataclass


@dataclass
class User:
    name: str
    age: int
    email: str = ""


u1 = User("سعید", 30, "saeed@example.com")
u2 = User("سعید", 30, "saeed@example.com")
u3 = User("مریم", 25)

print(u1)
print("برابر؟", u1 == u2)
print("نام:", u1.name)
u1.age += 1
print("سن جدید:", u1.age)
