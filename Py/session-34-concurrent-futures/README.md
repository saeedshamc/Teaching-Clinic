# جلسه ۳۴: Concurrent Futures — ۱.۵ ساعت

## 🎯 اهداف یادگیری

- `concurrent.futures` — API سطح بالا
- `ThreadPoolExecutor` — I/O-bound
- `ProcessPoolExecutor` — CPU-bound
- `submit`, `map`, `as_completed`
- `Future` — result, exception, timeout
- `wait` و `FIRST_COMPLETED`
- الگوهای real-world: batch processing, parallel download

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | چرا futures؟ |
| 10–25 | ThreadPoolExecutor |
| 25–40 | map vs submit |
| 40–55 | as_completed و exception |
| 55–70 | ProcessPoolExecutor |
| 70–85 | تمرین |
| 85–90 | جمع‌بندی |

---

## ۱. Future چیست؟

Future = وعده نتیجه async. وقتی task تمام شد، `.result()` مقدار را برمی‌گرداند.

---

## ۲. ThreadPoolExecutor

```python
from concurrent.futures import ThreadPoolExecutor
import time

def job(n):
    time.sleep(0.1)
    return n * n

with ThreadPoolExecutor(max_workers=4) as pool:
    results = list(pool.map(job, range(10)))
print(results)
```

---

## ۳. submit

```python
with ThreadPoolExecutor(max_workers=3) as pool:
    fut = pool.submit(job, 5)
    print(fut.result())  # block تا آماده شود
```

---

## ۴. as_completed

```python
from concurrent.futures import as_completed

with ThreadPoolExecutor(max_workers=3) as pool:
    futures = [pool.submit(job, i) for i in range(5)]
    for fut in as_completed(futures):
        print(fut.result())
```

ترتیب completion، نه ترتیب submit.

---

## ۵. exception handling

```python
def risky(x):
    if x == 0:
        raise ValueError("صفر!")
    return 10 / x

with ThreadPoolExecutor() as pool:
    fut = pool.submit(risky, 0)
    try:
        fut.result()
    except ValueError as e:
        print(e)
```

---

## ۶. timeout

```python
fut = pool.submit(slow_job)
try:
    result = fut.result(timeout=2.0)
except TimeoutError:
    print("زمان تمام شد")
    fut.cancel()
```

---

## ۷. ProcessPoolExecutor

```python
from concurrent.futures import ProcessPoolExecutor

def cpu_heavy(n):
    return sum(i * i for i in range(n))

with ProcessPoolExecutor(max_workers=4) as pool:
    results = pool.map(cpu_heavy, [10**6] * 4)
```

**نکته:** تابع باید picklable باشد — top-level function.

---

## ۸. wait

```python
from concurrent.futures import wait, FIRST_COMPLETED

done, not_done = wait(futures, return_when=FIRST_COMPLETED)
```

---

## ۹. map با chunksize

```python
pool.map(func, items, chunksize=100)
```

برای ProcessPool — batch بزرگ‌تر = overhead کمتر.

---

## ۱۰. shutdown

```python
pool = ThreadPoolExecutor(max_workers=2)
# ...
pool.shutdown(wait=True)  # context manager خودکار
```

---

## ۱۱. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_thread_pool_map.py](./examples/01_thread_pool_map.py) | map |
| [02_submit_as_completed.py](./examples/02_submit_as_completed.py) | submit |
| [03_exceptions_timeout.py](./examples/03_exceptions_timeout.py) | error handling |
| [04_process_pool.py](./examples/04_process_pool.py) | CPU parallel |

---

## ۱۲. Thread vs Process pool

| | ThreadPool | ProcessPool |
|---|------------|-------------|
| GIL | محدود CPU | bypass GIL |
| memory | shared | جدا |
| startup | سریع | کندتر |
| use | I/O | CPU |

---

## ۱۳. اشتباهات رایج

| اشتباه | مشکل |
|--------|------|
| lambda در ProcessPool | not picklable |
| result() بدون exception handle | crash |
| max_workers خیلی زیاد | context switch |
| nested pools | deadlock risk |

---

## ۱۴. تمرین کلاسی

1. map روی لیست اعداد — مربع
2. submit + as_completed
3. exception در یک future
4. مقایسه sequential vs pool timing

**تکلیف:** [question.md](./exercises/question.md)

---

## ۱۵. asyncio vs futures

`asyncio` برای coroutine؛ `concurrent.futures` برای sync functions در thread/process.

---

## ۱۶. Executor.map vs submit

| | map | submit |
|---|-----|--------|
| ترتیب نتیجه | حفظ input order | as_completed |
| exception | اولین fail | per future |
| use | batch یکسان | mixed tasks |

---

## ۱۷. callback add_done_callback

```python
def done(fut):
    print(fut.result())

fut = pool.submit(job, 5)
fut.add_done_callback(done)
```

---

## ۱۸. سوالات کلاسی

- ThreadPool vs ProcessPool؟
- Future.result() چه زمانی block می‌کند؟
- max_workers چگونه انتخاب شود؟

---

## ۱۹. ProcessPool guard

```python
if __name__ == "__main__":
    main()  # الزامی Windows
```

---

## ۲۰. as_completed با timeout

```python
for fut in as_completed(futures, timeout=30):
    ...
```

---

## ۲۱. chunksize tuning

```python
# ProcessPool — chunksize بزرگ‌تر = کمتر pickle overhead
pool.map(func, items, chunksize=50)
```

---

## ۲۲. جمع‌بندی

concurrent.futures **API یکپارچه** برای thread و process — map برای batch، submit برای کنترل fine-grained.

**تکلیف خانه:** [question.md](./exercises/question.md) — parallel URL fetch.

### چک‌لیست پایان جلسه

- [ ] ThreadPoolExecutor.map
- [ ] submit + as_completed
- [ ] exception در future
- [ ] timing مقایسه شد

**زمان باقی‌مانده:** Q&A — ProcessPool روی Windows و `if __name__`.

| مثال | فایل |
|------|------|
| map | 01_thread_pool_map.py |
| submit | 02_submit_as_completed.py |
| errors | 03_exceptions_timeout.py |
| CPU | 04_process_pool.py |

**جلسه بعد:** HTTP Client — ارتباط با API و وب‌سرویس‌ها.

---

## 📚 مطالعه تکمیلی

- [concurrent.futures — Python docs](https://docs.python.org/3/library/concurrent.futures.html)

---

**جلسه قبل:** [۳۳ — Threading](../session-33-threading/) | **بعد:** [۳۵ — HTTP Client](../session-35-http-client/)
