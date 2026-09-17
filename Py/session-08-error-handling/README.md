# جلسه ۸: مدیریت خطا و دیباگ (۱.۵ ساعت)

## 🎯 اهداف یادگیری

پس از این جلسه می‌توانید:
- Exception و traceback را بخوانید
- `try/except/else/finally` بنویسید
- انواع خطای رایج: `ValueError`, `ZeroDivisionError`, `FileNotFoundError`, `KeyError`
- چند `except` و catch کردن Exception عمومی
- `raise` برای پرتاب خطای سفارشی
- اصول دیباگ: print، traceback، input validation

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | خطا vs Exception |
| 10–30 | try/except پایه |
| 30–45 | انواع Exception |
| 45–60 | else/finally |
| 60–75 | raise و validation |
| 75–85 | دیباگ عملی |
| 85–90 | تکلیف |

---

## ۱. خطا چیست؟

```python
# بدون handle — برنامه crash
# result = 10 / 0  # ZeroDivisionError
```

Python traceback نشان می‌دهد خطا **کجا** رخ داده.

---

## ۲. try/except

```python
try:
    age = int(input("سن: "))
    print(f"سن شما {age} است")
except ValueError:
    print("لطفاً عدد صحیح وارد کنید")
```

**خط به خط:**
- `try` — کد پرخطر
- `except ValueError` — فقط این نوع خطا
- بقیه Exception همچنان propagate می‌شوند

---

## ۳. چند except

```python
try:
    a = int("abc")
    b = 10 / 0
except ValueError:
    print("تبدیل نامعتبر")
except ZeroDivisionError:
    print("تقسیم بر صفر")
except Exception as e:
    print("خطای دیگر:", e)
```

---

## ۴. else و finally

```python
try:
    f = open("data.txt", "r", encoding="utf-8")
except FileNotFoundError:
    print("فایل نیست")
else:
    print("فایل باز شد:", f.name)
    f.close()
finally:
    print("پایان بلوک try")
```

- `else` — فقط اگر except اجرا **نشد**
- `finally` — **همیشه** اجرا می‌شود

**بهتر:** `with open` به‌جای try/close دستی.

---

## ۵. raise

```python
def withdraw(balance, amount):
    if amount <= 0:
        raise ValueError("مبلغ باید مثبت باشد")
    if amount > balance:
        raise ValueError("موجودی کافی نیست")
    return balance - amount
```

---

## ۶. EAFP vs LBYL

```python
# LBYL — Look Before You Leap
if key in data:
    value = data[key]

# EAFP — Easier to Ask Forgiveness (Pythonic)
try:
    value = data[key]
except KeyError:
    value = None
```

---

## ۷. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_try_except.py](./examples/01_try_except.py) | پایه |
| [02_multiple_exceptions.py](./examples/02_multiple_exceptions.py) | چند except |
| [03_finally_raise.py](./examples/03_finally_raise.py) | finally و raise |
| [04_validation_demo.py](./examples/04_validation_demo.py) | اعتبارسنجی |

---

## ۸. اشتباهات رایج

| اشتباه | راه‌حل |
|--------|--------|
| `except:` خالی | نوع مشخص یا log |
| بلعیدن خطا بدون log | حداقل print/log |
| try خیلی بزرگ | فقط خط پرخطر |
| catch Exception همه‌جا | specific first |

---

## ۹. تمرین کلاسی

1. int از input با handle ValueError
2. تقسیم با handle ZeroDivisionError
3. خواندن فایل ناموجود
4. تابع validate_age(0-120)

**تکلیف:** [question.md](./exercises/question.md)

### ۷.۱. logging vs print

```python
import logging
logging.basicConfig(level=logging.INFO)
logging.error("خطای جدی")  # جلسه ۲۷
```

برای پروژه واقعی `logging` بهتر از `print` است.

### ۷.۲. assert (برای debug)

```python
assert age >= 0, "سن نمی‌تواند منفی باشد"
```

---

## ۱۱. تکلیف خانه

1. **ماشین‌حساب loop** تا quit
2. **CSV reader** ساده با split
3. **custom exception** class `ValidationError`

---

## ۱۳. توضیح خط‌به‌خط مثال‌ها

### 📄 `01_try_except.py`

```python
try:
    result = 10 / 0
except ZeroDivisionError:
    print("...")
```

- فقط خط داخل try که exception می‌دهد catch می‌شود
- exception type باید match کند

### 📄 `03_finally_raise.py`

- `raise ValueError("msg")` — پرتاب خطا
- `finally` حتی بعد return اجرا می‌شود

### 📄 `04_validation_demo.py`

- validation = fail fast با raise یا return None
- default values برای demo بدون input

---

## ۱۵. hierarchy Exception (خلاصه)

```
BaseException
 └── Exception
      ├── ValueError
      ├── TypeError
      ├── KeyError
      ├── FileNotFoundError
      └── ZeroDivisionError
```

---

## ۱۶. مطالعه بیشتر

- [docs — errors](https://docs.python.org/3/tutorial/errors.html)
- EAFP vs LBYL — سبک Pythonic
- تمرین: wrapper `safe_int()` با retry

---

## ۱۸. تکلیف خانه (جزئیات)

** safe_divide(a,b):** None برگرداند در صورت خطا

** read_lines(path):** لیست خطوط یا [] اگر فایل نبود

** validate_email ساده:** شامل `@` و `.` — bool

---

## ۲۰. پرسش‌های کلاسی (Q&A)

**س: try همه Exception؟**  
ج: `except Exception` — فقط top-level یا logging.

**س: else در try؟**  
ج: فقط بدون exception.

**س: finally همیشه؟**  
ج: بله — حتی با return.

**س: raise vs throw؟**  
ج: Python فقط raise دارد.

---

## ۲۱. خلاصه

- ✅ try/except/else/finally
- ✅ Exception types
- ✅ raise برای validation
- ✅ with برای فایل

---

**جلسه قبل:** [۷ — رشته و فایل](../session-07-strings-files/) | **بعد:** [۹ — OOP](../session-09-oop/)
