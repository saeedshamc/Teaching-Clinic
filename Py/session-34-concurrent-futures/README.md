# جلسه ۳۴: Concurrent Futures

## اهداف یادگیری (Learning Objectives)

پس از این جلسه می‌توانید:
- با ThreadPoolExecutor کار کنید
- چند وظیفه را ساده‌تر مدیریت کنید
- نتیجه futures را جمع‌آوری کنید

---

## concurrent.futures چیست؟

این ماژول رابط سطح‌بالاتری برای اجرای هم‌زمان کارها با thread یا process ارائه می‌دهد.

```python
from concurrent.futures import ThreadPoolExecutor
import time

def job(n):
    time.sleep(0.05)
    return n * n

with ThreadPoolExecutor(max_workers=3) as pool:
    results = list(pool.map(job, [1, 2, 3, 4]))
print(results)
```

### توضیح
- `ThreadPoolExecutor` → استخر thread می‌سازد
- `map` → تابع را روی ورودی‌ها اعمال می‌کند
- `max_workers` → حداکثر worker هم‌زمان

---

## فایل‌های این جلسه
- [examples/01_futures.py](./examples/01_futures.py)
- [exercises/exercise_01.py](./exercises/exercise_01.py)

## تمرین‌ها
- تمرین ساده: map را روی چند عدد اجرا کنید
- تمرین متوسط: طول رشته‌ها را با pool حساب کنید
