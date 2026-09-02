# ============================================================
# جلسه ۲۹ — Enum: پایه
# ============================================================

from enum import Enum


class Status(Enum):
    PENDING = "pending"
    ACTIVE = "active"
    DONE = "done"


def describe(status: Status) -> str:
    return f"{status.name} = {status.value}"


task_status = Status.PENDING
print(describe(task_status))
print("برابر DONE?", task_status == Status.DONE)

print("\nهمه وضعیت‌ها:")
for s in Status:
    print(f"  {s.name:8} → {s.value}")

# تبدیل از value
loaded = Status("active")
print("\nاز رشته:", loaded)
