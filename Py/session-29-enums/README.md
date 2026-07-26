# جلسه ۲۹: Enumها

## اهداف یادگیری (Learning Objectives)

پس از این جلسه می‌توانید:
- با Enum مجموعه‌های ثابت بسازید
- از مقادیر نام‌گذاری‌شده به‌جای رشته خام استفاده کنید
- خوانایی و ایمنی کد را بالا ببرید

---

## Enum چیست؟

Enum مجموعه‌ای از مقادیر ثابت و نام‌دار است؛ مثل وضعیت سفارش یا نقش کاربر.

```python
from enum import Enum

class Status(Enum):
    PENDING = "pending"
    DONE = "done"

task_status = Status.PENDING
print(task_status)
print(task_status.value)
```

### توضیح
- `Enum` → مقادیر ثابت را گروه‌بندی می‌کند
- `.value` → مقدار واقعی عضو را می‌دهد

---

## فایل‌های این جلسه
- [examples/01_enum.py](./examples/01_enum.py)
- [exercises/exercise_01.py](./exercises/exercise_01.py)

## تمرین‌ها
- تمرین ساده: Enum وضعیت بسازید
- تمرین متوسط: Enum نقش کاربر بسازید
