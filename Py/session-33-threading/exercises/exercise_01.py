# ============================================================
# تمرین جلسه ۳۳
# هدف: سه thread برای چاپ پیام بسازید.
# ============================================================

import threading

def say(msg):
    print(msg)

threads = [threading.Thread(target=say, args=(f"پیام {i}",)) for i in range(1, 4)]
for t in threads:
    t.start()
for t in threads:
    t.join()
