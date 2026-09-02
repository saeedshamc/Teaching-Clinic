# جلسه ۱۳: Comprehensionها (List، Dict، Set) (۱.۵ ساعت)

## 🎯 اهداف یادگیری

پس از این جلسه می‌توانید:
- مفهوم comprehension را با یک تشبیه ساده توضیح دهید
- با **list comprehension** لیست‌های جدید بسازید
- با **dict comprehension** و **set comprehension** ساختارهای دیگر بسازید
- شرط (`if`) را داخل comprehension به‌کار ببرید
- comprehension تو در تو (nested) برای ماتریس و لیست دوبعدی بنویسید
- تفاوت comprehension و حلقه `for` معمولی را بدانید
- تشخیص دهید چه زمانی comprehension خواناتر است و چه زمانی نه

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع | فعالیت |
|------|--------|--------|
| 0–10 | مرور | جمع‌بندی جلسه ۱۲ (پروژه نهایی) — حلقه‌ها و list |
| 10–25 | List comprehension | ساختار، مربع اعداد، فیلتر با `if` |
| 25–40 | Dict و Set | `{k: v for ...}` و `{expr for ...}` |
| 40–55 | Nested comprehension | flatten ماتریس، فیلتر دو مرحله‌ای |
| 55–70 | مقایسه و کاربرد | comprehension vs حلقه، generator expression |
| 70–85 | تمرین کلاسی | فیلتر محصولات، dict از لیست |
| 85–90 | جمع‌بندی | اشتباهات رایج + تکلیف |

---

## ۱. Comprehension چیست؟

Comprehension روشی **فشرده و پایتونی** برای ساختن list، dict یا set از روی یک منبع داده است.

**تشبیه:** فرض کنید یک سبد سیب دارید و می‌خواهید فقط سیب‌های سالم را در سبد جدید بگذارید. به‌جای اینکه یکی‌یکی بردارید، بررسی کنید و بگذارید، یک خط می‌نویسید: «هر سیب سالم را بردار و در سبد جدید بگذار.»

### معادل حلقه‌ای و comprehension

**روش معمولی:**
```python
numbers = [1, 2, 3, 4, 5]
squares = []
for n in numbers:
    squares.append(n * n)
```

**با list comprehension:**
```python
numbers = [1, 2, 3, 4, 5]
squares = [n * n for n in numbers]
```

هر دو خروجی یکسان دارند؛ comprehension کوتاه‌تر و اغلب خواناتر است.

---

## ۲. ساختار list comprehension

```python
[عبارت for متغیر in منبع if شرط]
```

| بخش | نقش |
|-----|-----|
| `عبارت` | مقداری که در لیست جدید قرار می‌گیرد |
| `for متغیر in منبع` | روی هر عنصر منبع حرکت می‌کند |
| `if شرط` | اختیاری — فقط عناصر مطابق شرط |

### مثال: مربع اعداد
```python
numbers = [1, 2, 3, 4, 5]
squares = [n * n for n in numbers]
print(squares)  # [1, 4, 9, 16, 25]
```

### مثال: فقط اعداد زوج
```python
evens = [n for n in numbers if n % 2 == 0]
print(evens)  # [2, 4]
```

### مثال: تبدیل رشته‌ها
```python
names = ["سعید", "مینا", "علی"]
upper_names = [name.upper() for name in names]
print(upper_names)  # ['سعید', 'مینا', 'علی'] — upper روی فارسی تغییری نمی‌دهد
english = ["hello", "world"]
upper_en = [w.upper() for w in english]
print(upper_en)  # ['HELLO', 'WORLD']
```

---

## ۳. dict comprehension

برای ساخت دیکشنری از روی لیست یا دیکشنری دیگر:

```python
names = ["سعید", "مینا", "علی"]
name_lengths = {name: len(name) for name in names}
print(name_lengths)
# {'سعید': 4, 'مینا': 4, 'علی': 3}
```

**ساختار:** `{کلید: مقدار for ... in ...}`

### معکوس کردن دیکشنری
```python
scores = {"علی": 18, "مریم": 15, "رضا": 12}
passed = {name: score for name, score in scores.items() if score >= 10}
print(passed)  # همه — همه قبول شدند
```

### dict از دو لیست موازی
```python
keys = ["a", "b", "c"]
values = [1, 2, 3]
mapping = {k: v for k, v in zip(keys, values)}
print(mapping)  # {'a': 1, 'b': 2, 'c': 3}
```

---

## ۴. set comprehension

برای ساخت مجموعه بدون تکرار:

```python
words = ["سلام", "دنیا", "سلام", "python"]
unique_lengths = {len(w) for w in words}
print(unique_lengths)  # {4, 5, 6} — ترتیب ثابت نیست
```

**نکته:** set ترتیب ندارد و تکرار را حذف می‌کند.

---

## ۵. comprehension تو در تو (Nested)

گاهی منبع خودش لیست دوبعدی است:

```python
matrix = [[1, 2], [3, 4], [5, 6]]
flat = [num for row in matrix for num in row]
print(flat)  # [1, 2, 3, 4, 5, 6]
```

**ترتیب خواندن:** از چپ به راست — اول حلقه بیرونی، بعد درونی (مثل دو `for` پشت سر هم).

### ماتریس با شرط
```python
matrix = [[1, 2, 3], [4, 5, 6], [7, 8, 9]]
evens_in_matrix = [n for row in matrix for n in row if n % 2 == 0]
print(evens_in_matrix)  # [2, 4, 6, 8]
```

