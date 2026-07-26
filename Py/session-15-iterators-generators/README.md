# جلسه ۱۵: Iterator و Generator

## اهداف یادگیری (Learning Objectives)

پس از این جلسه می‌توانید:
- تفاوت **iterable** و **iterator** را توضیح دهید
- از `iter()` و `next()` برای پیمایش دستی استفاده کنید
- generator با `yield` بسازید و مزایای آن را بدانید
- generator expression را از list comprehension تشخیص دهید
- بدانید چرا generator برای داده‌های بزرگ حافظه‌کارآمدتر است
- از `StopIteration` و پروتکل iterator آشنا شوید

---

## Iterable و Iterator

**Iterable** — شیئی که می‌توان روی آن حلقه `for` زد (لیست، رشته، tuple، dict).

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

---

## Generator چیست؟

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

## Generator Expression

مثل list comprehension ولی با پرانتز — **generator** برمی‌گرداند نه list:

```python
squares_list = [n * n for n in range(5)]      # list — همه در حافظه
squares_gen = (n * n for n in range(5))       # generator — lazy
```

---

## yield از چند مقدار

```python
def even_numbers(limit):
    number = 0
    while number <= limit:
        yield number
        number += 2

for n in even_numbers(10):
    print(n)  # 0, 2, 4, 6, 8, 10
```

---

## فایل‌های این جلسه

| فایل | توضیح |
|------|-------|
| [examples/01_generator.py](./examples/01_generator.py) | generator با yield |
| [examples/02_iterator_demo.py](./examples/02_iterator_demo.py) | iter، next و generator expression |
| [exercises/exercise_01.py](./exercises/exercise_01.py) | تمرین‌های این جلسه |

---

## توضیح کدها

### 📄 `01_generator.py`

```python
def count_up(limit):
    current = 1
    while current <= limit:
        yield current
        current += 1
```

**خط به خط:**
- `yield current` — مقدار را برمی‌گرداند ولی تابع تمام نمی‌شود
- دفعه بعد اجرا از خط بعد `yield` ادامه می‌یابد
- `current += 1` — شمارنده جلو می‌رود

---

### 📄 `02_iterator_demo.py`

```python
it = iter([10, 20, 30])
print(next(it))
```

**خط به خط:**
- `iter(...)` — iterator از iterable می‌سازد
- `next(it)` — عنصر بعدی؛ در پایان `StopIteration`
- `(x for x in ...)` — generator expression

---

## نکات رایج اشتباهات (Common Pitfalls)

### ❌ اشتباه ۱: iterator را دوبار مصرف کردن
```python
gen = (n for n in range(3))
list(gen)  # [0, 1, 2]
list(gen)  # [] — iterator تمام شده!
```

### ❌ اشتباه ۲: yield و return اشتباه
```python
def bad():
    yield 1
    return 2  # return فقط generator را تمام می‌کند؛ 2 به for نمی‌رسد
```

### ❌ اشتباه ۳: list بزرگ وقتی generator کافی است
```python
# ❌ میلیون عنصر در حافظه
big = [x * 2 for x in range(1_000_000)]

# ✅ lazy
big = (x * 2 for x in range(1_000_000))
```

---

## بهترین شیوه‌ها (Best Practices)

- برای دنباله‌های بزرگ یا بی‌نهایت از generator استفاده کنید
- generator expression برای pipeline داده (map/filter زنجیره‌ای)
- iterator یک‌بار مصرف است — برای چند بار، list بسازید یا generator جدید
- نام generator را فعل‌گونه بگذارید: `count_up`، `read_lines`

---

## خلاصه جلسه

در این جلسه یاد گرفتید:
- ✅ iterable → `for`؛ iterator → `next()`
- ✅ generator با `yield` — lazy و کم‌حافظه
- ✅ generator expression: `(expr for x in source)`
- ✅ iterator یک‌بار مصرف می‌شود
- ✅ مناسب برای فایل بزرگ و جریان داده

---

## تمرین‌ها

### تمرین ۱ (ساده): generator اعداد زوج
تابع `even_numbers(limit)` بنویسید که 0, 2, 4, ... تا limit تولید کند.

### تمرین ۲ (متوسط): generator کاراکترها
تابع `char_stream(text)` بنویسید که هر کاراکتر رشته را یکی‌یکی yield کند.

### تمرین ۳ (پیشرفته): generator expression
با generator expression فقط مربع اعداد زوج 0 تا 10 را بگیرید و در list تبدیل کنید.

راه‌حل‌ها در [exercises/exercise_01.py](./exercises/exercise_01.py) موجود است.

---

**جلسه بعدی:** [جلسه ۱۶ — محیط مجازی و بسته‌بندی](../session-16-virtualenv-packaging/)
