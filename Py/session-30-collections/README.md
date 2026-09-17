# جلسه ۳۰: ماژول Collections — ۱.۵ ساعت

## 🎯 اهداف یادگیری

- `Counter` — شمارش و پرتکرارترین
- `defaultdict` — مقدار پیش‌فرض خودکار
- `namedtuple` — record سبک immutable
- `deque` — صف دوطرفه کارآمد
- `OrderedDict` — (تاریخی؛ dict 3.7+ ordered)
- `ChainMap` — زنجیره دیکشنری
- الگوهای real-world: word count, grouping, sliding window

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | چرا collections؟ |
| 10–25 | Counter |
| 25–40 | defaultdict |
| 40–55 | namedtuple |
| 55–70 | deque |
| 70–85 | تمرین |
| 85–90 | جمع‌بندی |

---

## ۱. Counter

```python
from collections import Counter

words = ["سیب", "موز", "سیب", " پرتقال", "سیب", "موز"]
counts = Counter(words)
print(counts)                    # Counter({'سیب': 3, 'موز': 2, ...})
print(counts["سیب"])             # 3
print(counts.most_common(2))     # [('سیب', 3), ('موز', 2)]
```

### عملیات

```python
c1 = Counter(a=3, b=1)
c2 = Counter(a=1, b=2)
c1 + c2   # Counter({'a': 4, 'b': 3})
c1 - c2   # فقط مقادیر مثبت
c1.update(["a", "a", "c"])
```

---

## ۲. defaultdict

```python
from collections import defaultdict

# list factory — گروه‌بندی
groups = defaultdict(list)
for name, dept in [("سعید", "IT"), ("مریم", "HR"), ("علی", "IT")]:
    groups[dept].append(name)
print(dict(groups))

# int factory — شمارش
scores = defaultdict(int)
for word in "hello world".split():
    scores[word] += 1
```

---

## ۳. namedtuple

```python
from collections import namedtuple

Point = namedtuple("Point", ["x", "y"])
p = Point(3, 4)
print(p.x, p.y)
print(p._asdict())

# replace — immutable update
p2 = p._replace(x=10)
```

---

## ۴. deque

```python
from collections import deque

q = deque(maxlen=3)
q.append(1)
q.append(2)
q.append(3)
q.append(4)  # 1 حذف می‌شود
print(q)

d = deque([1, 2, 3])
d.appendleft(0)
print(d.popleft())
```

---

## ۵. Counter برای متن

```python
from collections import Counter
import re

text = "سلام سلام دنیا"
tokens = re.findall(r"\w+", text)
freq = Counter(tokens)
print(freq.most_common(1)[0])
```

---

## ۶. defaultdict nested

```python
tree = defaultdict(lambda: defaultdict(list))
tree["2024"]["فروردین"].append("گزارش Q1")
```

---

## ۷. ChainMap

```python
from collections import ChainMap

defaults = {"theme": "light", "lang": "fa"}
user = {"theme": "dark"}
config = ChainMap(user, defaults)
print(config["theme"])   # dark
print(config["lang"])    # fa
```

---

## ۸. OrderedDict (مرجع تاریخی)

از Python 3.7+ dict insertion order را حفظ می‌کند. OrderedDict هنوز برای `move_to_end` مفید است.

---

## ۹. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_counter.py](./examples/01_counter.py) | Counter |
| [02_defaultdict.py](./examples/02_defaultdict.py) | grouping |
| [03_namedtuple_deque.py](./examples/03_namedtuple_deque.py) | namedtuple + deque |
| [04_text_analysis.py](./examples/04_text_analysis.py) | تحلیل متن real-world |

---

## ۱۰. Counter vs dict

| | Counter | dict + get |
|---|---------|------------|
| missing key | 0 | KeyError یا get |
| most_common | built-in | manual sort |
| arithmetic | +, -, & | manual |

---

## ۱۱. اشتباهات رایج

| اشتباه | مشکل |
|--------|------|
| `defaultdict([])` | TypeError — callable بدهید |
| mutate namedtuple | AttributeError |
| Counter negative values | بعد از `-` حذف می‌شوند |

---

## ۱۲. تمرین کلاسی

1. Counter روی لیست میوه‌ها
2. defaultdict برای گروه‌بندی دانشجو بر اساس کلاس
3. namedtuple `Book(title, author)`
4. deque با maxlen=5 برای آخرین ۵ log

**تکلیف:** [question.md](./exercises/question.md)

---

## ۱۳. UserDict/UserList (اختیاری)

برای subclass کردن dict/list با رفتار سفارشی:

```python
from collections import UserDict

class SortedDict(UserDict):
    def __iter__(self):
        return iter(sorted(self.data))
```

---

## ۱۴. Performance

- deque: O(1) append/pop از هر دو طرف
- Counter: بهینه برای شمارش
- namedtuple: سبک‌تر از class معمولی

---

## ۱۵. OrderedDict.move_to_end

```python
from collections import OrderedDict

cache = OrderedDict()
cache["a"] = 1
cache["b"] = 2
cache.move_to_end("a")  # LRU: a تازه‌تر
```

---

## ۱۶. Counter.elements

```python
c = Counter(a=2, b=3)
list(c.elements())  # ['a','a','b','b','b']
```

---

## ۱۷. defaultdict در graph

```python
graph = defaultdict(list)
graph[1].append(2)
graph[1].append(3)
graph[2].append(4)
```

---

## ۱۸. سوالات کلاسی

- Counter vs dict با get چه مزیتی دارد؟
- چرا deque برای BFS بهتر از list است؟
- namedtuple چه زمانی به dataclass ترجیح دارد؟

---

## ۱۹. تمرین گروهی پیشنهادی

| گروه | task |
|------|------|
| A | Counter روی فایل log |
| B | defaultdict graph ساده |
| C | deque برای undo stack |

---

## ۲۰. جمع‌بندی جلسه

collections برای **شمارش، گروه‌بندی و ساختار سبک** — قبل از نوشتن dict دستی، Counter/defaultdict را بررسی کنید.

**تکلیف خانه:** [question.md](./exercises/question.md) — حداقل تمرین ۵ و ۶.

### چک‌لیست پایان جلسه

- [ ] Counter روی لیست اجرا شد
- [ ] defaultdict برای grouping
- [ ] namedtuple یا deque امتحان شد
- [ ] تمرین کلاسی تحویل شد

**زمان باقی‌مانده:** Q&A و مرور Counter.most_common و defaultdict factory.

**جلسه بعد:** argparse برای ساخت CLI.

---

## 📚 مطالعه تکمیلی

- [collections — Python docs](https://docs.python.org/3/library/collections.html)

---

**جلسه قبل:** [۲۹ — Enums](../session-29-enums/) | **بعد:** [۳۱ — Argparse](../session-31-argparse/)
