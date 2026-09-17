# ============================================================
# پاسخ نمونه — تمرین جلسه ۳۳ (ساده)
# ============================================================

import threading
import time

event = threading.Event()


def worker(name: str) -> None:
    print(f"{name} منتظر event...")
    event.wait()
    print(f"{name} ادامه داد!")


for n in ("A", "B"):
    threading.Thread(target=worker, args=(n,)).start()

time.sleep(0.5)
print("main: event.set()")
event.set()

time.sleep(0.3)
