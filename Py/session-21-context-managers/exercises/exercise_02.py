# ============================================================
# تمرین ۴–۵ جلسه ۲۱ — Context Manager (خانه)
# ============================================================

from contextlib import suppress
from pathlib import Path


class FileLogger:
    def __init__(self, name: str):
        self.name = name

    def __enter__(self):
        print(f"▶ {self.name}")
        return self

    def __exit__(self, exc_type, exc_val, exc_tb):
        status = "خطا" if exc_type else "OK"
        print(f"◀ {self.name}: {status}")
        return False


with FileLogger("عملیات"):
    print("  کار انجام شد")

print("\n--- suppress ---")
with suppress(FileNotFoundError):
    Path("missing.txt").unlink()
print("بدون crash ادامه داد ✓")
