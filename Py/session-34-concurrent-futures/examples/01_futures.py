# ============================================================
# جلسه ۳۴ — concurrent.futures
# فایل: 01_futures.py
# ============================================================

from concurrent.futures import ThreadPoolExecutor
import time

def job(n):
    time.sleep(0.05)
    return n * n

with ThreadPoolExecutor(max_workers=3) as pool:
    results = list(pool.map(job, [1, 2, 3, 4]))
print("نتایج:", results)
