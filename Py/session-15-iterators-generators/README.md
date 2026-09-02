# جلسه ۱۵: Iterator و Generator (۱.۵ ساعت)

## ۰. مرور جلسه قبل

در جلسه ۱۴ decoratorها را یاد گرفتید — توابع first-class. Generator هم تابع است که `yield` می‌کند. Comprehension `(x for x in ...)` همان generator expression است.

---

## 🎯 اهداف یادگیری

پس از این جلسه می‌توانید:
- تفاوت **iterable** و **iterator** را توضیح دهید
- از `iter()` و `next()` برای پیمایش دستی استفاده کنید
- generator با `yield` بسازید و مزایای آن را بدانید
- generator expression را از list comprehension تشخیص دهید
- بدانید چرا generator برای داده‌های بزرگ حافظه‌کارآمدتر است
- از `StopIteration` و پروتکل iterator آشنا شوید
- generator برای خواندن فایل خط‌به‌خط و pipeline داده بنویسید

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع | فعالیت |
|------|--------|--------|
| 0–10 | مرور | حلقه for — پشت صحنه iterator |
| 10–25 | Iterable vs Iterator | iter، next، StopIteration |
| 25–45 | Generator با yield | count_up، fibonacci |
| 45–60 | Generator expression | `(x for x in ...)` vs `[x for x in ...]` |
| 60–75 | کاربرد واقعی | خواندن فایل، pipeline |
| 75–85 | تمرین کلاسی | even_numbers، char_stream |
| 85–90 | جمع‌بندی | اشتباهات + تکلیف |

---

## ۱. Iterable و Iterator

**Iterable** — شیئی که می‌توان روی آن حلقه `for` زد (لیست، رشته، tuple، dict، فایل).

**Iterator** — شیئی که با `next()` یک عنصر در هر بار برمی‌گرداند.

**تشبیه:** iterable مثل **کتاب** است؛ iterator مثل **نشانگر صفحه** که یک صفحه در هر بار می‌خواند.

```python
numbers = [1, 2, 3]       # iterable
it = iter(numbers)        # iterator
print(next(it))           # 1
print(next(it))           # 2
print(next(it))           # 3
# next(it)                # StopIteration — تمام شد
```

### حلقه for چه می‌کند؟

```python
for item in [1, 2, 3]:
    print(item)
```

معادل:
```python
it = iter([1, 2, 3])
while True:
    try:
        item = next(it)
        print(item)
    except StopIteration:
        break
```

---

## ۲. Generator چیست؟

Generator تابعی است که به‌جای `return` از **`yield`** استفاده می‌کند. هر `yield` یک مقدار تولید می‌کند و **اجرا را متوقف** می‌کند — دفعه بعد از همان نقطه ادامه می‌یابد.

```python
def count_up(limit):
    current = 1
    while current <= limit:
        yield current
        current += 1

for value in count_up(5):
    print(value)
```

**مزایا:**
- حافظه کم — همه مقادیر یکجا ساخته نمی‌شوند
- lazy evaluation — فقط وقتی نیاز است تولید می‌شود
- مناسب برای جریان‌های بی‌نهایت یا فایل‌های بزرگ

---

## ۳. Generator Expression

مثل list comprehension ولی با پرانتز — **generator** برمی‌گرداند نه list:

```python
squares_list = [n * n for n in range(5)]      # list — همه در حافظه
squares_gen = (n * n for n in range(5))       # generator — lazy
```

---

## ۴. yield from — واگذاری به generator دیگر

```python
def chain_generators():
    yield from range(3)
    yield from ["a", "b"]
    yield from (x * 2 for x in range(2))

print(list(chain_generators()))  # [0, 1, 2, 'a', 'b', 0, 2]
```

---

## ۵. Generator بی‌نهایت

```python
def infinite_counter(start=0):
    n = start
    while True:
        yield n
        n += 1

counter = infinite_counter(100)
print(next(counter))  # 100
print(next(counter))  # 101
# فقط n تا next بگیرید — بی‌نهایت است!
```

---

## ۶. مثال واقعی — خواندن فایل خط‌به‌خط

```python
def read_lines(path):
    with open(path, encoding="utf-8") as f:
        for line in f:
            yield line.strip()

# فقط یک خط در حافظه — مناسب فایل گیگابایتی
for line in read_lines("big.log"):
    if "ERROR" in line:
        print(line)
```

---

## ۷. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_generator.py](./examples/01_generator.py) | generator با yield |
| [02_iterator_demo.py](./examples/02_iterator_demo.py) | iter، next و generator expression |
| [03_yield_from.py](./examples/03_yield_from.py) | yield from و generator بی‌نهایت |
| [04_file_pipeline.py](./examples/04_file_pipeline.py) | pipeline داده با generator |

