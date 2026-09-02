# جلسه ۱۴: Decoratorها (۱.۵ ساعت)

## 🎯 اهداف یادگیری

پس از این جلسه می‌توانید:
- مفهوم decorator را با یک تشبیه روزمره توضیح دهید
- تفاوت «تابع معمولی که تابع می‌گیرد» و «decorator با @» را بدانید
- یک decorator ساده بدون آرگومان بنویسید
- decorator با `*args` و `**kwargs` برای توابع با پارامتر بسازید
- از `functools.wraps` برای حفظ نام و docstring تابع اصلی استفاده کنید
- decorator factory (decorator با پارامتر) را بشناسید
- کاربردهای رایج (لاگ، زمان‌سنجی، شمارش فراخوانی) را پیاده کنید

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع | فعالیت |
|------|--------|--------|
| 0–10 | مرور | توابع به‌عنوان first-class object — جلسه ۶ |
| 10–25 | مفهوم decorator | wrap کردن تابع، `@` syntax |
| 25–40 | args و kwargs | decorator برای توابع با پارامتر |
| 40–55 | functools.wraps | حفظ metadata، زمان‌سنجی |
| 55–70 | decorator factory | `@retry(3)` — decorator با پارامتر |
| 70–85 | تمرین کلاسی | لاگ، شمارش، cache ساده |
| 85–90 | جمع‌بندی | اشتباهات رایج + تکلیف |

---

## ۱. Decorator چیست؟

Decorator تابعی است که **رفتار یک تابع دیگر را بدون تغییر بدنه آن** گسترش می‌دهد.

**تشبیه:** فرض کنید یک هدیه دارید. قبل از دادنش، کاغذ کادو می‌پیچید — هدیه همان است، ولی **قبل و بعد** کار اضافه (پیچیدن، پاپیون) انجام شده. Decorator هم «کاغذ کادو» برای تابع است.

### توابع first-class در Python

```python
def greet():
    print("سلام")

# تابع را می‌توان به متغیر داد
say = greet
say()  # سلام

# تابع را می‌توان به تابع دیگر داد
def run_twice(func):
    func()
    func()

run_twice(greet)
```

---

## ۲. بدون @ — درک مکانیزم

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

**خروجی:**
```
قبل از اجرا
سلام
بعد از اجرا
```

### با @ — syntax استاندارد

```python
@my_decorator
def say_hello():
    print("سلام")
```

`@my_decorator` در واقع همان `say_hello = my_decorator(say_hello)` است — فقط خواناتر.

---

## ۳. Decorator و پارامترهای تابع

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

## ۴. functools.wraps

بدون `wraps`، نام تابع بعد از decorate شدن `wrapper` می‌شود:

```python
from functools import wraps

def my_decorator(func):
    @wraps(func)
    def wrapper(*args, **kwargs):
        return func(*args, **kwargs)
    return wrapper

@my_decorator
def add(a, b):
    """جمع دو عدد"""
    return a + b

print(add.__name__)  # add (نه wrapper)
print(add.__doc__)   # جمع دو عدد
```

`@wraps(func)` metadata تابع اصلی (`__name__`، `__doc__`) را حفظ می‌کند — برای دیباگ و مستندات مهم است.

---

## ۵. decorator زمان‌سنجی

```python
import time
from functools import wraps

def timer(func):
    @wraps(func)
    def wrapper(*args, **kwargs):
        start = time.perf_counter()
        result = func(*args, **kwargs)
        elapsed = time.perf_counter() - start
        print(f"{func.__name__} در {elapsed:.4f} ثانیه")
        return result
    return wrapper

@timer
def slow_sum(n):
    return sum(range(n))

print(slow_sum(1_000_000))
```

---

## ۶. Decorator Factory — decorator با پارامتر

گاهی decorator خودش پارامتر می‌گیرد: `@retry(3)`

```python
from functools import wraps

def repeat(times):
    """decorator factory — times بار تابع را اجرا می‌کند"""
    def decorator(func):
        @wraps(func)
        def wrapper(*args, **kwargs):
            for _ in range(times):
                result = func(*args, **kwargs)
            return result
        return wrapper
    return decorator

@repeat(3)
def say_hi():
    print("سلام")

say_hi()  # سه بار «سلام» چاپ می‌شود
```

