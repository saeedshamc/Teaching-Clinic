# ============================================================
# جلسه ۲۹ — IntEnum و StrEnum
# ============================================================

from enum import IntEnum, StrEnum, auto


class Priority(IntEnum):
    LOW = 1
    MEDIUM = 2
    HIGH = 3
    CRITICAL = 4


class Color(StrEnum):
    RED = "red"
    GREEN = "green"
    BLUE = "blue"


class HttpMethod(StrEnum):
    GET = auto()
    POST = auto()
    PUT = auto()
    DELETE = auto()


print("HIGH > LOW:", Priority.HIGH > Priority.LOW)
print("Color:", Color.RED, "— upper:", Color.RED.upper())

tasks = [
    ("بک‌آپ", Priority.LOW),
    ("باگ امنیتی", Priority.CRITICAL),
    ("گزارش", Priority.MEDIUM),
]
for name, p in sorted(tasks, key=lambda x: x[1], reverse=True):
    print(f"{name}: {p.name}")

print("Method:", HttpMethod.POST)
