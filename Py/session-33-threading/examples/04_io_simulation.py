# ============================================================
# جلسه ۳۳ — Threading: شبیه‌سازی I/O موازی
# ============================================================

import threading
import time
from concurrent.futures import ThreadPoolExecutor, as_completed


def fetch(url: str, delay: float) -> str:
    """شبیه‌سازی درخواست شبکه."""
    time.sleep(delay)
    return f"پاسخ از {url}"


def sequential(urls: list[tuple[str, float]]) -> list[str]:
    start = time.perf_counter()
    results = [fetch(url, d) for url, d in urls]
    print(f"sequential: {time.perf_counter() - start:.2f}s")
    return results


def parallel(urls: list[tuple[str, float]], workers: int = 3) -> list[str]:
    start = time.perf_counter()
    with ThreadPoolExecutor(max_workers=workers) as pool:
        futures = {pool.submit(fetch, url, d): url for url, d in urls}
        results = []
        for fut in as_completed(futures):
            results.append(fut.result())
    print(f"parallel:   {time.perf_counter() - start:.2f}s")
    return results


URLS = [
    ("api/users", 0.4),
    ("api/orders", 0.3),
    ("api/products", 0.5),
    ("api/stats", 0.2),
]

print("=== sequential ===")
sequential(URLS)
print("\n=== parallel ===")
parallel(URLS)
