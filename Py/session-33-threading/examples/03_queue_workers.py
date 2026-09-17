# ============================================================
# جلسه ۳۳ — Threading: Queue producer/consumer
# ============================================================

import threading
import time
from queue import Queue


def producer(q: Queue, name: str, count: int) -> None:
    for i in range(count):
        item = f"{name}-item-{i}"
        q.put(item)
        time.sleep(0.05)
    print(f"{name} تمام شد")


def consumer(q: Queue, name: str) -> None:
    while True:
        item = q.get()
        if item is None:
            q.task_done()
            break
        print(f"  [{name}] پردازش: {item}")
        time.sleep(0.08)
        q.task_done()


def main():
    q: Queue = Queue()
    threads = [
        threading.Thread(target=producer, args=(q, "P1", 4)),
        threading.Thread(target=consumer, args=(q, "C1")),
        threading.Thread(target=consumer, args=(q, "C2")),
    ]
    for t in threads:
        t.start()

    threads[0].join()
    q.join()
    q.put(None)
    q.put(None)
    threads[1].join()
    threads[2].join()
    print("Done")


if __name__ == "__main__":
    main()
