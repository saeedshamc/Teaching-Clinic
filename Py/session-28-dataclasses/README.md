# جلسه ۲۸: Dataclassها — ۱.۵ ساعت

## 🎯 اهداف یادگیری

- ساخت کلاس داده با `@dataclass`
- فیلدهای پیش‌فرض، `field()` و factory
- `frozen`, `slots`, `order`
- `__post_init__` برای validation
- تبدیل به dict/json با `asdict`
- مقایسه با NamedTuple و کلاس معمولی

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | مشکل کلاس داده سنتی |
| 10–25 | @dataclass پایه |
| 25–40 | field و default |
| 40–55 | frozen و validation |
| 55–70 | asdict و nested |
| 70–85 | تمرین |
| 85–90 | جمع‌بندی |

---

## ۱. مشکل کلاس سنتی

```python
class User:
    def __init__(self, name: str, age: int):
        self.name = name
        self.age = age
    def __repr__(self):
        return f"User(name={self.name!r}, age={self.age})"
    def __eq__(self, other):
        ...
```

کد تکراری! dataclass این boilerplate را خودکار می‌سازد.

---

## ۲. dataclass پایه

```python
from dataclasses import dataclass

@dataclass
class User:
    name: str
    age: int

user = User("سعید", 30)
print(user)           # User(name='سعید', age=30)
print(user.name)
user.age = 31
```

متدهای خودکار: `__init__`, `__repr__`, `__eq__`

---

## ۳. field()

```python
from dataclasses import dataclass, field

@dataclass
class Product:
    name: str
    price: float
    tags: list[str] = field(default_factory=list)

p = Product("لپ‌تاپ", 50000000)
p.tags.append("الکترونیک")
```

**مهم:** برای mutable default از `default_factory` استفاده کنید، نه `=[]`.

---

## ۴. فیلدهای با/بدون پیش‌فرض

```python
@dataclass
class Order:
    id: int
    customer: str
    total: float = 0.0
    paid: bool = False
```

فیلدهای بدون default باید قبل از فیلدهای با default بیایند.

---

## ۵. frozen — immutable

```python
@dataclass(frozen=True)
class Point:
    x: float
    y: float

p = Point(1, 2)
# p.x = 3  # FrozenInstanceError
```

---

## ۶. order — مقایسه

```python
@dataclass(order=True)
class Score:
    value: int
    name: str

sorted([Score(90, "علی"), Score(85, "مریم")])
```

---

## ۷. __post_init__

```python
@dataclass
class Rectangle:
    width: float
    height: float

    def __post_init__(self):
        if self.width <= 0 or self.height <= 0:
            raise ValueError("ابعاد باید مثبت باشد")

    @property
    def area(self) -> float:
        return self.width * self.height
```

---

## ۸. asdict و astuple

```python
from dataclasses import asdict, astuple

user = User("سعید", 30)
print(asdict(user))    # {'name': 'سعید', 'age': 30}
print(astuple(user))   # ('سعید', 30)
```

---

## ۹. slots (Python 3.10+)

```python
@dataclass(slots=True)
class LightUser:
    name: str
    age: int
```

حافظه کمتر، attribute اضافی ممنوع.

---

## ۱۰. nested dataclass

```python
@dataclass
class Address:
    city: str
    street: str

@dataclass
class Customer:
    name: str
    address: Address

c = Customer("سعید", Address("تهران", "ولیعصر"))
print(asdict(c))
```

---

## ۱۱. field metadata

```python
@dataclass
class Config:
    host: str = field(default="localhost", metadata={"env": "APP_HOST"})
    port: int = field(default=8000, metadata={"env": "APP_PORT"})
```

---

## ۱۲. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_dataclass_basics.py](./examples/01_dataclass_basics.py) | پایه |
| [02_fields_defaults.py](./examples/02_fields_defaults.py) | field و factory |
| [03_frozen_validation.py](./examples/03_frozen_validation.py) | frozen و post_init |
| [04_nested_serialization.py](./examples/04_nested_serialization.py) | asdict و nested |

---

## ۱۳. dataclass vs NamedTuple

| | dataclass | NamedTuple |
|---|-----------|------------|
| mutable | بله (مگر frozen) | خیر |
| inheritance | بله | محدود |
| default | field | محدودتر |
| use case | entity/model | record سبک |

---

## ۱۴. اشتباهات رایج

| اشتباه | مشکل |
|--------|------|
| `tags: list = []` | shared mutable default |
| فیلد default قبل از required | SyntaxError |
| فراموش type hint | کمتر IDE help |
| frozen + mutable field | خطا در append |

---

## ۱۵. تمرین کلاسی

1. dataclass `Book` با title, author, pages
2. `Product` با tags و default_factory
3. `Point` frozen با distance از origin
4. `Customer` با Address تو در تو

**تکلیف:** [question.md](./exercises/question.md)

---

## ۱۶. dataclass + JSON

```python
import json
from dataclasses import asdict

data = json.dumps(asdict(user), ensure_ascii=False, indent=2)
```

---

## ۱۷. field(init=False)

```python
@dataclass
class User:
    name: str
    id: int = field(init=False)

    def __post_init__(self):
        self.id = hash(self.name) % 10000
```

---

## ۱۸. kw_only (Python 3.10+)

```python
@dataclass(kw_only=True)
class Config:
    host: str = "localhost"
    port: int = 8000

# Config("x")  # TypeError — فقط keyword
Config(host="x")
```

---

## ۱۹. سوالات کلاسی

- تفاوت dataclass و dict چیست؟
- چرا `frozen=True` برای value object؟
- `__post_init__` چه زمانی لازم است؟

---

## 📚 مطالعه تکمیلی

- [dataclasses — Python docs](https://docs.python.org/3/library/dataclasses.html)
- PEP 557

---

**جلسه قبل:** [۲۷ — Logging](../session-27-logging/) | **بعد:** [۲۹ — Enums](../session-29-enums/)