### ساخت ماتریس جدید
```python
# ماتریس ۳×۳ از صفرها
zeros = [[0 for _ in range(3)] for _ in range(3)]
print(zeros)
```

---

## ۶. comprehension با else (پیشرفته)

در list comprehension می‌توان `if-else` **قبل از** `for` گذاشت (نه بعد از آن):

```python
numbers = [1, 2, 3, 4, 5]
labels = ["زوج" if n % 2 == 0 else "فرد" for n in numbers]
print(labels)  # ['فرد', 'زوج', 'فرد', 'زوج', 'فرد']
```

**تفاوت:**
- `[x for x in items if cond]` — **فیلتر** (عناصر حذف می‌شوند)
- `[a if cond else b for x in items]` — **تبدیل** (همه عناصر می‌مانند)

---

## ۷. Generator expression — پیش‌نمایش

Comprehension با پرانتز به‌جای براکت، generator می‌سازد (جلسه ۱۵):

```python
squares_gen = (n * n for n in range(5))  # lazy — همه در حافظه نیست
squares_list = [n * n for n in range(5)]  # eager — list کامل
```

---

## ۸. مثال واقعی — فیلتر محصولات

```python
products = [
    {"name": "لپ‌تاپ", "price": 25_000_000, "stock": 3},
    {"name": "ماوس", "price": 500_000, "stock": 0},
    {"name": "کیبورد", "price": 1_200_000, "stock": 10},
]

# نام محصولات موجود و زیر ۲ میلیون
affordable = [
    p["name"]
    for p in products
    if p["stock"] > 0 and p["price"] < 2_000_000
]
print(affordable)  # ['ماوس', 'کیبورد']
```

---

## ۹. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_list_comprehension.py](./examples/01_list_comprehension.py) | list، dict و set comprehension |
| [02_nested_comprehension.py](./examples/02_nested_comprehension.py) | comprehension تو در تو و flatten |
| [03_conditional_transform.py](./examples/03_conditional_transform.py) | if-else و فیلتر پیشرفته |
| [04_real_world_filter.py](./examples/04_real_world_filter.py) | فیلتر داده واقعی |

---

## ۱۰. اشتباهات رایج (Pitfalls)

### ❌ comprehension خیلی پیچیده
```python
# ❌ خواندن سخت — بهتر است حلقه معمولی
result = [x*y for x in a for y in b if x > 0 if y < 10 for z in c if z != x]
```

```python
# ✅ comprehension ساده یا حلقه جدا
evens = [n for n in numbers if n % 2 == 0]
```

### ❌ side effect داخل comprehension
```python
# ❌ print داخل comprehension — anti-pattern
[print(n) for n in numbers]
```

```python
# ✅ فقط برای ساخت لیست
squares = [n * n for n in numbers]
for n in squares:
    print(n)
```

### ❌ فراموش کردن آکولاد در dict
```python
lengths = (name: len(name) for name in names)  # ❌ خطای syntax
lengths = {name: len(name) for name in names}  # ✅
```

### ❌ تله mutable default در nested
```python
# ❌ همه ردیف‌ها به یک list اشاره می‌کنند
matrix = [[0] * 3] * 3
matrix[0][0] = 1
print(matrix)  # همه ردیف‌ها تغییر می‌کنند!

# ✅ comprehension درست
matrix = [[0 for _ in range(3)] for _ in range(3)]
```

---

## ۱۱. بهترین شیوه‌ها

- comprehension را برای **تبدیل و فیلتر ساده** استفاده کنید
- اگر بیش از یک `if` یا دو حلقه تو در تو دارید، حلقه `for` معمولی خواناتر است
- نام متغیر حلقه را کوتاه ولی معنادار بگذارید (`n`، `name`، `row`)
- از side effect (مثل `print` یا نوشتن فایل) داخل comprehension پرهیز کنید
- برای داده بزرگ از generator expression `( ... )` به‌جای `[ ... ]` فکر کنید

---

## ۱۲. خلاصه جلسه

در این جلسه یاد گرفتید:
- ✅ comprehension روش فشرده ساخت list/dict/set است
- ✅ ساختار: `[expr for x in source if cond]`
- ✅ dict: `{k: v for ...}` و set: `{expr for ...}`
- ✅ comprehension تو در تو برای لیست‌های دوبعدی
- ✅ `if` بعد از `for` = فیلتر؛ `if-else` قبل از `for` = تبدیل
- ✅ سادگی مهم‌تر از کوتاهی افراطی است

---

## ۱۳. تکلیف خانه

### ساده (۱۵ دقیقه)
لیست `[1, 2, 3, 4, 5, 6, 7, 8, 9]` — فقط اعداد فرد را با list comprehension استخراج کنید.

### متوسط (۳۰ دقیقه)
لیست دانش‌آموزان با `name` و `score` دارید. dict comprehension بسازید که فقط نمره ≥ ۱۰ را نگه دارد.

### پیشرفته (۴۵ دقیقه)
ماتریس ۴×۴ تصادفی (یا ثابت) — با nested comprehension:
1. لیست تخت (flat) بسازید
2. فقط اعداد بزرگ‌تر از میانگین را برگردانید

راهنما: [exercises/question.md](./exercises/question.md)  
پاسخ نمونه: [exercise_01.py](./exercises/exercise_01.py) | [exercise_02.py](./exercises/exercise_02.py)

---

**جلسه بعدی:** [جلسه ۱۴ — Decoratorها](../session-14-decorators/)
