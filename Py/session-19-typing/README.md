# جلسه ۱۹: Type Hints (۱.۵ ساعت)

## ۰. مرور

Python dynamic است — type hint **اختیاری** است ولی در پروژه‌های بزرگ، IDE autocomplete و mypy ارزش زیادی دارند.

---

## 🎯 اهداف یادگیری

پس از این جلسه می‌توانید:
- مفهوم Type Hint را توضیح دهید
- نوع پارامترها و مقدار بازگشتی را annotate کنید
- از `list[str]`، `dict[str, int]` و تایپ‌های ترکیبی استفاده کنید
- `Optional`، `Union` و `|` را بشناسید
- TypedDict و dataclass با type hint را تشخیص دهید
- بدانید Python همچنان dynamic است — hint اجباری نیست
- از mypy یا Pylance برای بررسی static استفاده کنید

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع | فعالیت |
|------|--------|--------|
| 0–10 | مرور | چرا type hint؟ |
| 10–25 | پایه | param، return، variable |
| 25–40 | collection types | list، dict، tuple |
| 40–55 | Optional و Union | None، چند نوع |
| 55–70 | Callable و TypedDict | callback، struct |
| 70–85 | تمرین | annotate توابع |
| 85–90 | جمع‌بندی + تکلیف |

---

## ۱. Type Hint چیست؟

Type Hint **نشانه نوع** برای متغیرها و توابع است. Python در runtime اجبار نمی‌کند — فقط برای خواننده، IDE و ابزار static analysis.

```python
def greet(name: str) -> str:
    return f"سلام {name}"

message: str = greet("سعید")
```

---

## ۲. تایپ‌های پایه

```python
age: int = 30
price: float = 19.99
name: str = "سعید"
active: bool = True
```

---

## ۳. Collection Types (Python 3.9+)

```python
def average(numbers: list[float]) -> float:
    return sum(numbers) / len(numbers)

def find_age(people: dict[str, int], name: str) -> int:
    return people[name]

def get_coords() -> tuple[float, float]:
    return (35.7, 51.4)
```

---

## ۴. Optional و None

```python
def find_user(users: list[str], name: str) -> str | None:
    for u in users:
        if u == name:
            return u
    return None
```

`str | None` معادل `Optional[str]` از `typing`.

---

## ۵. Union — چند نوع

```python
def process(value: int | str) -> str:
    return str(value)
```

---

## ۶. Callable

```python
from collections.abc import Callable

def apply_twice(func: Callable[[int], int], value: int) -> int:
    return func(func(value))
```

---

## ۷. TypedDict

```python
from typing import TypedDict

class User(TypedDict):
    name: str
    age: int

def greet_user(user: User) -> str:
    return f"سلام {user['name']}"
```

---

## ۸. Any و object

```python
from typing import Any

def debug_log(data: Any) -> None:
    print(repr(data))
```

`Any` = هر نوعی — فقط وقتی واقعاً لازم است.

---

## ۹. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_type_hints.py](./examples/01_type_hints.py) | پایه |
| [02_optional_union.py](./examples/02_optional_union.py) | Optional، Union |
| [03_callable_typeddict.py](./examples/03_callable_typeddict.py) | Callable، TypedDict |
| [04_generic_basics.py](./examples/04_generic_basics.py) | TypeVar ساده |

---

## ۱۰. اشتباهات رایج (Pitfalls)

### ❌ فکر کردن hint = validation
```python
def add(a: int, b: int) -> int:
    return a + b

add("1", "2")  # ❌ خطا نمی‌دهد — "12" برمی‌گرداند!
```

### ❌ over-use Any
همه‌جا Any = type hint بی‌فایده.

### ❌ list vs List
Python 3.9+: `list[str]` — نیازی به `from typing import List` نیست.

---

## ۱۱. خلاصه

