# ============================================================
# جلسه ۲۱ — Context Manager
# فایل: 01_context_manager.py
# ============================================================

from contextlib import contextmanager
from pathlib import Path

path = Path(__file__).with_name("demo.txt")

with path.open("w", encoding="utf-8") as file:
    file.write("سلام از context manager")

with path.open("r", encoding="utf-8") as file:
    print(file.read())


@contextmanager
def greet():
    print("شروع")
    yield
    print("پایان")


with greet():
    print("بدنه اصلی")

path.unlink(missing_ok=True)
