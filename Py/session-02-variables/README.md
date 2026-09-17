# جلسه ۲: متغیرها و انواع داده پایه (۱.۵ ساعت)

## 🎯 اهداف یادگیری

پس از این جلسه می‌توانید:
- مفهوم متغیر و **Dynamic Typing** را توضیح دهید
- با انواع `int`، `float`، `str`، `bool` کار کنید
- نوع داده را با `type()` بررسی کنید
- تبدیل نوع با `int()`، `float()`، `str()`، `bool()` انجام دهید
- از `input()` و f-string برای تعامل با کاربر استفاده کنید
- قوانین نام‌گذاری (`snake_case`) و ثابت‌ها را رعایت کنید
- اشتباهات رایج (جمع str+int، `=` vs `==`) را بشناسید

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | مرور جلسه ۱ — `print` و اجرای اسکریپت |
| 10–25 | متغیر، انتساب، انواع پایه |
| 25–40 | `type()` و Type Casting |
| 40–55 | f-string و قالب‌بندی |
| 55–70 | `input()` — تبدیل ورودی، ماشین‌حساب ساده |
| 70–85 | نام‌گذاری، ثابت‌ها، تمرین مستطیل/فارنهایت |
| 85–90 | اشتباهات رایج + تکلیف |

---

## ۱. متغیر چیست؟

**متغیر** برچسبی روی یک مقدار در حافظه است. در Python نیازی به اعلام نوع نیست — مفسر خودش تشخیص می‌دهد (**Dynamic Typing**).

```python
name = "سعید"
age = 25
```

**خط به خط:**
- `name` — شناسه (identifier) متغیر
- `=` — **انتساب** (assignment)، نه مقایسه
- `"سعید"` — مقدار از نوع `str`

### ۱.۱. چرا متغیر؟

| مزیت | مثال |
|------|------|
| خوانایی | `total_price` بهتر از `499000` |
| تغییرپذیری | `count = count + 1` |
| استفاده مجدد | یک بار ذخیره، چند بار استفاده |

---

## ۲. انواع داده پایه

### ۲.۱. `int` — عدد صحیح

```python
age = 25
year = 2026
negative = -10
big = 1_000_000  # underscore برای خوانایی
```

### ۲.۲. `float` — عدد اعشاری

```python
price = 19.99
pi = 3.14159
scientific = 1.5e3  # 1500.0
```

### ۲.۳. `str` — رشته (متن)

```python
name = "پایتون"
quote = 'سلام'
multiline = """خط اول
خط دوم"""
```

### ۲.۴. `bool` — بولی

```python
is_active = True
has_error = False
```

**نکته:** `True` و `False` با T و F بزرگ — کلمات کلیدی Python.

---

## ۳. بررسی نوع — `type()`

```python
name = "سعید"
age = 25
print(type(name))   # <class 'str'>
print(type(age))    # <class 'int'>
```

در دیباگ و یادگیری بسیار مفید است.

---

## ۴. تبدیل نوع (Type Casting)

```python
age_text = "25"
age = int(age_text)      # str → int
price = float("19.99")   # str → float
label = str(100)         # int → str
flag = bool(1)           # True
```

**خط به خط:**
- `int("25")` — رشته **عددی** را به int تبدیل می‌کند
- `int("abc")` — **ValueError**
- `str(100)` — برای چسباندن به متن: `"تعداد: " + str(100)`

### ۴.۱. تبدیل ضمنی در عملیات

```python
result = 3 + 4.5   # int + float → float (7.5)
# "3" + 4          # TypeError — باید صریح cast کنید
```

---

## ۵. f-string (Formatted String Literals)

```python
name = "سعید"
age = 25
print(f"سلام {name}! سن شما {age} است.")
print(f"سال بعد {age + 1} ساله می‌شوید.")
print(f"قیمت: {19.5:.2f} تومان")  # دو رقم اعشار
```

- حرف `f` قبل از `"..."` — عبارت داخل `{}` ارزیابی می‌شود
- Python 3.6+ — روش پیشنهادی

---

## ۶. ورودی کاربر — `input()`

```python
name = input("نام شما چیست؟ ")
age = int(input("سن شما چند است؟ "))
print(f"سلام {name}! سال بعد {age + 1} ساله می‌شوید.")
```

