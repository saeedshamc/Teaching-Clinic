# جلسه ۳۸: کلاس‌های انتزاعی (ABC) — ۱.۵ ساعت

## 🎯 اهداف یادگیری

- `ABC` و `@abstractmethod`
- contract برای subclassها
- تفاوت ABC، interface، mixin
- `abstractproperty` (legacy) و `@property` + `@abstractmethod`
- `NotImplementedError` vs abstract
- الگوهای real-world: plugin, repository, strategy

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | polymorphism و contract |
| 10–25 | ABC پایه |
| 25–40 | چند abstract method |
| 40–55 | concrete implementations |
| 55–70 | protocol vs ABC |
| 70–85 | تمرین |
| 85–90 | جمع‌بندی |

---

## ۱. مشکل بدون ABC

```python
class Shape:
    def area(self):
        raise NotImplementedError

class Circle(Shape):
    pass  # فراموش area — خطا فقط runtime!
```

ABC در **instantiate** خطا می‌دهد.

---

## ۲. ABC پایه

```python
from abc import ABC, abstractmethod

class Shape(ABC):
    @abstractmethod
    def area(self) -> float:
        pass

class Square(Shape):
    def __init__(self, side: float):
        self.side = side

    def area(self) -> float:
        return self.side ** 2

# Shape()  # TypeError
sq = Square(4)
print(sq.area())
```

---

## ۳. چند abstract method

```python
class Animal(ABC):
    @abstractmethod
    def speak(self) -> str:
        pass

    @abstractmethod
    def move(self) -> str:
        pass
```

---

## ۴. abstract + concrete methods

```python
class BaseRepository(ABC):
    @abstractmethod
    def get(self, id: int):
        pass

    def exists(self, id: int) -> bool:
        try:
            self.get(id)
            return True
        except KeyError:
            return False
```

---

## ۵. @abstractmethod روی property

```python
class Config(ABC):
    @property
    @abstractmethod
    def name(self) -> str:
        pass
```

---

## ۶. register (virtual subclass)

```python
from abc import ABC

class MyABC(ABC):
    pass

class External:
    pass

MyABC.register(External)  # isinstance(External(), MyABC) → True
```

---

## ۷. ABC vs Protocol (typing)

| | ABC | Protocol |
|---|-----|----------|
| runtime check | بله | structural (3.8+) |
| inheritance | explicit | duck typing |
| use | framework contract | type hints |

```python
from typing import Protocol

class Drawable(Protocol):
    def draw(self) -> None: ...
```

---

## ۸. Strategy pattern

```python
class PaymentStrategy(ABC):
    @abstractmethod
    def pay(self, amount: float) -> bool:
        pass

class CreditCard(PaymentStrategy):
    def pay(self, amount: float) -> bool:
        print(f"پرداخت {amount} با کارت")
        return True

class Checkout:
    def __init__(self, strategy: PaymentStrategy):
        self.strategy = strategy

    def process(self, amount: float):
        return self.strategy.pay(amount)
```

---

## ۹. Plugin pattern

```python
class Plugin(ABC):
    @abstractmethod
    def name(self) -> str:
        pass

    @abstractmethod
    def run(self) -> None:
        pass

PLUGINS: list[type[Plugin]] = []

def register(cls):
    PLUGINS.append(cls)
    return cls
```

---

## ۱۰. NotImplementedError

```python
def method(self):
    raise NotImplementedError("subclass must implement")
```

- ABC: جلوگیری از instantiate کلاس ناقص
- NotImplementedError: optional override یا template method

---

## ۱۱. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_abc_basics.py](./examples/01_abc_basics.py) | Shape ABC |
| [02_animal_hierarchy.py](./examples/02_animal_hierarchy.py) | Animal |
| [03_strategy_payment.py](./examples/03_strategy_payment.py) | Strategy |
| [04_repository_pattern.py](./examples/04_repository_pattern.py) | Repository |

---

## ۱۲. اشتباهات رایج

| اشتباه | مشکل |
|--------|------|
| ABC بدون abstractmethod | subclass اجباری نیست |
| فراموش implement | TypeError at init |
| ABC برای همه کلاس‌ها | over-engineering |
| mix ABC + multiple inheritance | MRO پیچیده |

---

## ۱۳. تمرین کلاسی

1. Shape → Square, Circle
2. Animal → Dog, Cat
3. PaymentStrategy → two impl
4. سعی instantiate ABC — TypeError

**تکلیف:** [question.md](./exercises/question.md)

---

## ۱۴. Template Method

```python
class DataExporter(ABC):
    def export(self, data):
        formatted = self.format(data)
        self.write(formatted)

    @abstractmethod
    def format(self, data) -> str:
        pass

    @abstractmethod
    def write(self, content: str) -> None:
        pass
```

---

## ۱۵. isinstance با ABC

```python
isinstance(sq, Shape)  # True
```

---

## ۱۶. __subclasshook__

```python
class MyABC(ABC):
    @classmethod
    def __subclasshook__(cls, subclass):
        if cls is MyABC and hasattr(subclass, "required_method"):
            return True
        return NotImplemented
```

---

## ۱۷. multiple inheritance با ABC

```python
class Readable(ABC):
    @abstractmethod
    def read(self): ...

class Writable(ABC):
    @abstractmethod
    def write(self, data): ...

class ReadWrite(Readable, Writable):
    ...
```

---

## ۱۸. سوالات کلاسی

- ABC vs NotImplementedError؟
- Strategy pattern کجا مفید است؟
- Protocol چه زمانی جای ABC؟

---

## 📚 مطالعه تکمیلی

- [abc — Python docs](https://docs.python.org/3/library/abc.html)
- PEP 3119 — Abstract Base Classes

---

**جلسه قبل:** [۳۷ — Env Config](../session-37-env-config/) | **بعد:** [۳۹ — Properties](../session-39-properties/)
