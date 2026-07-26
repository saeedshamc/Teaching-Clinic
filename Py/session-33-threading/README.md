# جلسه ۳۳: چندریسمانی (Threading)

## اهداف یادگیری (Learning Objectives)

پس از این جلسه می‌توانید:
- مفهوم thread را درک کنید
- چند کار را هم‌زمان نسبی اجرا کنید
- محدودیت GIL را در سطح مفهومی بشناسید

---

## Thread چیست؟

Thread به برنامه اجازه می‌دهد چند مسیر اجرایی داشته باشد؛ برای کارهای I/O مثل انتظار شبکه مفید است.

```python
import threading
import time

def worker(name):
    print("شروع", name)
    time.sleep(0.1)
    print("پایان", name)

t1 = threading.Thread(target=worker, args=("الف",))
t2 = threading.Thread(target=worker, args=("ب",))
t1.start(); t2.start()
t1.join(); t2.join()
```

### توضیح
- `Thread` → یک ریسمان جدید می‌سازد
- `start` → اجرا را آغاز می‌کند
- `join` → منتظر اتمام می‌ماند

---

## فایل‌های این جلسه
- [examples/01_threading.py](./examples/01_threading.py)
- [exercises/exercise_01.py](./exercises/exercise_01.py)

## تمرین‌ها
- تمرین ساده: یک thread ساده بسازید
- تمرین متوسط: سه thread را start و join کنید
