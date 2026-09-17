# جلسه ۲۲: Lambda و توابع تابعی (۱.۵ ساعت)

## ۰. مرور

جلسه ۱۳ comprehension — `[f(x) for x in items]` اغلب جایگزین `map` است. lambda برای callback کوتاه در `sorted(key=...)` عالی است.

---

## 🎯 اهداف یادگیری

پس از این جلسه می‌توانید:
- تابع ناشناس `lambda` را بنویسید و محدودیت‌های آن را بدانید
- از `map`، `filter` و `reduce` برای تبدیل داده استفاده کنید
- `sorted` با `key=lambda` را به‌کار ببرید
- تفاوت lambda و `def` معمولی را تشخیص دهید
- بدانید چه زمانی list comprehension جایگزین بهتری است
- از `functools.reduce` و `operator` module آشنا شوید

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع | فعالیت |
|------|--------|--------|
| 0–10 | مرور | توابع first-class — جلسه ۶ |
| 10–25 | lambda | syntax، محدودیت یک expression |
| 25–40 | map و filter | تبدیل و فیلتر |
| 40–55 | sorted با key | مرتب‌سازی سفارشی |
| 55–70 | reduce | جمع تجمعی |
| 70–85 | تمرین | pipeline داده |
| 85–90 | جمع‌بندی + تکلیف |

---

## ۱. lambda چیست؟

`lambda` تابع **کوتاه و بدون نام** است — فقط یک expression:

```python
square = lambda x: x * x
print(square(5))  # 25

add = lambda a, b: a + b
print(add(2, 3))  # 5
```

**ساختار:** `lambda پارامترها: expression`

---

## ۲. محدودیت‌های lambda

- فقط **یک expression** — نه statement (if block، for، while)
- نه docstring
- برای منطق پیچیده از `def` استفاده کنید

```python
# ✅ lambda ساده
is_even = lambda n: n % 2 == 0

# ❌ lambda پیچیده — از def استفاده کنید
# lambda n: (print(n), n*2)[1]  # anti-pattern
```

---

## ۳. map — اعمال تابع روی هر عنصر

```python
numbers = [1, 2, 3, 4, 5]
doubled = list(map(lambda n: n * 2, numbers))
print(doubled)  # [2, 4, 6, 8, 10]

# معادل comprehension
doubled2 = [n * 2 for n in numbers]
```

---

## ۴. filter — فیلتر عناصر

```python
numbers = [1, 2, 3, 4, 5, 6]
evens = list(filter(lambda n: n % 2 == 0, numbers))
print(evens)  # [2, 4, 6]

# معادل comprehension
evens2 = [n for n in numbers if n % 2 == 0]
```

---

## ۵. sorted با key

```python
students = [
    {"name": "علی", "score": 18},
    {"name": "مریم", "score": 15},
    {"name": "رضا", "score": 20},
]

by_score = sorted(students, key=lambda s: s["score"], reverse=True)
for s in by_score:
    print(s["name"], s["score"])
```

---

## ۶. reduce — تجمیع

```python
from functools import reduce

numbers = [1, 2, 3, 4, 5]
total = reduce(lambda acc, n: acc + n, numbers)
print(total)  # 15

# معادل sum(numbers)
```

---

## ۷. operator module — جایگزین lambda

```python
from operator import add, itemgetter

print(reduce(add, [1, 2, 3, 4]))  # 10
by_name = sorted(students, key=itemgetter("name"))
```

---

## ۸. map/filter vs comprehension

| روش | مزیت |
|-----|------|
| comprehension | خواناتر در Python |
| map/filter | سبک functional، lazy در Py3 |

**توصیه Pythonic:** comprehension برای transform/filter ساده.

---

## ۹. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_lambda_map_filter.py](./examples/01_lambda_map_filter.py) | پایه |
| [02_sorted_key.py](./examples/02_sorted_key.py) | مرتب‌سازی |
| [03_reduce_operator.py](./examples/03_reduce_operator.py) | reduce |
| [04_functional_pipeline.py](./examples/04_functional_pipeline.py) | pipeline |

---

