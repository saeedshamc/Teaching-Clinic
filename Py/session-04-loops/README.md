# جلسه ۴: حلقه‌ها و کنترل جریان (۱.۵ ساعت)

## 🎯 اهداف یادگیری

پس از این جلسه می‌توانید:
- تفاوت `for` و `while` را توضیح دهید
- با `range()` و iterate روی لیست کار کنید
- از `break`، `continue` و `else` روی حلقه استفاده کنید
- حلقه‌های تو در تو برای جدول ضرب و الگوها بنویسید
- از حلقه بی‌نهایت (`while True`) با شرط خروج استفاده کنید
- اشتباهات رایج (off-by-one، infinite loop) را بشناسید

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | مرور شرط‌ها |
| 10–30 | حلقه `for` و `range()` |
| 30–45 | iterate روی لیست و رشته |
| 45–60 | حلقه `while` |
| 60–75 | `break`، `continue`، `else` |
| 75–85 | حلقه تو در تو — جدول ضرب |
| 85–90 | تکلیف |

---

## ۱. حلقه چیست؟

حلقه بلوک کد را **تکرار** می‌کند بدون copy-paste.

```python
for i in range(3):
    print(i)  # 0, 1, 2
```

---

## ۲. حلقه `for`

### ۲.۱. با `range()`

```python
# range(stop) — از ۰ تا stop-1
for i in range(5):
    print(i)  # 0..4

# range(start, stop)
for i in range(1, 6):
    print(i)  # 1..5

# range(start, stop, step)
for i in range(0, 10, 2):
    print(i)  # 0, 2, 4, 6, 8
```

**خط به خط:**
- `range(5)` — ۵ عدد: ۰ تا ۴
- `stop` **شامل نمی‌شود** — off-by-one دقت کنید

### ۲.۲. iterate روی sequence

```python
fruits = ["سیب", "موز", "پرتقال"]
for fruit in fruits:
    print(fruit)

for char in "Python":
    print(char)
```

### ۲.۳. `enumerate` — اندیس + مقدار

```python
items = ["الف", "ب", "پ"]
for index, item in enumerate(items):
    print(index, item)
```

---

## ۳. حلقه `while`

```python
count = 0
while count < 3:
    print("تکرار", count)
    count += 1
```

- شرط **قبل** از هر تکرار بررسی می‌شود
- **حتماً** متغیر شرط را داخل حلقه تغییر دهید — وگرنه infinite loop

### ۳.۱. `while True` + break

```python
while True:
    cmd = input("دستور (q=خروج): ")
    if cmd == "q":
        break
    print("اجرا:", cmd)
```

---

## ۴. `break` و `continue`

```python
# break — خروج کامل از حلقه
for i in range(1, 6):
    if i == 3:
        break
    print(i)  # 1, 2

# continue — پرش به تکرار بعد
for i in range(1, 6):
    if i == 3:
        continue
    print(i)  # 1, 2, 4, 5
```

---

## ۵. `else` روی حلقه

```python
for n in range(2, 10):
    if n % 7 == 0:
        print("یافت شد:", n)
        break
else:
    print("عدد اول ۷ در بازه یافت نشد")
```

`else` فقط وقتی اجرا می‌شود که حلقه **بدون break** تمام شود.

---

## ۶. حلقه تو در تو

```python
for i in range(1, 4):
    for j in range(1, 4):
        print(f"{i}×{j}={i*j}", end="\t")
    print()
```

---

## ۷. جمع و شمارش

```python
total = 0
for i in range(1, 101):
    total += i
print("جمع ۱ تا ۱۰۰:", total)

count = 0
for c in "hello":
    if c == "l":
        count += 1
print("تعداد l:", count)
```

---

## ۸. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_for_loop.py](./examples/01_for_loop.py) | for و range |
| [02_while_loop.py](./examples/02_while_loop.py) | while |
| [03_break_continue.py](./examples/03_break_continue.py) | break/continue |
| [04_multiplication_table.py](./examples/04_multiplication_table.py) | جدول ضرب |

---

## ۹. اشتباهات رایج

| اشتباه | مشکل | راه‌حل |
|--------|------|--------|
| `while True` بدون break | infinite loop | شرط خروج |
| `range(1, 10)` برای ۱–۱۰ | ۱۰ چاپ نمی‌شود | `range(1, 11)` |
| فراموش `count += 1` | حلقه بی‌پایان | به‌روزرسانی متغیر |
| تغییر لیست حین iterate | رفتار غیرمنتظره | کپی یا index معکوس |

---

## ۱۰. بهترین شیوه‌ها

1. **`for`** وقتی تعداد تکرار مشخص است
2. **`while`** برای شرط نامشخص (تا وقتی...)
3. از **`enumerate`** به‌جای `range(len(...))`
4. حلقه تو در تو عمیق → refactor به تابع
5. جمع/شمارش: متغیر **قبل** حلقه مقداردهی اولیه

---

## ۱۱. تمرین کلاسی

1. اعداد ۱ تا ۱۰
2. جمع ۱ تا ۱۰۰
3. جدول ضرب ۱ تا ۵
4. پیدا کردن اولین عدد بخش‌پذیر بر ۷ در ۱–۵۰

**تکلیف:** [question.md](./exercises/question.md)

### ۷.۱. reversed و sorted

```python
for i in reversed(range(5)):
    print(i)  # 4,3,2,1,0

nums = [3, 1, 4, 1, 5]
for n in sorted(nums):
    print(n)
```

### ۷.۲. الگوی accumulator

```python
total = 0
for x in values:
    total += x
```

---

## ۱۳. تکلیف خانه

1. **FizzBuzz** کامل ۱–۱۰۰
2. **عامل اول** — آیا n اول است؟
3. **مثلث اعداد** — چاپ الگوی *

---

## ۱۵. توضیح خط‌به‌خط مثال‌ها

### 📄 `01_for_loop.py`

```python
for number in range(1, 6):
    print(number)
```

- `range(1, 6)` → 1,2,3,4,5 — ۶ شامل نیست
- `for item in ["پایتون", ...]` — iterate مستقیم روی لیست

### 📄 `03_break_continue.py`

- `break` — خروج فوری از حلقه
- `continue` — رفتن به iteration بعد
- `else` on loop — فقط بدون break

### 📄 `04_multiplication_table.py`

- حلقه تو در تو: بیرونی سطر، درونی ستون
- `sum(range(1, 101))` — built-in جایگزین حلقه دستی

---

## ۱۷. مطالعه بیشتر

- [Python range() docs](https://docs.python.org/3/library/stdtypes.html#range)
- الگورitmهای رایج: sum, min, max, enumerate
- تمرین: nested loop برای مثلث Pascal

---

## ۱۸. خلاصه

- ✅ `for x in ...` و `range(start, stop, step)`
- ✅ `while` با شرط
- ✅ `break`, `continue`, `else`
- ✅ حلقه تو در تو

---

**جلسه قبل:** [۳ — شرط‌ها](../session-03-operators-conditions/) | **بعد:** [۵ — ساختار داده](../session-05-data-structures/)
