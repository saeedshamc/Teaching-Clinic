# جلسه ۱۷: AsyncIO

## اهداف یادگیری (Learning Objectives)

پس از این جلسه می‌توانید:
- با برنامه‌نویسی ناهم‌زمان (Asynchronous) آشنا شوید
- از `async` و `await` استفاده کنید
- تفاوت برنامه‌های هم‌زمان و ناهم‌زمان را درک کنید
- چند کار را هم‌زمان با `asyncio.gather` اجرا کنید

---

## AsyncIO چیست؟

در برنامه‌نویسی معمولی (هم‌زمان)، وقتی یک کار منتظر چیزی می‌ماند — مثلاً خوابیدن یا دریافت داده از شبکه — کل برنامه متوقف می‌شود.

AsyncIO به شما اجازه می‌دهد چند کار را بدون مسدود کردن یکدیگر اجرا کنید. در زمان انتظار یک کار، کار دیگری پیش می‌رود.

```python
import asyncio

async def greet():
    print("سلام")
    await asyncio.sleep(0.1)
    print("دنیا")

asyncio.run(greet())
```

### توضیح
- `async def` → تابع ناهم‌زمان (coroutine) تعریف می‌کند
- `await` → منتظر اتمام یک کار ناهم‌زمان می‌ماند
- `asyncio.sleep` → نسخه ناهم‌زمان `time.sleep` است
- `asyncio.run` → نقطه شروع اجرای برنامه async است

---

## اجرای هم‌زمان چند کار

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

با `asyncio.gather` چند coroutine هم‌زمان اجرا می‌شوند.

---

## فایل‌های این جلسه
- [examples/01_asyncio.py](./examples/01_asyncio.py)
- [exercises/exercise_01.py](./exercises/exercise_01.py)

## تمرین‌ها
- تمرین ساده: یک تابع async بنویسید که پیام چاپ کند
- تمرین متوسط: دو کار async را با `gather` هم‌زمان اجرا کنید
