# جلسه ۱۷: AsyncIO (۱.۵ ساعت)

## ۰. مرور

Threading (جلسه ۳۳) parallel واقعی با thread است. AsyncIO **cooperative multitasking** در یک thread — مناسب I/O زیاد، نه CPU.

---

## 🎯 اهداف یادگیری

پس از این جلسه می‌توانید:
- تفاوت **هم‌زمان (sync)** و **ناهم‌زمان (async)** را توضیح دهید
- coroutine با `async def` و `await` بنویسید
- برنامه async را با `asyncio.run()` اجرا کنید
- چند coroutine را با `asyncio.gather` هم‌زمان اجرا کنید
- `asyncio.sleep` را از `time.sleep` تشخیص دهید
- بدانید async برای I/O مناسب است نه CPU-intensive
- timeout و exception در async را بشناسید

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع | فعالیت |
|------|--------|--------|
| 0–10 | مرور | blocking vs non-blocking |
| 10–25 | async/await پایه | coroutine، asyncio.run |
| 25–40 | asyncio.sleep | انتظار بدون block |
| 40–55 | gather | اجرای موازی |
| 55–70 | create_task | task و cancel |
| 70–85 | تمرین | چند task هم‌زمان |
| 85–90 | جمع‌بندی + تکلیف |

---

## ۱. AsyncIO چیست؟

در برنامه‌نویسی **هم‌زمان (synchronous)**، وقتی یک کار منتظر چیزی می‌ماند — مثلاً دریافت داده از شبکه — کل برنامه **متوقف (block)** می‌شود.

**AsyncIO** به شما اجازه می‌دهد در زمان انتظار یک کار، کار دیگری پیش برود — بدون thread اضافه.

**تشبیه:** آشپز که منتظر جوشیدن آب است، به‌جای ایستادن، سالاد درست می‌کند.

```python
import asyncio

async def greet():
    print("سلام")
    await asyncio.sleep(0.1)  # منتظر — ولی event loop آزاد است
    print("دنیا")

asyncio.run(greet())
```

---

## ۲. مفاهیم کلیدی

| مفهوم | توضیح |
|-------|-------|
| `async def` | تعریف coroutine |
| `await` | منتظر اتمام coroutine دیگر |
| `asyncio.run()` | نقطه ورود — event loop را می‌سازد |
| `asyncio.sleep()` | sleep ناهم‌زمان — thread را block نمی‌کند |
| coroutine | تابع async که هنوز اجرا نشده |

---

## ۳. اجرای هم‌زمان با gather

```python
import asyncio

async def task(name, delay):
    print(f"شروع {name}")
    await asyncio.sleep(delay)
    print(f"پایان {name}")
    return name

async def main():
    results = await asyncio.gather(
        task("الف", 0.2),
        task("ب", 0.1),
    )
    print("نتایج:", results)

asyncio.run(main())
```

**خروجی:** ب قبل از الف تمام می‌شود (delay کمتر) — هر دو **هم‌زمان** شروع می‌شوند.

---

## ۴. create_task — اجرای پس‌زمینه

```python
async def main():
    task1 = asyncio.create_task(task("A", 1))
    task2 = asyncio.create_task(task("B", 0.5))
    await task1
    await task2
```

`create_task` coroutine را فوراً schedule می‌کند.

---

## ۵. async vs sync — زمان

```python
import asyncio
import time

async def fetch_data(id, delay):
    await asyncio.sleep(delay)
    return f"data-{id}"

async def main_async():
    start = time.perf_counter()
    await asyncio.gather(
        fetch_data(1, 1),
        fetch_data(2, 1),
        fetch_data(3, 1),
    )
    print(f"async: {time.perf_counter() - start:.1f}s")  # ~1s

def main_sync():
    start = time.perf_counter()
    for i in range(1, 4):
        time.sleep(1)
    print(f"sync: {time.perf_counter() - start:.1f}s")  # ~3s
```

---

## ۶. timeout

```python
async def slow():
    await asyncio.sleep(10)

async def main():
    try:
        await asyncio.wait_for(slow(), timeout=2.0)
    except asyncio.TimeoutError:
        print("زمان تمام شد!")
```

---

## ۷. چه زمانی async؟

