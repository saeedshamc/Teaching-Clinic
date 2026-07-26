# ============================================================
# تمرین جلسه ۲۹
# هدف: Enum برای نقش کاربر بسازید.
# ============================================================

from enum import Enum


class Role(Enum):
    ADMIN = "admin"
    USER = "user"


print(Role.ADMIN)
print(Role.USER.value)
