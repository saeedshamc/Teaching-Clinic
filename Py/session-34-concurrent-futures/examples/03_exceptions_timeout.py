# ============================================================
# جلسه ۳۴ — exception و timeout
# ============================================================

import time
from concurrent.futures import ThreadPoolExecutor, TimeoutError


def divide(a: float, b: float) -> float:
    time.sleep(0.1)
    return a / b


def slow_task() -> str:
    time.sleep(5)
    return "done"


with ThreadPoolExecutor(max_workers=2) as pool:
    # exception
    fut_err = pool.submit(divide, 10, 0)
    try:
        fut_err.result()
    except ZeroDivisionError as e:
        print("خطا:", type(e).__name__)

    # timeout
    fut_slow = pool.submit(slow_task)
    try:
        print(fut_slow.result(timeout=0.5))
    except TimeoutError:
        print("timeout — task هنوز در حال اجراست")
        fut_slow.cancel()

print("تمام")
