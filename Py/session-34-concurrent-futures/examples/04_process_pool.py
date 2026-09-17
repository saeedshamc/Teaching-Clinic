# ============================================================
# جلسه ۳۴ — ProcessPoolExecutor (CPU-bound)
# ============================================================

import time
from concurrent.futures import ProcessPoolExecutor


def sum_squares(n: int) -> int:
    return sum(i * i for i in range(n))


def main():
    N = 2_000_000
    tasks = [N] * 4

    start = time.perf_counter()
    seq = sum(sum_squares(n) for n in tasks)
    seq_t = time.perf_counter() - start

    start = time.perf_counter()
    with ProcessPoolExecutor(max_workers=4) as pool:
        par = sum(pool.map(sum_squares, tasks))
    par_t = time.perf_counter() - start

    print(f"sequential: {seq} ({seq_t:.2f}s)")
    print(f"parallel:   {par} ({par_t:.2f}s)")


if __name__ == "__main__":
    main()