**ساختار:** سه لایه — factory → decorator → wrapper

---

## ۷. چند decorator روی یک تابع

```python
@timer
@count_calls
def work():
    print("کار انجام شد")
```

معادل: `work = timer(count_calls(work))` — از پایین به بالا اعمال می‌شود.

---

## ۸. کاربردهای رایج

| کاربرد | توضیح |
|--------|-------|
| لاگ | چاپ نام تابع و زمان فراخوانی |
| زمان‌سنجی | اندازه‌گیری مدت اجرا |
| کش (cache) | `@functools.lru_cache` — ذخیره نتیجه |
| دسترسی | بررسی login قبل از اجرا |
| retry | تلاش مجدد در صورت خطا |
| validation | بررسی نوع یا محدوده پارامترها |

### lru_cache — decorator داخلی Python

```python
from functools import lru_cache

@lru_cache(maxsize=None)
def fib(n):
    if n < 2:
        return n
    return fib(n - 1) + fib(n - 2)

print(fib(100))  # سریع — نتایج cache شده
```

---

## ۹. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_decorator.py](./examples/01_decorator.py) | decorator ساده قبل/بعد |
| [02_decorator_advanced.py](./examples/02_decorator_advanced.py) | شمارش فراخوانی و wraps |
| [03_timer_logging.py](./examples/03_timer_logging.py) | زمان‌سنجی و لاگ |
| [04_decorator_factory.py](./examples/04_decorator_factory.py) | factory و lru_cache |

---

## ۱۰. اشتباهات رایج (Pitfalls)

### ❌ فراموش کردن return
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

### ❌ wrapper بدون args برای تابع با پارامتر
```python
@my_decorator
def add(a, b):
    return a + b

add(1, 2)  # ❌ TypeError اگر wrapper() پارامتر نپذیرد
```

### ❌ decorator خیلی سنگین
```python
# ❌ منطق پیچیده داخل decorator — سخت برای تست
@do_everything_including_database_and_email
def simple_add(a, b):
    return a + b
```

### ❌ فراموش کردن @wraps
```python
# ❌ بعد از decorate، help(func) نام wrapper را نشان می‌دهد
# ✅ همیشه @wraps(func) در wrapper
```

---

## ۱۱. بهترین شیوه‌ها

- همیشه `return func(...)` را در wrapper فراموش نکنید
- از `functools.wraps` استفاده کنید
- decorator را برای **یک مسئولیت** نگه دارید (فقط لاگ، فقط زمان)
- decoratorهای پیچیده را در ماژول جدا بنویسید
- برای cache ساده از `@lru_cache` استاندارد استفاده کنید
- decorator factory را فقط وقتی decorator واقعاً پارامتر نیاز دارد به‌کار ببرید

---

## ۱۲. خلاصه جلسه

در این جلسه یاد گرفتید:
- ✅ decorator تابعی است که تابع دیگر را wrap می‌کند
- ✅ `@decorator` همان `func = decorator(func)` است
- ✅ `*args, **kwargs` برای توابع با پارامتر لازم است
- ✅ `functools.wraps` metadata را حفظ می‌کند
- ✅ decorator factory برای `@decorator(arg)` سه لایه دارد
- ✅ کاربرد: لاگ، زمان، شمارش، cache، دسترسی

---

## ۱۳. تکلیف خانه

### ساده (۱۵ دقیقه)
decorator `log_time` بسازید که **قبل** اجرا «شروع» و **بعد** «پایان» چاپ کند.

### متوسط (۳۰ دقیقه)
decorator `count_calls` بسازید که هر بار فراخوانی، شماره را چاپ کند و attribute `calls` داشته باشد.

### پیشرفته (۴۵ دقیقه)
decorator factory `@validate_positive` بسازید که اگر هر آرگومان عددی منفی باشد، `ValueError` بدهد.

راهنما: [exercises/question.md](./exercises/question.md)  
پاسخ نمونه: [exercise_01.py](./exercises/exercise_01.py) | [exercise_02.py](./exercises/exercise_02.py)

---

**جلسه بعدی:** [جلسه ۱۵ — Iterator و Generator](../session-15-iterators-generators/)
