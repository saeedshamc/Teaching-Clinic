# جلسه ۶: توابع (Functions) (۱.۵ ساعت)

## 🎯 اهداف یادگیری

پس از این جلسه می‌توانید:
- تابع را به‌عنوان بلوک قابل استفاده مجدد تعریف کنید
- پارامترها، آرگومان‌های positional و keyword را بشناسید
- با `return` مقدار برگردانید
- scope محلی/سراسری و `global` را درک کنید
- default parameters و docstring بنویسید
- توابع را برای refactor کد تکراری استفاده کنید

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | چرا تابع؟ DRY |
| 10–30 | تعریف، فراخوانی، return |
| 30–45 | پارامترها — default, keyword |
| 45–60 | scope — local vs global |
| 60–75 | docstring، مثال عملی |
| 75–85 | *args (معرفی کوتاه) |
| 85–90 | تکلیف |

---

## ۱. تابع چیست؟

```python
def greet(name):
    """به کاربر سلام می‌کند."""
    print(f"سلام {name}!")


greet("سعید")
greet("مریم")
```

**خط به خط:**
- `def` — شروع تعریف تابع
- `greet` — نام تابع
- `(name)` — پارامتر
- `:` و indent — بدنه تابع

---

## ۲. `return`

```python
def add(a, b):
    return a + b

result = add(2, 3)
print(result)  # 5
```

- بدون `return` → `None` برمی‌گردد
- `return` زودهنگام — خروج از تابع

```python
def is_adult(age):
    if age < 18:
        return False
    return True
```

---

## ۳. پارامترهای پیش‌فرض

```python
def power(base, exp=2):
    return base ** exp

print(power(3))      # 9
print(power(3, 3))   # 27
```

**نکته:** پارامتر بدون default قبل از default بیاید.

---

## ۴. آرگومان keyword

```python
def create_user(name, age, city="تهران"):
    print(f"{name}, {age}, {city}")

create_user("علی", 25)
create_user(name="مریم", age=30, city="اصفهان")
```

---

## ۵. Scope

```python
x = 10  # global

def show():
    x = 5  # local — shadow
    print("داخل:", x)

show()
print("خارج:", x)  # 10
```

### ۵.۱. `global` (با احتیاط)

```python
count = 0

def increment():
    global count
    count += 1
```

**بهترین عمل:** از `global` کم استفاده کنید — `return` ترجیح داده می‌شود.

---

## ۶. docstring

```python
def area_rectangle(w, h):
    """مساحت مستطیل را برمی‌گرداند.

    Args:
        w: عرض
        h: ارتفاع
    """
    return w * h
```

---

## ۷. مثال — اعتبارسنجی و محاسبه

```python
def celsius_to_fahrenheit(c):
    return c * 9 / 5 + 32

def validate_score(score):
    return 0 <= score <= 100

def grade_from_score(score):
    if not validate_score(score):
        return "نامعتبر"
    if score >= 90:
        return "A"
    if score >= 75:
        return "B"
    return "C"
```

---

## ۸. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_simple_function.py](./examples/01_simple_function.py) | تعریف و فراخوانی |
| [02_return_value.py](./examples/02_return_value.py) | return |
| [03_parameters.py](./examples/03_parameters.py) | default و keyword |
| [04_scope_docstring.py](./examples/04_scope_docstring.py) | scope و docstring |

---

## ۹. اشتباهات رایج

| اشتباه | راه‌حل |
|--------|--------|
| فراموش `:` بعد def | SyntaxError |
| تغییر list default | `def f(x=[])` — از None استفاده کنید |
| استفاده زیاد global | return و پارامتر |
| تابع خیلی بلند | تقسیم به توابع کوچک |

---

## ۱۰. تمرین کلاسی

1. `greet(name)`
2. `average(a, b, c)`
3. `is_even(n)` → bool
4. `rectangle_info(w, h)` → مساحت و محیط

**تکلیف:** [question.md](./exercises/question.md)

### ۷.۱. *args (معرفی)

```python
def total(*numbers):
    s = 0
    for n in numbers:
        s += n
    return s

print(total(1, 2, 3, 4))  # 10
```

### ۷.۲. lambda (پیش‌نمایش جلسه ۲۲)

```python
double = lambda x: x * 2
print(double(5))
```

---

## ۱۲. تکلیف خانه

1. **factorial** با تابع recursive یا حلقه
2. **is_palindrome** برای رشته
3. **ماژول math_utils** — جدا در فایل دوم

---

## ۱۴. توضیح خط‌به‌خط مثال‌ها

### 📄 `01_simple_function.py`

```python
def greet(name):
    print("سلام", name)
```

- `def` — تعریف تابع
- فراخوانی: `greet("سعید")` — name= "سعید"

### 📄 `02_return_value.py`

- `return` بلافاصله تابع را ترک می‌کند
- مقدار برگشتی در متغیر ذخیره می‌شود

### 📄 `04_scope_docstring.py`

- `nonlocal` — متغیر enclosing scope (نه global)
- `__doc__` — docstring تابع

---

## ۱۶. مقایسه def vs lambda

| | def | lambda |
|---|-----|--------|
| چند خط | ✅ | یک expression |
| statement | ✅ | ❌ |
| docstring | ✅ | ❌ |
| کاربرد | منطق اصلی | callback کوتاه |

---

## ۱۷. مطالعه بیشتر

- [docs — def](https://docs.python.org/3/tutorial/controlflow.html#defining-functions)
- LEGB rule برای scope
- تمرین: تبدیل اسکریپت linear به توابع

---

## ۱۹. تکلیف خانه (جزئیات)

** factorial:** `def factorial(n)` — 0! = 1

** is_prime:** `def is_prime(n)` — bool

** geometry module:** `area_circle(r)`, `area_rectangle(w,h)` در یک فایل

---

## ۲۱. پرسش‌های کلاسی (Q&A)

**س: تفاوت print و return؟**  
ج: print نمایش — return مقدار به caller.

**س: پارامتر default mutable؟**  
ج: `def f(x=[])` خطرناک — از `None` استفاده کنید.

**س: global vs nonlocal؟**  
ج: global ماژule-level — nonlocal enclosing function.

---

## ۲۲. خلاصه

- ✅ `def name(params):`
- ✅ `return` مقدار
- ✅ default و keyword args
- ✅ scope محلی/سراسری

---

**جلسه قبل:** [۵ — ساختار داده](../session-05-data-structures/) | **بعد:** [۷ — رشته و فایل](../session-07-strings-files/)
