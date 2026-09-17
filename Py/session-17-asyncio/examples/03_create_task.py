# ============================================================
# جلسه ۱۷ — create_task و cancel
# فایل: 03_create_task.py
# ============================================================

import asyncio


async def worker(name: str, delay: float):
    print(f"  [{name}] شروع")
    await asyncio.sleep(delay)
    print(f"  [{name}] پایان")
    return name


async def main():
    # create_task — فوراً schedule می‌شود
    task_a = asyncio.create_task(worker("A", 0.5))
    task_b = asyncio.create_task(worker("B", 0.3))

    # منتظر هر دو
    results = await asyncio.gather(task_a, task_b)
    print("نتایج:", results)

    # --- cancel ---
    long_task = asyncio.create_task(worker("طولانی", 5.0))
    await asyncio.sleep(0.1)
    long_task.cancel()
    try:
        await long_task
    except asyncio.CancelledError:
        print("✋ task طولانی cancel شد")


asyncio.run(main())
