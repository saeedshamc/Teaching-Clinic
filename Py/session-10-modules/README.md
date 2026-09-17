# جلسه ۱۰: ماژول‌ها و کتابخانه استاندارد (۱.۵ ساعت)

## 🎯 اهداف یادگیری

پس از این جلسه می‌توانید:
- مفهوم module و package را توضیح دهید
- `import`, `from ... import`, `as` استفاده کنید
- با `math`, `random`, `datetime`, `os` کار کنید
- ماژول شخصی بسازید و import کنید
- `if __name__ == "__main__"` را درک کنید
- ساختار پروژه چندفایلی ساده طراحی کنید

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | چرا ماژول؟ سازماندهی کد |
| 10–25 | import styles |
| 25–40 | math و random |
| 40–55 | datetime |
| 55–70 | os و pathlib (معرفی) |
| 70–85 | ماژول شخصی |
| 85–90 | تکلیف |

---

## ۱. ماژول چیست؟

**ماژول** = یک فایل `.py`. **Package** = پوشه با `__init__.py`.

```python
import math
print(math.sqrt(16))  # 4.0
```

---

## ۲. سبک‌های import

```python
import math
from math import sqrt, pi
from datetime import datetime as dt
import random as rnd

# ❌ from math import *  — namespace pollution
```

| سبک | کاربرد |
|-----|--------|
| `import m` | دسترسی `m.func` |
| `from m import f` | فقط f |
| `as` | نام کوتاه |

---

## ۳. math

```python
import math

print(math.sqrt(25))
print(math.ceil(4.2))
print(math.floor(4.8))
print(math.pow(2, 10))
print(math.pi)
```

---

## ۴. random

```python
import random

print(random.randint(1, 6))       # تاس
print(random.choice(["الف", "ب", "پ"]))
items = [1, 2, 3, 4, 5]
random.shuffle(items)
print(items)
```

---

## ۵. datetime

```python
from datetime import datetime, timedelta

now = datetime.now()
print(now.strftime("%Y-%m-%d %H:%M"))
tomorrow = now + timedelta(days=1)
print("فردا:", tomorrow.date())
```

---

## ۶. os

```python
import os

print("CWD:", os.getcwd())
print("exists:", os.path.exists("README.md"))
path = os.path.join("examples", "01_math.py")
print(path)
```

---

## ۷. `__name__ == "__main__"`

```python
# my_module.py
def helper():
    print("helper")

if __name__ == "__main__":
    helper()  # فقط وقتی مستقیم اجرا شود
```

---

## ۸. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_math.py](./examples/01_math.py) | math |
| [02_random.py](./examples/02_random.py) | random |
| [03_datetime.py](./examples/03_datetime.py) | datetime |
| [04_custom_module.py](./examples/04_custom_module.py) | import محلی |

---

## ۹. اشتباهات رایج

| اشتباه | راه‌حل |
|--------|--------|
| import * | import صریح |
| circular import | refactor |
| نام فایل = stdlib | مثلاً `random.py` نسازید |
| مسیر import | PYTHONPATH یا package |

---

## ۱۰. تمرین کلاسی

1. sqrt و pi از math
2. عدد تصادفی ۱–۱۰۰
3. تاریخ امروز formatted
4. ماژول utils با تابع add

**تکلیف:** [question.md](./exercises/question.md)

### ۷.۱. sys.argv (پیش‌نمایش)

```python
import sys
print("args:", sys.argv)
```

### ۷.۲. json module

```python
import json
data = {"name": "سعید"}
text = json.dumps(data, ensure_ascii=False)
```

---

## ۱۲. تکلیف خانه

1. **password generator** با random + string
2. **age calculator** با datetime
3. **package mytools** — پوشه با __init__.py

---

## ۱۴. توضیح خط‌به‌خط مثال‌ها

### 📄 `01_math.py`

```python
import math
print(math.sqrt(16))
```

- `math` stdlib — نیاز به pip ندارد
- توابع همیشه `math.` prefix

### 📄 `02_random.py`

- `seed(42)` — reproducible random برای تست
- `shuffle` in-place — لیست را تغییر می‌دهد

### 📄 `04_custom_module.py`

```python
if __name__ == "__main__":
    demo()
```

- وقتی import شود `__name__` = نام ماژول
- وقتی مستقیم اجرا شود `__name__` = `"__main__"`

---

## ۱۶. stdlib پیشنهادی برای مطالعه

| ماژول | کاربرد |
|-------|--------|
| collections | Counter, deque |
| itertools | ترکیبات |
| functools | lru_cache |
| pathlib | مسیر فایل |
| json | JSON I/O |

---

## ۱۷. مطالعه بیشتر

- [Python Module Index](https://docs.python.org/3/py-modindex.html)
- ساخت package با `__init__.py`
- تمرین: split پروژه به models.py + main.py

---

## ۱۹. تکلیف خانه (جزئیات)

** dice simulator:** 1000 بار تاس — histogram

** date diff:** دو تاریخ — اختلاف روز

** project structure:**

```
myapp/
  __init__.py
  utils.py
  main.py
```

---

## ۲۱. پرسش‌های کلاسی (Q&A)

**س: import math vs from math import *؟**  
ج: دومی namespace را آلوده می‌کند.

**س: __name__ == "__main__"؟**  
ج: فقط direct execution.

**س: sys.path چیست؟**  
ج: لیست مسیرهای جستجوی import.

---

## ۲۲. خلاصه

- ✅ module = فایل .py
- ✅ import / from / as
- ✅ stdlib: math, random, datetime, os
- ✅ `__name__ == "__main__"`

---

**جلسه قبل:** [۹ — OOP](../session-09-oop/) | **بعد:** [۱۱ — کتابخانه بیرونی](../session-11-external-libs/)
