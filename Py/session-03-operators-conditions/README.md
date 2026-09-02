# جلسه ۳: عملگرها، شرط‌ها و منطق برنامه (۱.۵ ساعت)

## 🎯 اهداف یادگیری

پس از این جلسه می‌توانید:
- عملگرهای ریاضی، مقایسه‌ای، منطقی و انتساب را بشناسید
- با `if`، `elif`، `else` شاخه‌های تصمیم بسازید
- شرط‌های ترکیبی با `and`، `or`، `not` بنویسید
- از عملگر سه‌تایی (ternary) برای مقداردهی concise استفاده کنید
- truthiness در Python را درک کنید
- کد readable برای سناریوهای واقعی (نمره، ورود، تخفیف) بنویسید

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | مرور متغیرها و انواع |
| 10–25 | عملگرهای ریاضی و مقایسه |
| 25–40 | عملگرهای منطقی و truthiness |
| 40–60 | `if/elif/else` — تمرین زنده |
| 60–75 | ternary، nested if، مثال نمره |
| 75–85 | اشتباهات رایج |
| 85–90 | تکلیف |

---

## ۱. عملگرهای ریاضی

```python
x = 10
y = 3

print(x + y)   # 13 — جمع
print(x - y)   # 7  — تفریق
print(x * y)   # 30 — ضرب
print(x / y)   # 3.333... — تقسیم (همیشه float)
print(x // y)  # 3  — تقسیم صحیح
print(x % y)   # 1  — باقی‌مانده
print(x ** y)  # 1000 — توان
```

**خط به خط:**
- `/` در Python 3 همیشه **float** برمی‌گرداند
- `//` — بخش صحیح تقسیم
- `%` — باقی‌مانده؛ برای زوج/فرد: `n % 2 == 0`

---

## ۲. عملگرهای مقایسه

```python
a = 10
b = 3

print(a == b)   # False — برابر
print(a != b)   # True  — نابرابر
print(a > b)    # True
print(a <= b)   # False
```

**مهم:** `==` مقایسه است؛ `=` انتساب.

```python
if age == 18:   # ✅
    pass
# if age = 18:  # ❌ SyntaxError
```

---

## ۳. عملگرهای منطقی

```python
age = 20
has_ticket = True

print(age >= 18 and has_ticket)   # True
print(age < 18 or has_ticket)     # True
print(not has_ticket)             # False
```

| عملگر | معنی |
|--------|------|
| `and` | هر دو True |
| `or` | حداقل یکی True |
| `not` | معکوس |

### ۳.۱. Truthiness

در Python این‌ها **False** محسوب می‌شوند:
- `False`, `None`, `0`, `0.0`, `""`, `[]`, `{}`, `set()`

```python
name = ""
if name:
    print("نام دارد")
else:
    print("نام خالی")  # اجرا می‌شود
```

---

## ۴. ساختار `if/else`

### ۴.۱. فرم پایه

```python
age = 20

if age >= 18:
    print("بزرگسال")
else:
    print("نوجوان")
```

**نکات:**
- بعد از `if` و `else` **دو نقطه `:`** الزامی
- بلوک با **indent** (معمولاً ۴ فاصله)
- Python از `{}` استفاده نمی‌کند

### ۴.۲. `elif` — چند شرط

```python
score = 85

if score >= 90:
    grade = "A"
elif score >= 80:
    grade = "B"
elif score >= 70:
    grade = "C"
else:
    grade = "F"

print(f"نمره: {grade}")
```

**ترتیب مهم است** — اولین شرط True اجرا می‌شود.

### ۴.۳. شرط‌های تو در تو

```python
age = 20
has_id = True

if age >= 18:
    if has_id:
        print("ورود مجاز")
    else:
        print("کارت شناسایی لازم است")
else:
    print("سن کافی نیست")
```

**بهترین عمل:** بیش از ۲–۳ سطح تو در تو → refactor با `and` یا تابع.

---

## ۵. عملگر سه‌تایی (Ternary)

