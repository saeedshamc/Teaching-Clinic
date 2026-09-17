# ============================================================
# جلسه ۱۷ — AsyncIO: async/await و gather
# فایل: 01_asyncio.py
# ============================================================
# async def → coroutine | await → منتظر coroutine دیگر
# asyncio.run() → نقطه ورود event loop
# asyncio.gather → اجرای موازی چند coroutine
# ============================================================

import asyncio


async def greet():
    """coroutine ساده — چاپ و sleep ناهم‌زمان"""
    print("سلام")
    await asyncio.sleep(0.1)
    print("دنیا")


async def task(name, delay):
    """task با نام و تأخیر — مقدار بازگشتی دارد"""
    print(f"شروع {name}")
    await asyncio.sleep(delay)
    print(f"پایان {name}")
    return name


async def main():
    await greet()
    print("---")
    # gather — هر دو task هم‌زمان شروع می‌شوند
    results = await asyncio.gather(
        task("الف", 0.2),
        task("ب", 0.1),
    )
    print("نتایج:", results)


asyncio.run(main())
