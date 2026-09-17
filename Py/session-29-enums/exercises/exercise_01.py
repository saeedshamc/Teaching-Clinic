# ============================================================
# پاسخ نمونه — تمرین جلسه ۲۹ (ساده)
# ============================================================

from enum import Enum, IntEnum, StrEnum


class Status(Enum):
    PENDING = "pending"
    IN_PROGRESS = "in_progress"
    DONE = "done"


class Priority(IntEnum):
    LOW = 1
    MEDIUM = 2
    HIGH = 3


class Role(StrEnum):
    ADMIN = "admin"
    USER = "user"
    GUEST = "guest"


def parse_status(s: str) -> Status:
    try:
        return Status(s)
    except ValueError:
        raise ValueError(f"وضعیت نامعتبر: {s!r}") from None


for s in Status:
    print(f"{s.name} = {s.value}")

print("HIGH > LOW:", Priority.HIGH > Priority.LOW)
print("Role:", Role.ADMIN)

print(parse_status("done"))
try:
    parse_status("invalid")
except ValueError as e:
    print(e)
