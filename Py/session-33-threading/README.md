# جلسه ۳۳: چندریسمانی (Threading) — ۱.۵ ساعت

## 🎯 اهداف یادگیری

- مفهوم thread و process
- `threading.Thread` — start, join
- GIL و محدودیت‌های CPU-bound
- `Lock`, `RLock` — thread safety
- `Event`, `Condition` (مقدمه)
- `threading.local` و daemon threads
- الگوهای real-world: worker pool ساده، download موازی

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | concurrency vs parallelism |
| 10–25 | Thread پایه |
| 25–40 | join و daemon |
| 40–55 | Lock و race condition |
| 55–70 | Event و Queue |
| 70–85 | تمرین |
| 85–90 | جمع‌بندی |

---

## ۱. Process vs Thread

| | Process | Thread |
|---|---------|--------|
| حافظه | جدا | مشترک |
| overhead | بیشتر | کمتر |
| GIL | هر process جدا | یک GIL |
| مناسب | CPU-heavy | I/O-heavy |

---

## ۲. Thread پایه

```python
import threading
import time

def worker(name, delay):
    print(f"شروع {name}")
    time.sleep(delay)
    print(f"پایان {name}")

t = threading.Thread(target=worker, args=("A", 0.5))
t.start()
t.join()
print("تمام")
```

---

## ۳. چند thread

```python
threads = []
for i in range(3):
    t = threading.Thread(target=worker, args=(f"T{i}", 0.2))
    threads.append(t)
    t.start()
for t in threads:
    t.join()
```

---

## ۴. GIL

Global Interpreter Lock — فقط یک thread Python bytecode اجرا می‌کند.

- CPU-bound: threading کمک نمی‌کند → `multiprocessing`
- I/O-bound: threading مفید است (sleep, network, disk)

---

## ۵. Race condition

```python
counter = 0

def increment():
    global counter
    for _ in range(100000):
        counter += 1  # ناامن!

threads = [threading.Thread(target=increment) for _ in range(2)]
# counter < 200000
```

---

## ۶. Lock

```python
lock = threading.Lock()
counter = 0

def safe_increment():
    global counter
    with lock:
        for _ in range(100000):
            counter += 1
```

---

## ۷. RLock

Reentrant lock — همان thread می‌تواند دوباره acquire کند.

---

## ۸. Event

```python
event = threading.Event()

def waiter():
    print("منتظر...")
    event.wait()
    print("ادامه!")

threading.Thread(target=waiter).start()
time.sleep(1)
event.set()
```

---

## ۹. Queue — producer/consumer

```python
from queue import Queue

q = Queue()

def producer():
    for i in range(5):
        q.put(i)

def consumer():
    while True:
        item = q.get()
        if item is None:
            break
        print(item)
        q.task_done()
```

---

## ۱۰. daemon thread

```python
t = threading.Thread(target=background, daemon=True)
t.start()
# با exit برنامه main، daemon کشته می‌شود
```

---

## ۱۱. threading.local

```python
local = threading.local()

def worker():
    local.value = threading.current_thread().name
    print(local.value)
```

---

## ۱۲. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_thread_basics.py](./examples/01_thread_basics.py) | start/join |
| [02_lock_race.py](./examples/02_lock_race.py) | race و Lock |
| [03_queue_workers.py](./examples/03_queue_workers.py) | producer/consumer |
| [04_io_simulation.py](./examples/04_io_simulation.py) | I/O موازی |

---

## ۱۳. اشتباهات رایج

| اشتباه | مشکل |
|--------|------|
| فراموش join | برنامه زود تمام می‌شود |
| threading برای CPU | GIL — کندتر |
| Lock بدون with | deadlock risk |
| shared mutable بدون lock | race |

---

## ۱۴. تمرین کلاسی

1. دو thread با sleep
2. Lock برای counter
3. Queue با 1 producer و 2 consumer
4. مقایسه sequential vs threaded I/O

**تکلیف:** [question.md](./exercises/question.md)

---

## ۱۵. threading vs asyncio

| | threading | asyncio |
|---|-----------|---------|
| model | preemptive OS threads | cooperative coroutines |
| blocking code | OK | needs async |
| scale | محدود (threads) | هزاران task |

---

## ۱۶. ThreadPoolExecutor در threading

```python
# session-33 و 34 مرتبط — ThreadPool سطح بالاتر
from concurrent.futures import ThreadPoolExecutor
```

---

## ۱۷. Semaphore

```python
sem = threading.Semaphore(3)  # حداکثر 3 هم‌زمان
with sem:
    do_work()
```

---

## ۱۸. سوالات کلاسی

- GIL چه محدودیتی ایجاد می‌کند؟
- چه زمانی Lock لازم است؟
- daemon thread چیست؟

---

## ۱۹. threading.main_thread

```python
import threading
print(threading.main_thread().name)
print(threading.active_count())
```

---

## ۲۰. Timer thread

```python
threading.Timer(2.0, callback).start()
```

---

## ۲۱. جمع‌بندی

threading برای **I/O موازی** — CPU سنگین → multiprocessing یا concurrent.futures ProcessPool.

**تکلیف خانه:** [question.md](./exercises/question.md) — BankAccount thread-safe.

### چک‌لیست پایان جلسه

- [ ] Thread start/join
- [ ] race condition مشاهده شد
- [ ] Lock اعمال شد
- [ ] GIL توضیح داده شد

---

## 📚 مطالعه تکمیلی

- [threading — Python docs](https://docs.python.org/3/library/threading.html)

---

**جلسه قبل:** [۳۲ — SQLite](../session-32-sqlite/) | **بعد:** [۳۴ — Concurrent Futures](../session-34-concurrent-futures/)
