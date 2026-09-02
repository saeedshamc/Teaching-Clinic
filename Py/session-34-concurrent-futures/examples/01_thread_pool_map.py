# ============================================================
# جلسه ۳۴ — ThreadPoolExecutor.map
# ============================================================

import time
from concurrent.futures import ThreadPoolExecutor


def slow_square(n: int) -> int:
    time.sleep(0.1)
    return n * n


numbers = list(range(1, 9))

start = time.perf_counter()
seq = [slow_square(n) for n in numbers]
seq_time = time.perf_counter() - start

start = time.perf_counter()
with ThreadPoolExecutor(max_workers=4) as pool:
    par = list(pool.map(slow_square, numbers))
par_time = time.perf_counter() - start

print("sequential:", seq, f"({seq_time:.2f}s)")
print("parallel:  ", par, f"({par_time:.2f}s)")
