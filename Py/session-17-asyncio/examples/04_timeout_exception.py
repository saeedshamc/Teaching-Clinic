# ============================================================
# جلسه ۱۷ — timeout و exception در async
# فایل: 04_timeout_exception.py
# ============================================================

import asyncio


async def slow_operation():
    await asyncio.sleep(5)
    return "تمام شد"


async def risky_operation(should_fail: bool):
    await asyncio.sleep(0.1)
    if should_fail:
        raise ValueError("خطای عمدی!")
    return "موفق"


async def main():
    # --- timeout ---
    try:
        result = await asyncio.wait_for(slow_operation(), timeout=1.0)
    except asyncio.TimeoutError:
        print("⏰ timeout — عملیات بیش از ۱ ثانیه طول کشید")

    # --- exception در gather ---
    results = await asyncio.gather(
        risky_operation(False),
        risky_operation(True),
        return_exceptions=True,  # خطا را exception object برمی‌گرداند
    )
    for i, r in enumerate(results):
        if isinstance(r, Exception):
            print(f"  task {i}: خطا — {r}")
        else:
            print(f"  task {i}: {r}")


asyncio.run(main())