---

## ۸. اشتباهات رایج (Pitfalls)

### ❌ iterator را دوبار مصرف کردن
```python
gen = (n for n in range(3))
list(gen)  # [0, 1, 2]
list(gen)  # [] — iterator تمام شده!
```

### ❌ yield و return اشتباه
```python
def bad():
    yield 1
    return 2  # return فقط generator را تمام می‌کند؛ 2 به for نمی‌رسد
```

### ❌ list بزرگ وقتی generator کافی است
```python
# ❌ میلیون عنصر در حافظه
big = [x * 2 for x in range(1_000_000)]

# ✅ lazy
big = (x * 2 for x in range(1_000_000))
```

---

## ۹. بهترین شیوه‌ها

- برای دنباله‌های بزرگ یا بی‌نهایت از generator استفاده کنید
- generator expression برای pipeline داده (map/filter زنجیره‌ای)
- iterator یک‌بار مصرف است — برای چند بار، list بسازید یا generator جدید
- نام generator را فعل‌گونه بگذارید: `count_up`، `read_lines`

---

## ۱۰. خلاصه جلسه

- ✅ iterable → `for`؛ iterator → `next()`
- ✅ generator با `yield` — lazy و کم‌حافظه
- ✅ generator expression: `(expr for x in source)`
- ✅ iterator یک‌بار مصرف می‌شود
- ✅ مناسب برای فایل بزرگ و جریان داده

---

## ۱۱. تکلیف خانه

### ساده: generator اعداد زوج
تابع `even_numbers(limit)` — 0, 2, 4, ... تا limit.

### متوسط: char_stream
تابع `char_stream(text)` — هر کاراکتر را yield کند.

### پیشرفته: pipeline
generator expression برای مربع اعداد زوج 0 تا 20، سپس فقط بزرگ‌تر از 100.

راهنما: [exercises/question.md](./exercises/question.md)  
پاسخ: [exercise_01.py](./exercises/exercise_01.py) | [exercise_02.py](./exercises/exercise_02.py)

---

## ۱۲. توضیح فایل‌های مثال

### 📄 `01_generator.py`
- `count_up` — شمارنده با yield؛ state بین فراخوانی‌ها حفظ می‌شود
- `even_numbers` — الگوی step=2
- `fibonacci` — دو متغیر state (`a`, `b`) در generator

### 📄 `02_iterator_demo.py`
- `iter(numbers)` — ساخت iterator از list
- `next(it)` — عنصر بعدی تا `StopIteration`
- `(n for n in range(5))` — generator expression

### 📄 `03_yield_from.py`
- `yield from sub_range(...)` — واگذاری به generator فرعی
- `infinite_counter` — حلقه بی‌نهایت با yield

### 📄 `04_file_pipeline.py`
- pipeline: `read_lines` → `parse_csv_lines` → `filter_adults`
- هر مرحله generator به بعدی — بدون list میانی بزرگ

---

## ۱۳. سوالات کلاسی

1. تفاوت `[x for x in range(5)]` و `(x for x in range(5))` چیست؟
2. چرا `list(gen)` دوبار روی یک generator خالی برمی‌گرداند؟
3. `yield` و `return` در generator چه تفاوتی دارند؟

---

## ۱۴. تمرین کلاسی تفصیلی (۳۰ دقیقه)

**بخش A:** generator `count_down(n)` از n تا 1  
**بخش B:** با generator expression sum مربع‌های زوج 0-100  
**بخش C:** مقایسه حافظه list vs generator برای range(1_000_000)

---

## ۱۵. راهنمای مدرس

- **دمو:** `next(gen)` روی generator تا StopIteration
- **Compare:** `sys.getsizeof(list)` vs generator object
- **زمان‌بندی:** yield 25min | iter/next 15min | pipeline 20min
- **ارتباط:** generator expression در comprehension جلسه ۱۳
- **خانه:** exercise_02 — filter_by_length

---

## ۱۶. منابع تکمیلی

- [Python docs — Generators](https://docs.python.org/3/howto/functional.html#generators)
- [PEP 255 — Simple Generators](https://peps.python.org/pep-0255/)

### چک‌لیست پایان جلسه

- [ ] iter/next دستی روی list
- [ ] generator با yield نوشته شد
- [ ] generator expression vs list comp
- [ ] exercise_01 اجرا شد
- [ ] exercise_02 تکلیف داده شد

---

**جلسه بعدی:** [جلسه ۱۶ — محیط مجازی و بسته‌بندی](../session-16-virtualenv-packaging/)
