# ============================================================
# جلسه ۳۴ — submit و as_completed
# ============================================================

import time
from concurrent.futures import ThreadPoolExecutor, as_completed


def fetch_page(url: str, delay: float) -> str:
    time.sleep(delay)
    return f"<html>{url}</html>"


urls = [
    ("https://a.com", 0.3),
    ("https://b.com", 0.1),
    ("https://c.com", 0.2),
    ("https://d.com", 0.15),
]

with ThreadPoolExecutor(max_workers=3) as pool:
    future_map = {
        pool.submit(fetch_page, url, delay): url for url, delay in urls
    }
    print("نتایج به ترتیب اتمام:")
    for fut in as_completed(future_map):
        url = future_map[fut]
        print(f"  {url} → {len(fut.result())} chars")
