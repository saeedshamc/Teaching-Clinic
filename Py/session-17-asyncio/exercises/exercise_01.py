# ============================================================
# تمرین ۱–۳ جلسه ۱۷ — AsyncIO (کلاسی)
# ============================================================

import asyncio


async def greet():
    print("سلام")
    await asyncio.sleep(0.2)
    print("دنیا")


async def task(name, delay):
    print(f"شروع {name}")
    await asyncio.sleep(delay)
    print(f"پایان {name}")
    return name


async def main():
    await greet()
    print("---")
    results = await asyncio.gather(task("الف", 0.3), task("ب", 0.1))
    print("نتایج:", results)


asyncio.run(main())