## ۱۰. اشتباهات رایج (Pitfalls)

### ❌ lambda در حلقه با متغیر loop
```python
funcs = [lambda x: x * i for i in range(3)]
funcs[0](10)  # 20 نه 0! — i آخرین مقدار را دارد
# ✅ default arg: lambda x, i=i: x * i
```

### ❌ list(map(...)) وقتی generator کافی است
```python
# map در Py3 lazy است
m = map(lambda x: x*2, range(1000000))  # generator
```

---

## ۱۱. خلاصه

- ✅ lambda = تابع یک‌خطی
- ✅ map/filter/reduce برای functional style
- ✅ sorted(..., key=lambda)
- ✅ comprehension اغلب خواناتر

---

## ۱۲. تکلیف

راهنما: [exercises/question.md](./exercises/question.md)  
پاسخ: [exercise_01.py](./exercises/exercise_01.py) | [exercise_02.py](./exercise_02.py)

---

## ۱۳. functools.partial — جایگزین lambda

```python
from functools import partial

def power(base, exp):
    return base ** exp

square = partial(power, exp=2)
print(square(5))  # 25
```

---

## ۱۴. any و all با generator

```python
numbers = [2, 4, 6, 8]
all_even = all(n % 2 == 0 for n in numbers)
any_big = any(n > 100 for n in numbers)
```

---

## ۱۵. توضیح فایل‌های مثال

### 📄 `01_lambda_map_filter.py` — پایه
### 📄 `02_sorted_key.py` — key=lambda
### 📄 `03_reduce_operator.py` — reduce و operator
### 📄 `04_functional_pipeline.py` — pipeline زنجیره‌ای

---

## ۱۶. سوالات کلاسی

1. lambda vs def — چه زمانی lambda؟
2. `[f(x) for x in items]` vs `list(map(f, items))`؟
3. تله lambda در حلقه for؟

---

## ۱۷. Zen of Python (functional)

> Explicit is better than implicit.  
> Readability counts.

اگر lambda خوانایی را کم می‌کند — `def` بنویسید.

---

## راهنمای مدرس (۱۵ دقیقه)

- قبل از شروع: مرور کوتاه جلسه قبل و اهداف روی تخته
- حین جلسه: دانشجو کد را خودش تایپ کند — copy/paste نکند
- بعد از تمرین: ۵ دقیقه Q&A و اشاره به exercises/question.md
- تکلیف خانه: exercise_02.py — deadline هفته بعد
- ارزیابی: حضور در تمرین کلاسی + تحویل exercise_02

---

## منابع تکمیلی

- [functools — reduce, partial](https://docs.python.org/3/library/functools.html)
- [operator module](https://docs.python.org/3/library/operator.html)

### چک‌لیست پایان جلسه

- [ ] lambda ساده
- [ ] map و filter
- [ ] sorted با key
- [ ] reduce
- [ ] partial از functools (اشاره)
- [ ] comprehension vs map مقایسه شد

---

## نکات تکمیلی برای مدرس

تله lambda in loop را **حتماً** نشان دهید. PEP 8: lambda فقط برای callback یک‌خطی. sorted(key=) کاربرد عملی بیشتری از map دارد.

### اجرای تمرین‌ها

```bash
python session-22-lambda-functional/examples/04_functional_pipeline.py
python session-22-lambda-functional/exercises/exercise_02.py
```

در کد production امروزه comprehension اغلب بر map/filter/traditional lambda ترجیح داده می‌شود.

### when to use

| ابزار | کاربرد |
|-------|--------|
| lambda | key= در sorted، callback کوتاه |
| map | transform (یا comprehension) |
| filter | فیلتر (یا comprehension) |
| reduce | aggregate — یا sum/max built-in |

Functional style در Python toolkit است — نه paradigm اجباری.

Guido: map/filter «beautiful heart» — ولی list comp often clearer.

جلسه ۲۴ JSON و ۲۵ CSV complement این functional data tools.

مثال pipeline: `python session-22-lambda-functional/examples/04_functional_pipeline.py`

---

**جلسه بعدی:** [جلسه ۲۳ — Regex](../session-23-regex/)
