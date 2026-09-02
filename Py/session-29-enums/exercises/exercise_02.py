# ============================================================
# پاسخ نمونه — تمرین جلسه ۲۹ (پیشرفته)
# ============================================================

import json
from enum import Flag, StrEnum, auto, unique


class Permission(Flag):
    READ = auto()
    WRITE = auto()
    EXEC = auto()


@unique
class OrderStatus(StrEnum):
    NEW = "new"
    PAID = "paid"
    SHIPPED = "shipped"
    DELIVERED = "delivered"


def user_can(user_perm: Permission, needed: Permission) -> bool:
    return needed in user_perm


editor = Permission.READ | Permission.WRITE
print("editor WRITE?", user_can(editor, Permission.WRITE))
print("editor EXEC?", user_can(editor, Permission.EXEC))

status = OrderStatus.SHIPPED
payload = json.dumps({"status": status.value}, ensure_ascii=False)
print("JSON:", payload)

restored = OrderStatus(json.loads(payload)["status"])
print("بازیابی:", restored, restored == status)
