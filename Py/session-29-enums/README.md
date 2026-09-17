# جلسه ۲۹: Enumها — ۱.۵ ساعت

## 🎯 اهداف یادگیری

- تعریف Enum با مقادیر نام‌دار
- `Enum`, `IntEnum`, `StrEnum`, `Flag`
- دسترسی: name, value, iteration
- `@unique` و auto()
- مقایسه با string literal
- الگوهای real-world: status, role, HTTP method

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | مشکل magic string |
| 10–25 | Enum پایه |
| 25–40 | IntEnum و StrEnum |
| 40–55 | Flag و ترکیب |
| 55–70 | تبدیل و validation |
| 70–85 | تمرین |
| 85–90 | جمع‌بندی |

---

## ۱. مشکل magic string

```python
status = "pending"  # typo → "pendng" — بدون خطا!
if status == "done":
    ...
```

Enum typo را در زمان تعریف/استفاده محدود می‌کند.

---

## ۲. Enum پایه

```python
from enum import Enum

class Status(Enum):
    PENDING = "pending"
    ACTIVE = "active"
    DONE = "done"

task = Status.PENDING
print(task)           # Status.PENDING
print(task.name)      # PENDING
print(task.value)     # pending
print(task == Status.PENDING)  # True
```

---

## ۳. iteration

```python
for s in Status:
    print(s.name, s.value)

Status("pending")     # Status.PENDING
Status["PENDING"]     # Status.PENDING
```

---

## ۴. IntEnum

```python
from enum import IntEnum

class Priority(IntEnum):
    LOW = 1
    MEDIUM = 2
    HIGH = 3

print(Priority.HIGH > Priority.LOW)  # True
```

---

## ۵. StrEnum (Python 3.11+)

```python
from enum import StrEnum

class Color(StrEnum):
    RED = "red"
    GREEN = "green"
    BLUE = "blue"

print(Color.RED + "")  # red — رفتار str
```

برای نسخه‌های قدیمی‌تر از `(str, Enum)` استفاده کنید.

---

## ۶. auto()

```python
from enum import Enum, auto

class Day(Enum):
    MON = auto()
    TUE = auto()
    WED = auto()
```

---

## ۷. @unique

```python
from enum import Enum, unique

@unique
class Role(Enum):
    ADMIN = 1
    USER = 2
    # GUEST = 1  # ValueError: duplicate
```

---

## ۸. Flag — ترکیب bitwise

```python
from enum import Flag, auto

class Permission(Flag):
    READ = auto()
    WRITE = auto()
    EXECUTE = auto()

full = Permission.READ | Permission.WRITE
print(Permission.READ in full)  # True
```

---

## ۹. functional API

```python
from enum import Enum

Animal = Enum("Animal", ["CAT", "DOG", "BIRD"])
Animal = Enum("Animal", "CAT DOG BIRD")
```

---

## ۱۰. Enum در dataclass

```python
from dataclasses import dataclass
from enum import Enum

class OrderStatus(Enum):
    NEW = "new"
    SHIPPED = "shipped"
    DELIVERED = "delivered"

@dataclass
class Order:
    id: int
    status: OrderStatus = OrderStatus.NEW
```

---

## ۱۱. JSON serialization

```python
import json

status = Status.DONE
data = {"status": status.value}
json.dumps(data)  # {"status": "done"}

# بارگذاری
loaded = Status(json.loads('{"status":"done"}')["status"])
```

---

## ۱۲. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_enum_basics.py](./examples/01_enum_basics.py) | Enum پایه |
| [02_int_str_enum.py](./examples/02_int_str_enum.py) | IntEnum و StrEnum |
| [03_flag_permissions.py](./examples/03_flag_permissions.py) | Flag |
| [04_order_workflow.py](./examples/04_order_workflow.py) | workflow real-world |

---

## ۱۳. اشتباهات رایج

| اشتباه | مشکل |
|--------|------|
| مقایسه با string | `status == "pending"` False |
| duplicate value | بدون @unique هشدار نمی‌دهد |
| mutate Enum member | ممنوع |
| Enum در set با value mutable | — |

---

## ۱۴. Enum vs Literal

| | Enum | Literal |
|---|------|---------|
| runtime object | بله | فقط type hint |
| iteration | بله | خیر |
| IDE autocomplete | عالی | خوب |

---

## ۱۵. تمرین کلاسی

1. Enum `Status` با PENDING, DONE
2. IntEnum `Priority` — مقایسه عددی
3. Flag `Permission` — ترکیب READ|WRITE
4. تبدیل string به Enum با try/except

**تکلیف:** [question.md](./exercises/question.md)

---

## ۱۶. match با Enum (3.10+)

```python
match status:
    case Status.PENDING:
        print("در انتظار")
    case Status.DONE:
        print("تمام")
    case _:
        print("نامشخص")
```

---

## ۱۷. IntFlag

```python
from enum import IntFlag, auto

class Color(IntFlag):
    RED = auto()
    GREEN = auto()
    BLUE = auto()

white = Color.RED | Color.GREEN | Color.BLUE
print(Color.RED in white)
```

---

## ۱۸. auto() با start

```python
from enum import Enum, auto

class Number(Enum):
    def _generate_next_value_(name, start, count, last):
        return count + 1
    ONE = auto()
    TWO = auto()
```

---

## ۱۹. سوالات کلاسی

- چرا `"pending" == Status.PENDING` False است؟
- Flag با Enum معمولی چه فرقی دارد؟
- Enum در JSON چگونه serialize می‌شود؟

---

## 📚 مطالعه تکمیلی

- [enum — Python docs](https://docs.python.org/3/library/enum.html)

---

**جلسه قبل:** [۲۸ — Dataclasses](../session-28-dataclasses/) | **بعد:** [۳۰ — Collections](../session-30-collections/)
