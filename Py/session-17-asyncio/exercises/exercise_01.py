# ============================================================
# تمرین جلسه ۱۷
# هدف: دو کار async را با gather هم‌زمان اجرا کنید.
# ============================================================

import asyncio


async def say_hello(name, delay):
    await asyncio.sleep(delay)
    print("سلام", name)
    return name


async def main():
    results = await asyncio.gather(
        say_hello("سعید", 0.1),
        say_hello("مینا", 0.2),
    )
    print("نام‌ها:", results)


asyncio.run(main())