| مناسب ✅ | نامناسب ❌ |
|----------|------------|
| HTTP request | محاسبات سنگین CPU |
| خواندن/نوشتن فایل | پردازش تصویر |
| database query | حلقه‌های عددی بزرگ |
| WebSocket | |

برای CPU-intensive: `multiprocessing` یا `concurrent.futures`.

---

## ۸. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_asyncio.py](./examples/01_asyncio.py) | async/await و gather |
| [02_sync_vs_async.py](./examples/02_sync_vs_async.py) | مقایسه زمان |
| [03_create_task.py](./examples/03_create_task.py) | task و cancel |
| [04_timeout_exception.py](./examples/04_timeout_exception.py) | timeout و خطا |

---

## ۹. اشتباهات رایج (Pitfalls)

### ❌ فراموش کردن await
```python
async def bad():
    asyncio.sleep(1)  # ❌ coroutine ساخته شد ولی await نشد!
```

### ❌ time.sleep در async
```python
async def bad():
    time.sleep(5)  # ❌ کل event loop را block می‌کند
    await asyncio.sleep(5)  # ✅
```

### ❌ فراموش کردن asyncio.run
```python
async def main():
    ...

main()  # ❌ coroutine object — اجرا نمی‌شود
asyncio.run(main())  # ✅
```

---

## ۱۰. خلاصه

- ✅ `async def` + `await` + `asyncio.run()`
- ✅ `gather` برای اجرای موازی I/O
- ✅ async برای I/O، نه CPU
- ✅ `asyncio.sleep` نه `time.sleep`

---

## ۱۱. تکلیف

راهنما: [exercises/question.md](./exercises/question.md)  
پاسخ: [exercise_01.py](./exercises/exercise_01.py) | [exercise_02.py](./exercises/exercise_02.py)

---

## ۱۲. Event Loop — مفهوم پشت صحنه

`asyncio.run(main())` یک **event loop** می‌سازد:
1. coroutineها را schedule می‌کند
2. وقتی یکی `await` می‌زند، control به loop برمی‌گردد
3. coroutine آماده دیگر اجرا می‌شود

**نکته:** در یک thread — نه parallel واقعی CPU.

---

## ۱۳. asyncio.run vs get_event_loop

```python
# ✅ Python 3.7+ — همیشه از این استفاده کنید
asyncio.run(main())

# ❌ قدیمی — در اسکریپت ساده لازم نیست
loop = asyncio.get_event_loop()
loop.run_until_complete(main())
```

---

## ۱۴. توضیح فایل‌های مثال

### 📄 `01_asyncio.py` — greet و gather پایه
### 📄 `02_sync_vs_async.py` — 3×1s sync vs ~1s async
### 📄 `03_create_task.py` — create_task و cancel
### 📄 `04_timeout_exception.py` — wait_for و return_exceptions

---

## ۱۵. سوالات کلاسی

1. `await asyncio.sleep(1)` با `time.sleep(1)` در async چه فرقی دارد؟
2. چرا async برای محاسبات سنگین CPU مناسب نیست؟
3. `gather` و `create_task` چه زمانی؟

---

## ۱۶. مقایسه async با threading

| | AsyncIO | Threading |
|---|---------|-----------|
| Parallel CPU | ❌ | محدود (GIL) |
| I/O concurrent | ✅ | ✅ |
| پیچیدگی | await/async | lock/race |
| حافظه | کم | بیشتر |

---

## راهنمای مدرس (۱۵ دقیقه)

- قبل از شروع: مرور کوتاه جلسه قبل و اهداف روی تخته
- حین جلسه: دانشجو کد را خودش تایپ کند — copy/paste نکند
- بعد از تمرین: ۵ دقیقه Q&A و اشاره به exercises/question.md
- تکلیف خانه: exercise_02.py — deadline هفته بعد
- ارزیابی: حضور در تمرین کلاسی + تحویل exercise_02

---

## منابع تکمیلی

- [asyncio — Python docs](https://docs.python.org/3/library/asyncio.html)
- [Real Python — Async IO](https://realpython.com/async-io-python/)

### چک‌لیست پایان جلسه

- [ ] async/await و asyncio.run
- [ ] gather با ۲ task
- [ ] timeout با wait_for
- [ ] exercise_01 و exercise_02

---

**جلسه بعدی:** [جلسه ۱۸ — تست‌نویسی](../session-18-testing/)
