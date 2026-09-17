# ============================================================
# جلسه ۴۴ — perf_counter
# فایل: 04_perf_counter.py
# ============================================================

import time


def fibonacci(n: int) -> int:
    if n <= 1:
        return n
    return fibonacci(n - 1) + fibonacci(n - 2)


def fibonacci_memo(n: int, cache: dict | None = None) -> int:
    if cache is None:
        cache = {}
    if n in cache:
        return cache[n]
    if n <= 1:
        return n
    cache[n] = fibonacci_memo(n - 1, cache) + fibonacci_memo(n - 2, cache)
    return cache[n]


def bench(label: str, fn, n: int) -> None:
    start = time.perf_counter()
    result = fn(n)
    elapsed = time.perf_counter() - start
    print(f"{label}(n={n}) = {result} → {elapsed:.4f}s")


bench("fibonacci", fibonacci, 30)
bench("fibonacci_memo", lambda n: fibonacci_memo(n), 30)
