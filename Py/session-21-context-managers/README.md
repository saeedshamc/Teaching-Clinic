# جلسه ۲۱: Context Managerها (۱.۵ ساعت)

## ۰. مرور

در جلسه ۷ با `open` و فایل کار کردید. `with open(...)` یک context manager داخلی Python است — امروز مکانیزم و ساخت CM سفارشی.

---

## 🎯 اهداف یادگیری

پس از این جلسه می‌توانید:
- مفهوم context manager و پروتکل `__enter__`/`__exit__` را درک کنید
- از دستور `with` برای مدیریت امن منابع استفاده کنید
- context manager با `@contextmanager` از `contextlib` بسازید
- کلاس-based context manager با `__enter__` و `__exit__` بنویسید
- `contextlib.suppress` و `ExitStack` را بشناسید
- بدانید چرا `with open()` از فراموش کردن `close` جلوگیری می‌کند

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع | فعالیت |
|------|--------|--------|
| 0–10 | مرور | open/close — مشکل resource leak |
| 10–25 | with و open | فایل امن |
| 25–40 | @contextmanager | yield pattern |
| 40–55 | کلاس-based CM | __enter__/__exit__ |
| 55–70 | suppress و nesting | چند CM |
| 70–85 | تمرین | timer CM |
| 85–90 | جمع‌بندی + تکلیف |

---

## ۱. Context Manager چیست؟

Context Manager شیئی است که **ورود و خروج** از یک بلوک را مدیریت می‌کند — معمولاً برای acquire/release منابع.

**تشبیه:** اتاق کنفرانس — با کارت وارد می‌شوید (enter)، کار می‌کنید، با خروج چراغ‌ها خاموش می‌شود (exit) — حتی اگر وسط کار خطا رخ دهد.

```python
with open("file.txt", "w", encoding="utf-8") as f:
    f.write("سلام")
# فایل خودکار بسته شد — حتی اگر exception رخ دهد
```

---

## ۲. پروتکل __enter__ و __exit__

```python
class MyContext:
    def __enter__(self):
        print("ورود")
        return self  # مقدار as

    def __exit__(self, exc_type, exc_val, exc_tb):
        print("خروج")
        return False  # exception propagate شود

with MyContext() as ctx:
    print("بدنه")
```

---

## ۳. @contextmanager — روش generator

```python
from contextlib import contextmanager

@contextmanager
def timer():
    import time
    start = time.perf_counter()
    yield
    print(f"زمان: {time.perf_counter() - start:.3f}s")

with timer():
    sum(range(1_000_000))
```

`yield` مرز enter/exit است — قبل yield = enter، بعد yield = exit.

---

## ۴. contextmanager با مقدار

```python
@contextmanager
def temporary_value(obj, attr, new_val):
    old = getattr(obj, attr)
    setattr(obj, attr, new_val)
    try:
        yield
    finally:
        setattr(obj, attr, old)
```

---

## ۵. contextlib.suppress

```python
from contextlib import suppress

with suppress(FileNotFoundError):
    Path("missing.txt").unlink()
# خطا نادیده گرفته می‌شود
```

---

## ۶. ExitStack — چند CM پویا

```python
from contextlib import ExitStack

with ExitStack() as stack:
    f1 = stack.enter_context(open("a.txt"))
    f2 = stack.enter_context(open("b.txt"))
    # هر دو در خروج بسته می‌شوند
```

---

## ۷. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_context_manager.py](./examples/01_context_manager.py) | with و @contextmanager |
| [02_class_based_cm.py](./examples/02_class_based_cm.py) | کلاس __enter__/__exit__ |
| [03_timer_cm.py](./examples/03_timer_cm.py) | زمان‌سنجی |
| [04_suppress_exitstack.py](./examples/04_suppress_exitstack.py) | suppress و ExitStack |

---

## ۸. اشتباهات رایج (Pitfalls)

### ❌ فراموش کردن finally در CM دستی
`with` خودکار finally دارد.

