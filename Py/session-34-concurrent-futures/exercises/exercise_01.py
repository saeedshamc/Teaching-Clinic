# ============================================================
# پاسخ نمونه — تمرین جلسه ۳۴ (ساده)
# ============================================================

from concurrent.futures import ThreadPoolExecutor, as_completed


def square(n: int) -> int:
    return n * n


def safe_div(a: float, b: float) -> float:
    return a / b


with ThreadPoolExecutor(max_workers=4) as pool:
    results = list(pool.map(square, range(1, 11)))
    print("map:", results)

    futures = [pool.submit(square, i) for i in range(1, 4)]
    print("submit sum:", sum(f.result() for f in futures))

    futs = {pool.submit(square, i): i for i in range(5, 10)}
    print("as_completed:", end=" ")
    for f in as_completed(futs):
        print(f.result(), end=" ")
    print()

    try:
        pool.submit(safe_div, 1, 0).result()
    except ZeroDivisionError:
        print("exception handled")
