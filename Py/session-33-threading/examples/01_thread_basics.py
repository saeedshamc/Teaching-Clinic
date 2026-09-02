# ============================================================
# جلسه ۳۳ — Threading: پایه
# ============================================================

import threading
import time


def worker(name: str, delay: float) -> None:
    print(f"[{threading.current_thread().name}] شروع {name}")
    time.sleep(delay)
    print(f"[{threading.current_thread().name}] پایان {name}")


def main():
    threads = []
    for i, delay in enumerate([0.3, 0.2, 0.1], start=1):
        t = threading.Thread(target=worker, args=(f"کار-{i}", delay), name=f"Worker-{i}")
        threads.append(t)
        t.start()

    for t in threads:
        t.join()

    print("همه threadها تمام شدند.")


if __name__ == "__main__":
    main()
