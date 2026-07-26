# جلسه ۱۴: Decoratorها

## اهداف یادگیری (Learning Objectives)

پس از این جلسه می‌توانید:
- مفهوم decorator را با یک تشبیه روزمره توضیح دهید
- تفاوت «تابع معمولی که تابع می‌گیرد» و «decorator با @» را بدانید
- یک decorator ساده بدون آرگومان بنویسید
- decorator با `*args` و `**kwargs` برای توابع با پارامتر بسازید
- از `functools.wraps` برای حفظ نام و docstring تابع اصلی استفاده کنید
- کاربردهای رایج (لاگ، زمان‌سنجی، شمارش فراخوانی) را تشخیص دهید

---

## Decorator چیست؟

Decorator تابعی است که **رفتار یک تابع دیگر را بدون تغییر بدنه آن** گسترش می‌دهد.

**تشبیه:** فرض کنید یک هدیه دارید. قبل از دادنش، کاغذ کادو می‌پیچید — هدیه همان است، ولی **قبل و بعد** کار اضافه (پیچیدن، پاپیون) انجام شده. Decorator هم «کاغذ کادو» برای تابع است.

### بدون @ — درک مکانیزم

```python
def my_decorator(func):
    def wrapper():
        print("قبل از اجرا")
        func()
        print("بعد از اجرا")
    return wrapper

def say_hello():
    print("سلام")

# اعمال دستی decorator
say_hello = my_decorator(say_hello)
say_hello()
```

### با @ — syntax استاندارد

```python
@my_decorator
def say_hello():
    print("سلام")
```

`@my_decorator` در واقع همان `say_hello = my_decorator(say_hello)` است — فقط خواناتر.

---

## Decorator و پارامترهای تابع

اگر تابع اصلی آرگومان دارد، `wrapper` باید `*args` و `**kwargs` بپذیرد:

```python
def count_calls(func):
    def wrapper(*args, **kwargs):
        wrapper.calls += 1
        print(f"فراخوانی شماره {wrapper.calls}")
        return func(*args, **kwargs)
    wrapper.calls = 0
    return wrapper

@count_calls
def greet(name):
    print("سلام", name)

greet("سعید")  # فراخوانی شماره 1
greet("مینا")  # فراخوانی شماره 2
```

**نکته:** `return func(...)` مقدار بازگشتی تابع اصلی را حفظ می‌کند.

---

## functools.wraps

بدون `wraps`، نام تابع بعد از decorate شدن `wrapper` می‌شود:

```python
from functools import wraps

def my_decorator(func):
    @wraps(func)
    def wrapper(*args, **kwargs):
        return func(*args, **kwargs)
    return wrapper
```

`@wraps(func)` metadata تابع اصلی (`__name__`، `__doc__`) را حفظ می‌کند — برای دیباگ و مستندات مهم است.

---

## کاربردهای رایج

| کاربرد | توضیح |
|--------|-------|
| لاگ | چاپ نام تابع و زمان فراخوانی |
| زمان‌سنجی | اندازه‌گیری مدت اجرا |
| کش (cache) | ذخیره نتیجه برای ورودی تکراری |
| دسترسی | بررسی login قبل از اجرا |
| retry | تلاش مجدد در صورت خطا |

---

## فایل‌های این جلسه

| فایل | توضیح |
|------|-------|
| [examples/01_decorator.py](./examples/01_decorator.py) | decorator ساده قبل/بعد |
| [examples/02_decorator_advanced.py](./examples/02_decorator_advanced.py) | شمارش فراخوانی و wraps |
| [exercises/exercise_01.py](./exercises/exercise_01.py) | تمرین‌های این جلسه |

---

## توضیح کدها

### 📄 `01_decorator.py`

```python
def my_decorator(func):
    def wrapper():
        print("قبل از اجرا")
        func()
        print("بعد از اجرا")
    return wrapper
```

**خط به خط:**
- `my_decorator(func)` — تابع اصلی را به‌عنوان پارامتر می‌گیرد
- `wrapper` — تابع جدیدی که قبل/بعد کار اضافه می‌کند
- `return wrapper` — wrapper جایگزین تابع اصلی می‌شود (با `@`)
- `func()` — بدنه اصلی تابع اجرا می‌شود

---

### 📄 `02_decorator_advanced.py`

```python
@wraps(func)
def wrapper(*args, **kwargs):
    ...
    return func(*args, **kwargs)
```

**خط به خط:**
- `*args, **kwargs` — هر تعداد آرگومان positional و keyword
- `wrapper.calls` — attribute روی خود wrapper برای نگه‌داشتن state
- `@wraps(func)` — نام و docstring تابع اصلی حفظ می‌شود

---

## نکات رایج اشتباهات (Common Pitfalls)

### ❌ اشتباه ۱: فراموش کردن return
```python
def bad_decorator(func):
    def wrapper(*args, **kwargs):
        func(*args, **kwargs)  # ❌ مقدار بازگشتی از بین می‌رود
    return wrapper
```

```python
def good_decorator(func):
    def wrapper(*args, **kwargs):
        return func(*args, **kwargs)  # ✅
    return wrapper
```

### ❌ اشتباه ۲: wrapper بدون args برای تابع با پارامتر
```python
@my_decorator
def add(a, b):
    return a + b

add(1, 2)  # ❌ TypeError اگر wrapper() پارامتر نپذیرد
```

### ❌ اشتباه ۳: decorator خیلی سنگین
```python
# ❌ منطق پیچیده داخل decorator — سخت برای تست
@do_everything_including_database_and_email
def simple_add(a, b):
    return a + b
```

---

## بهترین شیوه‌ها (Best Practices)

- همیشه `return func(...)` را در wrapper فراموش نکنید
- از `functools.wraps` استفاده کنید
- decorator را برای **یک مسئولیت** نگه دارید (فقط لاگ، فقط زمان)
- decoratorهای پیچیده را در ماژول جدا بنویسید
- برای decorator با پارامتر (مثل `@retry(3)`) به الگوی decorator factory نیاز دارید — موضوع پیشرفته‌تر

---

## خلاصه جلسه

در این جلسه یاد گرفتید:
- ✅ decorator تابعی است که تابع دیگر را wrap می‌کند
- ✅ `@decorator` همان `func = decorator(func)` است
- ✅ `*args, **kwargs` برای توابع با پارامتر لازم است
- ✅ `functools.wraps` metadata را حفظ می‌کند
- ✅ کاربرد: لاگ، زمان، شمارش، دسترسی

---

## تمرین‌ها

### تمرین ۱ (ساده): decorator زمان
decorator `log_time` بسازید که **قبل** اجرا «شروع» و **بعد** «پایان» چاپ کند.

### تمرین ۲ (متوسط): شمارش فراخوانی
decorator `count_calls` بسازید که هر بار فراخوانی، شماره را چاپ کند (مثل مثال README).

### تمرین ۳ (پیشرفته): decorator با مقدار بازگشتی
تابعی که عدد برمی‌گرداند decorate کنید و مقدار بازگشتی را هم چاپ کنید.

راه‌حل‌ها در [exercises/exercise_01.py](./exercises/exercise_01.py) موجود است.

---

**جلسه بعدی:** [جلسه ۱۵ — Iterator و Generator](../session-15-iterators-generators/)
