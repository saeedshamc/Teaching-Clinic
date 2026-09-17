# ============================================================
# تمرین ۱–۳ جلسه ۲۱ — Context Manager (کلاسی)
# ============================================================

import time
from contextlib import contextmanager
from pathlib import Path


# --- ۱. with open ---
path = Path(__file__).with_name("demo_exercise.txt")
with path.open("w", encoding="utf-8") as f:
    f.write("سلام از with\n")

with path.open("r", encoding="utf-8") as f:
    print("محتوا:", f.read().strip())
path.unlink(missing_ok=True)


# --- ۲. @contextmanager greet ---
@contextmanager
def greet():
    print("شروع")
    yield
    print("پایان")


with greet():
    print("بدنه اصلی")


# --- ۳. timer ---
@contextmanager
def timer():
    start = time.perf_counter()
    yield
    print(f"زمان: {time.perf_counter() - start:.4f}s")


with timer():
    _ = sum(range(100_000))
