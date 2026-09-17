# جلسه ۴۵: الگوهای طراحی (Design Patterns) (۱.۵ ساعت)

## 🎯 اهداف یادگیری

پس از این جلسه می‌توانید:
- مفهوم design pattern و زمان استفاده را توضیح دهید
- Singleton را در Python پیاده‌سازی کنید (با احتیاط)
- Factory Method / Simple Factory را بشناسید
- Strategy pattern برای تعویض الگوریتم استفاده کنید
- Observer برای رویداد و notify پیاده کنید
- over-engineering را اجتناب کنید

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع | فعالیت |
|------|--------|--------|
| 0–10 | pattern چیست؟ | GoF، Pythonic |
| 10–30 | Singleton | یک نمونه |
| 30–45 | Factory | ساخت object |
| 45–60 | Strategy | swap algorithm |
| 60–75 | Observer | event notify |
| 75–85 | تمرین | ترکیب patterns |
| 85–90 | جمع‌بندی + تکلیف | question.md |

---

## ۱. Design Pattern چیست؟

**راه‌حل تکرارشونده** برای مشکلات طراحی نرم‌افزار — نه کد copy-paste، بلکه **الگوی فکری**.

کتاب Gang of Four (1994) — 23 الگو. Python سینتکس ساده‌تری دارد — بعضی patternها کمتر لازم‌اند.

---

## ۲. Singleton — یک نمونه

```python
class Logger:
    _instance = None

    def __new__(cls):
        if cls._instance is None:
            cls._instance = super().__new__(cls)
            cls._instance.messages = []
        return cls._instance
```

**هشدار Python:** module-level object اغلب جایگزین بهتر Singleton است:

```python
# logger.py
logger = Logger()  # یک instance در import
```

---

## ۳. Factory — ساخت بدون expose class

```python
def shape_factory(kind: str):
    if kind == "circle":
        return Circle()
    if kind == "square":
        return Square()
    raise ValueError(f"unknown: {kind}")
```

مصرف‌کننده `Circle()` را مستقیم نمی‌سازد — coupling کمتر.

---

## ۴. Strategy — الگوریتم قابل تعویض

```python
class SortStrategy(Protocol):
    def sort(self, data: list) -> list: ...

class QuickSort:
    def sort(self, data): ...

class Sorter:
    def __init__(self, strategy: SortStrategy):
        self.strategy = strategy
```

runtime algorithm عوض می‌شود بدون تغییر Sorter.

---

## ۵. Observer — publish/subscribe

```python
class Subject:
    def __init__(self):
        self._observers = []
    def attach(self, obs): self._observers.append(obs)
    def notify(self, msg):
        for obs in self._observers:
            obs.update(msg)
```

GUI، events، logging — use cases رایج.

---

## ۶. Decorator pattern vs Python decorator

| GoF Decorator | @decorator Python |
|---------------|-------------------|
| class wrapper | function wrapper |
| composition | syntax sugar |

هر دو «رفتار wrap» — implementation متفاوت.

---

## ۷. Repository pattern (مرور)

از جلسات قبل — abstraction روی data access. Factory + Strategy اغلب همراه Repository.

---

## ۸. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_singleton.py](./examples/01_singleton.py) | Singleton Logger |
| [02_factory.py](./examples/02_factory.py) | shape factory |
| [03_strategy.py](./examples/03_strategy.py) | discount strategy |
| [04_observer.py](./examples/04_observer.py) | observer ساده |

---

## ۹. Pythonic alternatives

| Pattern | Python way |
|---------|------------|
| Singleton | module singleton |
| Factory | dict dispatch |
| Strategy | function as arg |
| Null object | None handling |

---

## ۱۰. when NOT to use patterns

- پروژه کوچک — YAGNI
- فقط برای «clean code badge»
- قبل از درک مشکل واقعی

---

## ۱۱. اجرای مثال‌ها

```bash
python session-45-design-patterns/examples/01_singleton.py
python session-45-design-patterns/examples/02_factory.py
python session-45-design-patterns/examples/03_strategy.py
python session-45-design-patterns/examples/04_observer.py
```

---

## ۱۲. اشتباهات رایج

| اشتباه | مشکل |
|--------|------|
| Singleton everywhere | test سخت، global state |
| Factory برای ۲ class | overkill |
| Pattern name dropping | complexity |
| GoF verbatim در Python | unpythonic |

---

## ۱۳. تمرین کلاسی

1. Singleton Logger با log()
2. Factory برای Notification (email, sms)
3. Strategy برای shipping cost

---

## ۱۴. تکلیف

**تکلیف:** [question.md](./exercises/question.md)

پاسخ: [exercise_01.py](./exercises/exercise_01.py) | [exercise_02.py](./exercises/exercise_02.py)

---

## ۱۵. خلاصه

```
Singleton   → یک instance (با احتیاط)
Factory     → ساخت object بدون coupling
Strategy    → swap algorithm
Observer    → notify subscribers
YAGNI       → فقط وقتی لازم
```

---

**جلسه قبل:** [۴۴ — Performance](../session-44-performance-timeit/) | **بعد:** [۴۶ — Protocols](../session-46-protocols-typing/)

---

## ۱۶. Template Method (مرور)

```python
class DataExporter(ABC):
    def export(self, data):
        cleaned = self.clean(data)
        return self.write(cleaned)
    @abstractmethod
    def write(self, cleaned): ...
```

---

## ۱۷. Adapter pattern

```python
class LegacyAPI:
    def fetch_old(self): ...

class APIAdapter:
    def __init__(self, legacy): self.legacy = legacy
    def get(self): return self.legacy.fetch_old()
```

---

## ۱۹. Builder pattern (مرور)

```python
class QueryBuilder:
    def __init__(self):
        self._parts = []
    def select(self, *cols):
        self._parts.append(f"SELECT {','.join(cols)}")
        return self
    def build(self):
        return " ".join(self._parts)
```

---

## ۲۰. Dependency Injection

Strategy و Factory اغلب با DI ترکیب می‌شوند — service constructor dependency می‌گیرد.

---

## ۲۱. anti-pattern: God Object

یک class که همه کار می‌کند — refactor به services.

---

## ۲۲. pattern selection guide

| مشکل | pattern |
|------|---------|
| یک instance | Singleton/module |
| ساخت object | Factory |
| swap algorithm | Strategy |
| data access | Repository |

---

## ۲۳. exercise debrief

بعد از تمرین Observer — فکر کنید چند subscriber واقعاً لازم است؟  
YAGNI: یک listener کافی تا نیاز دوم ثابت شود.

---

## ۲۴. مرور جلسه

Singleton → Factory → Strategy → Observer — چهار الگوی پایه  
که در ۸۰٪ پروژه‌های Python کاربرد دارند.

---

## ۲۳. exercise mapping

| تمرین | pattern |
|-------|---------|
| Config singleton | Singleton |
| notifier_factory | Factory |
| ShippingCalculator | Strategy |
| OrderEvents | Observer |
