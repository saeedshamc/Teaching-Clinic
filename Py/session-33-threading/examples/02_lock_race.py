# ============================================================
# جلسه ۳۳ — Threading: race condition و Lock
# ============================================================

import threading

UNSAFE_COUNT = 0
SAFE_COUNT = 0
lock = threading.Lock()


def unsafe_increment(n: int) -> None:
    global UNSAFE_COUNT
    for _ in range(n):
        temp = UNSAFE_COUNT
        temp += 1
        UNSAFE_COUNT = temp


def safe_increment(n: int) -> None:
    global SAFE_COUNT
    for _ in range(n):
        with lock:
            SAFE_COUNT += 1


N = 100_000
threads = []

for _ in range(4):
    threads.append(threading.Thread(target=unsafe_increment, args=(N,)))
for t in threads:
    t.start()
for t in threads:
    t.join()

print(f"ناامن (انتظار 400000): {UNSAFE_COUNT}")

threads = []
for _ in range(4):
    threads.append(threading.Thread(target=safe_increment, args=(N,)))
for t in threads:
    t.start()
for t in threads:
    t.join()

print(f"امن (انتظار 400000): {SAFE_COUNT}")
