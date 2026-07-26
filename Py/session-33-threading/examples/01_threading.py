# ============================================================
# جلسه ۳۳ — Threading
# فایل: 01_threading.py
# ============================================================

import threading
import time

def worker(name):
    print("شروع", name)
    time.sleep(0.1)
    print("پایان", name)

threads = [
    threading.Thread(target=worker, args=("الف",)),
    threading.Thread(target=worker, args=("ب",)),
]
for t in threads:
    t.start()
for t in threads:
    t.join()
print("همه کارها تمام شد")
