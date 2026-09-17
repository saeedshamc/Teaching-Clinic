# ============================================================
# جلسه ۱۷ — مقایسه sync و async
# فایل: 02_sync_vs_async.py
# ============================================================

import asyncio
import time


async def fetch_data(item_id: int, delay: float) -> str:
    """شبیه‌سازی درخواست شبکه — منتظر پاسخ"""
    await asyncio.sleep(delay)
    return f"داده-{item_id}"


async def run_async():
    start = time.perf_counter()
    results = await asyncio.gather(
        fetch_data(1, 1.0),
        fetch_data(2, 1.0),
        fetch_data(3, 1.0),
    )
    elapsed = time.perf_counter() - start
    print(f"⚡ async: {elapsed:.2f}s — {results}")


def run_sync():
    start = time.perf_counter()
    results = []
    for i in range(1, 4):
        time.sleep(1.0)  # block — یکی‌یکی
        results.append(f"داده-{i}")
    elapsed = time.perf_counter() - start
    print(f"🐢 sync:  {elapsed:.2f}s — {results}")


print("--- sync (ترتیبی) ---")
run_sync()

print("\n--- async (موازی I/O) ---")
asyncio.run(run_async())

print("\n💡 async برای I/O مناسب است — CPU-intensive نه")
