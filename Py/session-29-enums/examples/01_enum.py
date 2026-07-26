# ============================================================
# جلسه ۲۹ — Enum
# فایل: 01_enum.py
# ============================================================

from enum import Enum


class Status(Enum):
    PENDING = "pending"
    DONE = "done"


def describe(status: Status) -> str:
    if status is Status.DONE:
        return "انجام شده"
    return "در انتظار"


print(describe(Status.PENDING))
print(Status.DONE.value)
