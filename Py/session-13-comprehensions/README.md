# جلسه ۱۳: Comprehensionها (List، Dict، Set)

## اهداف یادگیری (Learning Objectives)

پس از این جلسه می‌توانید:
- مفهوم comprehension را با یک تشبیه ساده توضیح دهید
- با **list comprehension** لیست‌های جدید بسازید
- با **dict comprehension** و **set comprehension** ساختارهای دیگر بسازید
- شرط (`if`) را داخل comprehension به‌کار ببرید
- تفاوت comprehension و حلقه `for` معمولی را بدانید
- تشخیص دهید چه زمانی comprehension خواناتر است و چه زمانی نه

---

## Comprehension چیست؟

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

## ساختار list comprehension

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

---

## dict comprehension

برای ساخت دیکشنری از روی لیست یا دیکشنری دیگر:

```python
names = ["سعید", "مینا", "علی"]
name_lengths = {name: len(name) for name in names}
print(name_lengths)
# {'سعید': 4, 'مینا': 4, 'علی': 3}
```

**ساختار:** `{کلید: مقدار for ... in ...}`

---

## set comprehension

برای ساخت مجموعه بدون تکرار:

```python
words = ["سلام", "دنیا", "سلام", "python"]
unique_lengths = {len(w) for w in words}
print(unique_lengths)  # {4, 5, 6} — ترتیب ثابت نیست
```

---

## comprehension تو در تو (Nested)

گاهی منبع خودش لیست دوبعدی است:

```python
matrix = [[1, 2], [3, 4], [5, 6]]
flat = [num for row in matrix for num in row]
print(flat)  # [1, 2, 3, 4, 5, 6]
```

**ترتیب خواندن:** از چپ به راست — اول حلقه بیرونی، بعد درونی (مثل دو `for` پشت سر هم).

---

## فایل‌های این جلسه

| فایل | توضیح |
|------|-------|
| [examples/01_list_comprehension.py](./examples/01_list_comprehension.py) | list، dict و set comprehension |
| [examples/02_nested_comprehension.py](./examples/02_nested_comprehension.py) | comprehension تو در تو و فیلتر |
| [exercises/exercise_01.py](./exercises/exercise_01.py) | تمرین‌های این جلسه |

---

## توضیح کدها

### 📄 `01_list_comprehension.py`

```python
numbers = [1, 2, 3, 4, 5]
squares = [n * n for n in numbers]
```

**خط به خط:**
- `numbers` — لیست منبع
- `[n * n for n in numbers]` — برای هر `n`، مربع آن را در لیست جدید می‌گذارد
- `if n % 2 == 0` — فقط عناصری که باقیمانده تقسیم بر ۲ صفر است نگه داشته می‌شوند

---

### 📄 `02_nested_comprehension.py`

```python
flat = [num for row in matrix for num in row]
```

**خط به خط:**
- `for row in matrix` — روی هر ردیف ماتریس
- `for num in row` — روی هر عدد داخل ردیف
- `num` — همان عدد در لیست تخت (flat) قرار می‌گیرد

---

## نکات رایج اشتباهات (Common Pitfalls)

### ❌ اشتباه ۱: comprehension خیلی پیچیده
```python
# ❌ خواندن سخت — بهتر است حلقه معمولی
result = [x*y for x in a for y in b if x > 0 if y < 10 for z in c if z != x]
```

```python
# ✅ comprehension ساده یا حلقه جدا
evens = [n for n in numbers if n % 2 == 0]
```

### ❌ اشتباه ۲: side effect داخل comprehension
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

### ❌ اشتباه ۳: فراموش کردن آکولاد در dict
```python
lengths = (name: len(name) for name in names)  # ❌ خطای syntax
lengths = {name: len(name) for name in names}  # ✅
```

---

## بهترین شیوه‌ها (Best Practices)

- comprehension را برای **تبدیل و فیلتر ساده** استفاده کنید
- اگر بیش از یک `if` یا دو حلقه تو در تو دارید، حلقه `for` معمولی خواناتر است
- نام متغیر حلقه را کوتاه ولی معنادار بگذارید (`n`، `name`، `row`)
- از side effect (مثل `print` یا نوشتن فایل) داخل comprehension پرهیز کنید

---

## خلاصه جلسه

در این جلسه یاد گرفتید:
- ✅ comprehension روش فشرده ساخت list/dict/set است
- ✅ ساختار: `[expr for x in source if cond]`
- ✅ dict: `{k: v for ...}` و set: `{expr for ...}`
- ✅ comprehension تو در تو برای لیست‌های دوبعدی
- ✅ سادگی مهم‌تر از کوتاهی افراطی است

---

## تمرین‌ها

### تمرین ۱ (ساده): اعداد فرد
لیست `[1, 2, 3, 4, 5, 6, 7, 8, 9]` داده شده. با **list comprehension** فقط اعداد **فرد** را استخراج کنید.

**راهنما:** از `if n % 2 != 0` استفاده کنید.

### تمرین ۲ (متوسط): dict comprehension
لیست `["سعید", "مینا", "علی"]` داده شده. دیکشنری بسازید که **کلید = نام** و **مقدار = طول نام** باشد.

**راهنما:** `{name: len(name) for name in names}`

### تمرین ۳ (پیشرفته): فیلتر و تبدیل
لیست `[10, 15, 20, 25, 30]` — لیستی از **مربع اعداد بزرگ‌تر از ۱۵** بسازید.

**راهنما:** `[n * n for n in nums if n > 15]`

راه‌حل‌ها در فایل [exercises/exercise_01.py](./exercises/exercise_01.py) موجود است.

---

**جلسه بعدی:** [جلسه ۱۴ — Decoratorها](../session-14-decorators/)
