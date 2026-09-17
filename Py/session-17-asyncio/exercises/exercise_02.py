# ============================================================
# تمرین ۴–۵ جلسه ۱۷ — AsyncIO (خانه)
# ============================================================

import asyncio
import time


async def download(name, delay):
    print(f"  دانلود {name}...")
    await asyncio.sleep(delay)
    return f"{name}-OK"


async def slow():
    await asyncio.sleep(10)


async def main():
    start = time.perf_counter()
    results = await asyncio.gather(
        download("فایل-1", 1),
        download("فایل-2", 2),
        download("فایل-3", 3),
    )
    print(f"زمان: {time.perf_counter() - start:.1f}s")
    print("نتایج:", results)

    try:
        await asyncio.wait_for(slow(), timeout=2.0)
    except asyncio.TimeoutError:
        print("timeout دریافت شد ✓")


asyncio.run(main())
