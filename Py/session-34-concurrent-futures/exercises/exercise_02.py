# ============================================================
# پاسخ نمونه — تمرین جلسه ۳۴ (پیشرفته)
# ============================================================

import time
from concurrent.futures import ThreadPoolExecutor, as_completed


def fetch(url: str) -> dict:
    delay = 0.1 + (hash(url) % 3) * 0.05
    time.sleep(delay)
    return {"url": url, "status": 200, "size": len(url) * 10}


URLS = [f"https://api.example.com/item/{i}" for i in range(12)]


def sequential(urls: list[str]) -> list[dict]:
    t0 = time.perf_counter()
    out = [fetch(u) for u in urls]
    print(f"seq: {time.perf_counter() - t0:.2f}s")
    return out


def parallel(urls: list[str], workers: int = 4) -> list[dict]:
    t0 = time.perf_counter()
    with ThreadPoolExecutor(max_workers=workers) as pool:
        futs = {pool.submit(fetch, u): u for u in urls}
        out = []
        for f in as_completed(futs):
            out.append(f.result())
    print(f"par: {time.perf_counter() - t0:.2f}s")
    return out


sequential(URLS[:6])
parallel(URLS[:6])