```python
score = 75
status = "قبول" if score >= 50 else "مردود"
print(status)
```

- عبارت است نه statement — **مقدار** برمی‌گرداند
- برای دو حالت ساده مناسب

---

## ۶. عملگرهای انتساب ترکیبی

```python
count = 0
count += 1   # count = count + 1
count *= 2   # count = count * 2
```

---

## ۷. مثال واقعی — سیستم نمره

```python
score = 82

if score < 0 or score > 100:
    print("نمره نامعتبر!")
elif score >= 90:
    print("عالی — A")
elif score >= 75:
    print("خوب — B")
elif score >= 50:
    print("قابل قبول — C")
else:
    print("مردود — F")

result = "قبول" if score >= 50 else "مردود"
print(f"وضعیت: {result}")
```

---

## ۸. مثال — زوج یا فرد

```python
number = 7

if number % 2 == 0:
    print(f"{number} زوج است")
else:
    print(f"{number} فرد است")
```

---

## ۹. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_operators.py](./examples/01_operators.py) | عملگرهای ریاضی و مقایسه |
| [02_conditions.py](./examples/02_conditions.py) | if/elif/else |
| [03_logical_operators.py](./examples/03_logical_operators.py) | and/or/not |
| [04_grade_system.py](./examples/04_grade_system.py) | سیستم نمره واقعی |

---

## ۱۰. اشتباهات رایج

| اشتباه | مشکل | راه‌حل |
|--------|------|--------|
| `if age >= 18` بدون `:` | SyntaxError | `if age >= 18:` |
| `if age = 18` | SyntaxError | `==` |
| `if 18 <= age <= 65` | ✅ در Python مجاز | — |
| elif ترتیب اشتباه | شرط پایین هرگز اجرا نمی‌شود | از strict به loose |
| ternary تو در تو زیاد | خوانایی پایین | if/elif |

### ❌ فراموش کردن indent

```python
if True:
print("خطا")  # IndentationError
```

---

## ۱۱. بهترین شیوه‌ها

1. **همیشه `{}` معادل:** indent یکسان در بلوک
2. **نام bool:** `is_valid`, `has_access`
3. **Early validation:** ابتدا ورودی نامعتبر را رد کنید
4. **elif به‌جای if تو در تو** وقتی ممکن است
5. **تست edge case:** ۰، منفی، مرز (مثلاً ۱۸، ۵۰)

---

## ۱۲. تمرین کلاسی

1. زوج/فرد — عدد از کاربر
2. بزرگ‌ترین سه عدد — بدون `max()`
3. ماه → فصل — elif
4. ماشین‌حساب mini — دو عدد + عملگر

**تکلیف:** [question.md](./exercises/question.md)

### ۷.۴. مقایسه زنجیره‌ای (Python)

```python
age = 25
if 18 <= age <= 65:
    print("سن کاری")
```

Python اجازه می‌دهد شرط را به‌صورت ریاضی بنویسید — خواناتر از `age >= 18 and age <= 65`.

### ۷.۵. match-case (Python 3.10+ — پیش‌نمایش)

```python
command = "start"
match command:
    case "start":
        print("شروع")
    case "stop":
        print("توقف")
    case _:
        print("نامعتبر")
```

در دوره مقدماتی `if/elif` کافی است؛ `match` برای جلسات پیشرفته.

---

## ۱۴. تکلیف خانه

1. **BMI کامل** — دسته‌بندی با elif
2. **ساعت روز** — ۰–۲۳ → صبح/ظهر/عصر/شب
3. **Leap year** — سال کبیسه با قوانین تقسیم

---

## ۱۵. خلاصه

- ✅ عملگرها: `+`, `==`, `and`, `+=`
- ✅ `if/elif/else` با `:` و indent
- ✅ ternary: `a if cond else b`
- ✅ truthiness برای بررسی خالی بودن

---

**جلسه قبل:** [۲ — متغیرها](../session-02-variables/) | **بعد:** [۴ — حلقه‌ها](../session-04-loops/)