**مهم:** `input()` **همیشه `str` برمی‌گرداند** — برای محاسبه باید cast کنید.

### ۶.۱. حالت دمو (بدون تعامل)

```python
# برای تست خودکار — مقادیر از پیش تعیین‌شده
name = "سعید"
age = 25
```

---

## ۷. قوانین نام‌گذاری

| قانون | درست | نادرست |
|-------|------|--------|
| با حرف یا `_` شروع | `name`, `_temp` | `2name` |
| فقط حروف، عدد، `_` | `user_age` | `user-age` |
| حساس به حروف | `age` ≠ `Age` | — |
| نه کلمه کلیدی | `user_name` | `class`, `for` |

**سبک:** `snake_case` — `first_name`, `total_price`

### ۷.۱. ثابت‌ها (قرارداد)

```python
MAX_USERS = 100
PI = 3.14159
```

Python از نظر فنی مانع تغییر نمی‌شود — **قرارداد** است که با حروف بزرگ بنویسید.

---

## ۸. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_variables.py](./examples/01_variables.py) | تعریف متغیر و `type()` |
| [02_user_input.py](./examples/02_user_input.py) | `input` و f-string |
| [03_type_casting.py](./examples/03_type_casting.py) | تبدیل نوع |
| [04_constants_demo.py](./examples/04_constants_demo.py) | ثابت‌ها و محاسبات |

---

## ۹. اشتباهات رایج

| اشتباه | مشکل | راه‌حل |
|--------|------|--------|
| `age + 1` وقتی age از input | TypeError | `int(input(...))` |
| `if age = 18` | SyntaxError | `if age == 18` |
| `"قیمت: " + 100` | TypeError | f-string یا `str()` |
| نام `x`, `a` | خوانایی پایین | نام معنادار |

---

## ۱۰. بهترین شیوه‌ها

1. نام‌های **معنادار** و `snake_case`
2. بعد از `input()` **همیشه cast** برای عدد
3. **f-string** برای ترکیب متن و مقدار
4. ثابت‌ها با **UPPER_SNAKE_CASE**
5. در ابتدای یادگیری `type()` برای اطمینان

---

## ۱۱. تمرین کلاسی

1. نام و شهر → پیام خوش‌آمد
2. طول و عرض مستطیل → مساحت و محیط
3. سلسیوس → فارنهایت: `F = C × 9/5 + 32`
4. دو عدد از کاربر → جمع، تفاضل، ضرب، تقسیم

**تکلیف:** [question.md](./exercises/question.md)

---

## ۱۲. تکلیف خانه

1. برنامه «مبدل واحد» — سانتی‌متر ↔ اینچ
2. برنامه «فروشگاه» — قیمت، تعداد، تخفیف درصدی
3. جدول truthiness — چاپ `bool()` برای `0`, `""`, `[]`, `"0"`

**راه‌حل نمونه:** [exercise_02.py](./exercises/exercise_02.py)

---

## ۱۴. توضیح خط‌به‌خط مثال‌ها

### 📄 `01_variables.py`

```python
name = "سعید"
age = 25
height = 1.78
is_student = True
```

- هر خط یک **انتساب** — متغیر به مقدار bind می‌شود
- Python نوع را runtime تشخیص می‌دهد
- `type(name)` → `<class 'str'>`

### 📄 `03_type_casting.py`

- `int("25")` — parse رشته عددی
- `bool("")` → False — empty string falsy است
- `bool("hi")` → True

### 📄 `04_constants_demo.py`

- `TAX_RATE = 0.09` — قرارداد ثابت
- `{amount:,.0f}` — جداکننده هزارگان در f-string

---

## ۱۶. مطالعه بیشتر

- [docs.python.org — Built-in Types](https://docs.python.org/3/library/stdtypes.html)
- PEP 8 — نام‌گذاری `snake_case`
- تمرین: REPL برای آزمایش `type()` و cast

---

## ۱۷. خلاصه

- ✅ متغیر = برچسب روی مقدار
- ✅ `int`, `float`, `str`, `bool`
- ✅ `type()`, `int()`, `float()`, `str()`
- ✅ `input()` → str؛ f-string → `f"{var}"`

---

**جلسه قبل:** [۱ — مقدمه](../session-01-intro/) | **بعد:** [۳ — عملگرها و شرط‌ها](../session-03-operators-conditions/)
