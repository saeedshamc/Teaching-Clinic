# جلسه ۴۶: Protocol و Typing پیشرفته (۱.۵ ساعت)

## 🎯 اهداف یادگیری

پس از این جلسه می‌توانید:
- تفاوت ABC (nominal) و Protocol (structural) را توضیح دهید
- `typing.Protocol` برای duck typing static بنویسید
- `@runtime_checkable` را بشناسید
- `TypeVar` و bounded generics استفاده کنید
- `Callable`, `Iterable`, `Sequence` را به کار ببرید
- با mypy/pyright type check کنید (مرور)

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع | فعالیت |
|------|--------|--------|
| 0–10 | مرور typing | hints جلسه ۱۹ |
| 10–30 | Protocol پایه | Writable |
| 30–45 | structural subtyping | بدون inherit |
| 45–60 | TypeVar | generic functions |
| 60–75 | Callable/Iterable | higher-order |
| 75–85 | runtime_checkable | isinstance |
| 85–90 | جمع‌بندی + تکلیف | question.md |

---

## ۱. ABC vs Protocol

| ABC | Protocol |
|-----|----------|
| nominal — باید inherit | structural — فقط shape |
| `class X(ABC)` | `class X(Protocol)` |
| isinstance سخت | duck typing + types |

Python philosophy: «if it walks like a duck...» — Protocol این را formal می‌کند.

---

## ۲. Protocol پایه

```python
from typing import Protocol

class Writable(Protocol):
    def write(self, data: str) -> None: ...

def save(dest: Writable, text: str) -> None:
    dest.write(text)
```

`MemoryFile` نیازی به inherit Writable ندارد — فقط `write` داشته باشد.

---

## ۳. runtime_checkable

```python
from typing import runtime_checkable, Protocol

@runtime_checkable
class Drawable(Protocol):
    def draw(self) -> None: ...

isinstance(obj, Drawable)  # runtime check
```

بدون decorator، isinstance با Protocol کار نمی‌کند.

---

## ۴. TypeVar — generic

```python
from typing import TypeVar

T = TypeVar("T")

def first(items: list[T]) -> T:
    return items[0]
```

type checker می‌داند return همان نوع elements است.

---

## ۵. Bounded TypeVar

```python
from typing import TypeVar

class SupportsLen(Protocol):
    def __len__(self) -> int: ...

T = TypeVar("T", bound=SupportsLen)

def longest(a: T, b: T) -> T:
    return a if len(a) >= len(b) else b
```

---

## ۶. Callable

```python
from typing import Callable

def apply_twice(fn: Callable[[int], int], x: int) -> int:
    return fn(fn(x))
```

---

## ۷. Iterable vs Sequence

| Iterable | Sequence |
|----------|----------|
| فقط iterate | + index، len |
| generator OK | list, tuple |

---

## ۸. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_protocol_basics.py](./examples/01_protocol_basics.py) | Writable |
| [02_structural_typing.py](./examples/02_structural_typing.py) | بدون inherit |
| [03_typevar.py](./examples/03_typevar.py) | generic first |
| [04_callable_iterable.py](./examples/04_callable_iterable.py) | Callable |

---

## ۹. mypy (مرور)

```bash
pip install mypy
mypy mymodule.py
```

Static type checker — Protocol و TypeVar را درک می‌کند.

---

## ۱۰. Protocol در stdlib

- `typing.SupportsInt`
- `collections.abc.Iterable`
- Python 3.8+: many moved to collections.abc

---

## ۱۱. اجرای مثال‌ها

```bash
python session-46-protocols-typing/examples/01_protocol_basics.py
python session-46-protocols-typing/examples/02_structural_typing.py
python session-46-protocols-typing/examples/03_typevar.py
python session-46-protocols-typing/examples/04_callable_iterable.py
```

---

## ۱۲. اشتباهات رایج

| اشتباه | مشکل |
|--------|------|
| Protocol + ABC confuse | دو paradigm |
| isinstance بدون runtime_checkable | TypeError |
| over-annotate | noise |
| ignore mypy errors | false security |

---

## ۱۳. تمرین کلاسی

1. Protocol `Readable` با read()
2. TypeVar برای swap
3. Callable filter

---

## ۱۴. تکلیف

**تکلیف:** [question.md](./exercises/question.md)

پاسخ: [exercise_01.py](./exercises/exercise_01.py) | [exercise_02.py](./exercises/exercise_02.py)

---

## ۱۵. خلاصه

```
Protocol           → structural subtyping
@runtime_checkable → isinstance
TypeVar("T")       → generic
Callable[[A], B]   → function type
ABC = nominal      | Protocol = structural
```

---

**جلسه قبل:** [۴۵ — Design Patterns](../session-45-design-patterns/) | **بعد:** [۴۷ — Security](../session-47-security-basics/)

---

## ۱۶. TypedDict

```python
from typing import TypedDict

class UserDict(TypedDict):
    id: int
    name: str
```

---

## ۱۷. Literal و Final

```python
from typing import Literal, Final

Mode = Literal["r", "w"]
MAX_SIZE: Final = 1024
```

---

## ۱۸. Union و Optional

```python
def find(id: int) -> User | None: ...
# equivalent: Optional[User]
```

---

## ۲۰. Generic Protocol

```python
class Comparable(Protocol):
    def __lt__(self, other: Self) -> bool: ...

def min_of(a: T, b: T) -> T: ...  # T bound=Comparable
```

---

## ۲۱. overload (مرور)

```python
from typing import overload

@overload
def process(x: int) -> int: ...
@overload
def process(x: str) -> str: ...
```

---

## ۲۲. pyright vs mypy

هر دو static checker — pyright در VS Code/Pylance integrated.

---

## ۲۳. gradual typing

Python dynamic است — type hints اختیاری و gradual.

---

## ۲۴. exercise: Readable Protocol

پیاده‌سازی `FileReader` و `HttpReader` بدون inherit —  
service فقط `Readable` می‌خواهد.

---

## ۲۵. ABC + Protocol together

```python
class Repository(ABC):
    @abstractmethod
    def save(self, item): ...

class WritableRepo(Repository, Protocol):
    ...
```

هر دو paradigm در یک codebase — use case dependent.

**پیشنهاد:** جلسه ۳۸ (ABC) + این جلسه را کنار هم مرور کنید.

Typed code = fewer runtime surprises + IDE autocomplete بهتر.

---

## ۲۴. exercise hints

- `Readable` — دو class بدون inherit مشترک
- `transform` — Callable + TypeVar
- `pair_first` — generic tuple access