### ❌ yield دو بار در @contextmanager
```python
@contextmanager
def bad():
    yield 1
    yield 2  # ❌ RuntimeError
```

### ❌ __exit__ بدون cleanup در exception
```python
def __exit__(self, exc_type, exc_val, exc_tb):
    self.cleanup()  # ✅ همیشه cleanup
    return False
```

---

## ۹. خلاصه

- ✅ `with` = enter + body + exit (حتی با exception)
- ✅ `@contextmanager` + yield
- ✅ کلاس: `__enter__` / `__exit__`
- ✅ suppress برای نادیده گرفتن exception

---

## ۱۰. تکلیف

راهنما: [exercises/question.md](./exercises/question.md)  
پاسخ: [exercise_01.py](./exercises/exercise_01.py) | [exercise_02.py](./exercise_02.py)

---

## ۱۰. CM داخلی Python

| CM | کاربرد |
|----|--------|
| `open()` | فایل |
| `threading.Lock()` | lock |
| `decimal.localcontext()` | precision |
| `@contextmanager` | سفارشی |

---

## ۱۱. __exit__ و exception

```python
def __exit__(self, exc_type, exc_val, exc_tb):
    self.cleanup()
    return False  # False = exception propagate
    # return True = exception بلعیده می‌شود (معمولاً نکنید)
```

---

## ۱۲. توضیح فایل‌های مثال

### 📄 `01_context_manager.py` — with open و @contextmanager
### 📄 `02_class_based_cm.py` — FileLogger و Counter
### 📄 `03_timer_cm.py` — زمان‌سنج با yield
### 📄 `04_suppress_exitstack.py` — suppress و ExitStack

---

## ۱۳. سوالات کلاسی

1. `with` چه مزیتی نسبت به try/finally دارد؟
2. `@contextmanager` چند بار yield مجاز است؟
3. ExitStack چه زمانی لازم است؟

---

## ۱۴. try/finally vs with

```python
# ❌ verbose
f = open("x.txt")
try:
    f.write("data")
finally:
    f.close()

# ✅ with
with open("x.txt", "w") as f:
    f.write("data")
```

---

## راهنمای مدرس (۱۵ دقیقه)

- قبل از شروع: مرور کوتاه جلسه قبل و اهداف روی تخته
- حین جلسه: دانشجو کد را خودش تایپ کند — copy/paste نکند
- بعد از تمرین: ۵ دقیقه Q&A و اشاره به exercises/question.md
- تکلیف خانه: exercise_02.py — deadline هفته بعد
- ارزیابی: حضور در تمرین کلاسی + تحویل exercise_02

---

## منابع تکمیلی

- [contextlib — Python docs](https://docs.python.org/3/library/contextlib.html)
- [PEP 343 — The with Statement](https://peps.python.org/pep-0343/)

### چک‌لیست پایان جلسه

- [ ] with open
- [ ] @contextmanager
- [ ] class __enter__/__exit__
- [ ] suppress و ExitStack
- [ ] timer CM در تمرین
- [ ] FileLogger class-based

---

## نکات تکمیلی برای مدرس

مقایسه `@contextmanager` vs class — برای CM ساده generator کافی، برای stateful از class. `with open` را با exception وسط بلوک demo کنید — فایل still closed.

### اجرای تمرین‌ها

```bash
python session-21-context-managers/examples/03_timer_cm.py
python session-21-context-managers/exercises/exercise_01.py
```

Context manager پایه `with` در تمام فصل‌های بعدی (فایل، DB، lock) تکرار می‌شود.

### الگوی رایج

```python
with resource() as r:
    r.do_work()
# cleanup automatic
```

همیشه prefer `with` بر try/finally دستی برای resource management.

PEP 343 استاندارد `with` statement را معرفی کرد.

---

**جلسه بعدی:** [جلسه ۲۲ — Lambda و توابع تابعی](../session-22-lambda-functional/)
