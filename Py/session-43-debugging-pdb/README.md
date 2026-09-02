# جلسه ۴۳: دیباگ با pdb و breakpoint (۱.۵ ساعت)

## 🎯 اهداف یادگیری

پس از این جلسه می‌توانید:
- تفاوت print debugging و debugger تعاملی را بدانید
- از `breakpoint()` (Python 3.7+) استفاده کنید
- دستورات pdb: `n`, `s`, `c`, `l`, `p`, `pp`, `q` را بشناسید
- breakpoint شرطی و post-mortem debugging انجام دهید
- با VS Code debugger کار کنید (مرور)
- logging را به‌عنوان مکمل دیباگ به کار ببرید

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع | فعالیت |
|------|--------|--------|
| 0–10 | انواع باگ | syntax vs logic vs runtime |
| 10–25 | print vs debugger | محدودیت print |
| 25–45 | breakpoint() | توقف و inspect |
| 45–60 | دستورات pdb | step، next، continue |
| 60–75 | post-mortem | pdb.pm() |
| 75–85 | VS Code | break point گرافیکی |
| 85–90 | جمع‌بندی + تکلیف | question.md |

---

## ۱. چرا debugger؟

```python
total = 0
for i in range(5):
    total += i
print(total)  # 10 — اگر 9 بود چرا؟
```

با print باید حدس بزنید کجا breakpoint بگذارید. **Debugger** اجازه می‌دهد:
- execution را متوقف کنید
- هر متغیر را ببینید
- خط‌به‌خط جلو بروید

---

## ۲. breakpoint() — راه مدرن

```python
def divide(a, b):
    breakpoint()  # معادل import pdb; pdb.set_trace()
    return a / b
```

وقتی به این خط برسد، REPL تعاملی pdb باز می‌شود.

**محیط:** `PYTHONBREAKPOINT=0` breakpoint را غیرفعال می‌کند (production).

---

## ۳. دستورات اصلی pdb

| دستور | کوتاه | کار |
|-------|-------|-----|
| next | n | خط بعد (بدون ورود به تابع) |
| step | s | ورود به تابع |
| continue | c | ادامه تا breakpoint بعد |
| list | l | نمایش کد اطراف |
| print | p x | مقدار x |
| pp x | | pretty print |
| where | w | stack trace |
| quit | q | خروج |

---

## ۴. مثال عملی

```python
def process_items(items):
    results = []
    for item in items:
        breakpoint()  # اینجا item و results را inspect کنید
        results.append(item * 2)
    return results
```

در pdb: `p item`, `p results`, `n` برای next.

---

## ۵. breakpoint شرطی (manual)

```python
for i, value in enumerate(data):
    if value < 0:
        breakpoint()
```

یا در VS Code: conditional breakpoint روی `value < 0`.

---

## ۶. post-mortem debugging

وقتی exception رخ داده و برنامه crash کرد:

```python
import pdb

try:
    risky()
except Exception:
    pdb.post_mortem()  # وارد pdb در محل exception
```

یا: `python -m pdb script.py`

---

## ۷. pdb vs IDE debugger

| pdb | VS Code / PyCharm |
|-----|------------------|
| ترمینال | GUI |
| همه‌جا | نیاز IDE |
| سبک | watch، call stack visual |

هر دو مهارت ضروری — pdb روی سرور بدون GUI.

---

## ۸. logging + debug

```python
import logging
logging.basicConfig(level=logging.DEBUG)
logger = logging.getLogger(__name__)

def compute(x):
    logger.debug("input=%s", x)
    result = x * 2
    logger.debug("output=%s", result)
    return result
```

logging برای production trace؛ pdb برای investigation عمیق.

---

## ۹. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_breakpoint_demo.py](./examples/01_breakpoint_demo.py) | breakpoint (commented) |
| [02_debug_commands.py](./examples/02_debug_commands.py) | راهنمای دستورات |
| [03_conditional_debug.py](./examples/03_conditional_debug.py) | توقف شرطی |
| [04_logging_debug.py](./examples/04_logging_debug.py) | logging مکمل |

**نکته:** در مثال ۱ breakpoint comment شده تا اجرای خودکار block نشود.

---

## ۱۰. trace اشتباهات منطقی

```python
def average(numbers):
    total = sum(numbers)
    count = len(numbers)
    # باگ: اگر numbers خالی باشد ZeroDivisionError
    return total / count
```

breakpoint قبل از return → `p count`, `p total`.

---

## ۱۱. watch expression

در IDE: `len(items)`, `item['price']` — در pdb: `p expression`.

---

## ۱۲. اجرای مثال‌ها

```bash
python session-43-debugging-pdb/examples/02_debug_commands.py
python session-43-debugging-pdb/examples/03_conditional_debug.py
python session-43-debugging-pdb/examples/04_logging_debug.py
# برای 01: breakpoint را uncomment کنید
```

---

## ۱۳. اشتباهات رایج

| اشتباه | مشکل | راه‌حل |
|--------|------|--------|
| breakpoint در production | توقف کاربر | env var |
| فقط print | کند و noisy | debugger |
| step در loop بزرگ | خسته‌کننده | conditional bp |
| فراموش q | hang | quit |
| edit کد حین pdb | confusing state | restart |

---

## ۱۴. remote debugging (اشاره)

`debugpy` برای attach از VS Code به process remote — خارج از scope پایه.

---

## ۱۵. traceback خواندن

```
Traceback (most recent call last):
  File "app.py", line 10, in main
    result = divide(10, 0)
  File "app.py", line 3, in divide
    return a / b
ZeroDivisionError: division by zero
```

پایین = محل exception؛ بالا = call chain.

---

## ۱۶. تمرین کلاسی

1. باگ off-by-one در loop — با breakpoint پیدا کنید
2. logging DEBUG برای تابع factorial
3. post-mortem روی KeyError

---

## ۱۷. تکلیف

**تکلیف:** [question.md](./exercises/question.md)

پاسخ: [exercise_01.py](./exercises/exercise_01.py) | [exercise_02.py](./exercises/exercise_02.py)

---

## ۱۸. خلاصه

```
breakpoint()     → pdb.set_trace()
n / s / c        → next / step / continue
p var            → inspect
PYTHONBREAKPOINT=0 → disable
logging.DEBUG    → trace production-safe
traceback        → از پایین بخوانید
```

---

**جلسه قبل:** [۴۲ — Mocking](../session-42-mocking/) | **بعد:** [۴۴ — Performance](../session-44-performance-timeit/)

---

## ۱۹. VS Code launch.json

```json
{
  "configurations": [{
    "name": "Python: Current File",
    "type": "debugpy",
    "request": "launch",
    "program": "${file}"
  }]
}
```

---

## ۲۰. assert برای debug سریع

```python
assert len(items) > 0, f"expected items, got {items}"
```

در production با `python -O` حذف می‌شود — فقط develop.

---

## ۲۱. منابع

- [pdb docs](https://docs.python.org/3/library/pdb.html)
- جلسه ۸ — error handling
- جلسه ۲۷ — logging