- ✅ hint برای خوانایی و IDE
- ✅ `list[str]`، `dict[k, v]`، `tuple[...]`
- ✅ `X | None` برای optional
- ✅ Python dynamic می‌ماند

---

## ۱۲. تکلیف

راهنما: [exercises/question.md](./exercises/question.md)  
پاسخ: [exercise_01.py](./exercises/exercise_01.py) | [exercise_02.py](./exercise_02.py)

---

## ۱۳. mypy — بررسی static (اختیاری)

```bash
pip install mypy
mypy script.py
```

mypia خطاهای نوع را **قبل از اجرا** پیدا می‌کند.

---

## ۱۴. TYPE_CHECKING — جلوگیری از circular import

```python
from typing import TYPE_CHECKING
if TYPE_CHECKING:
    from mymodule import MyClass
```

---

## ۱۵. Python 3.10+ syntax

```python
def process(value: int | str) -> str | None: ...
# به‌جای Union[int, str] و Optional[str]
```

---

## ۱۶. توضیح فایل‌های مثال

### 📄 `01_type_hints.py` — list[float]، dict[str, int]
### 📄 `02_optional_union.py` — int | None
### 📄 `03_callable_typeddict.py` — Callable و TypedDict
### 📄 `04_generic_basics.py` — TypeVar

---

## ۱۷. سوالات کلاسی

1. type hint در runtime اجبار می‌کند؟
2. `list[str]` vs `List[str]`؟
3. TypedDict vs dataclass؟

---

## ۱۸. gradual typing

می‌توانید فقط بخشی از کد را annotate کنید — لازم نیست یک‌شبه همه فایل‌ها.

```python
def legacy(x): ...           # بدون hint
def new_func(x: int) -> str: ...  # با hint
```

---

## راهنمای مدرس (۱۵ دقیقه)

- قبل از شروع: مرور کوتاه جلسه قبل و اهداف روی تخته
- حین جلسه: دانشجو کد را خودش تایپ کند — copy/paste نکند
- بعد از تمرین: ۵ دقیقه Q&A و اشاره به exercises/question.md
- تکلیف خانه: exercise_02.py — deadline هفته بعد
- ارزیابی: حضور در تمرین کلاسی + تحویل exercise_02

---

## منابع تکمیلی

- [typing — Python docs](https://docs.python.org/3/library/typing.html)
- [mypy — static type checker](https://mypy.readthedocs.io/)

### چک‌لیست پایان جلسه

- [ ] annotate param و return
- [ ] list[str] و dict[str, int]
- [ ] Optional و Union
- [ ] TypedDict
- [ ] mypy به‌صورت اختیاری demo شد
- [ ] `int | str` Union syntax

---

## نکات تکمیلی برای مدرس

تأکید کنید type hint **اجبار runtime نیست** — یک test زنده با `add("1","2")` که خطا نمی‌دهد. VS Code/Pylance autocomplete را نشان دهید. برای mypy فقط mention — نصب اختیاری.

### اجرای تمرین‌ها

```bash
python session-19-typing/exercises/exercise_01.py
python session-19-typing/exercises/exercise_02.py
```

نکته: از Python 3.9 به بعد `list[str]` ترجیح دارد بر `List[str]` از typing.

### mapping رایج

| Python | Type Hint |
|--------|-----------|
| `"text"` | `str` |
| `42` | `int` |
| `[1,2]` | `list[int]` |
| `{"a":1}` | `dict[str, int]` |
| `None` | `None` یا `X \| None` |

Type hints قرارداد بین توسعه‌دهندگان هستند — نه runtime validator.

PEP 484 اولین spec رسمی type hints در Python 3.5 بود.

در جلسه ۴۶ Protocol typing پیشرفته‌تر بررسی می‌شود.

مثال‌ها را با `python session-19-typing/examples/01_type_hints.py` اجرا کنید.

---

**جلسه بعدی:** [جلسه ۲۰ — پروژه پیشرفته](../session-20-advanced-project/)
