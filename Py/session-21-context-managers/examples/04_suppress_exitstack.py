# ============================================================
# جلسه ۲۱ — suppress و ExitStack
# فایل: 04_suppress_exitstack.py
# ============================================================

from contextlib import ExitStack, suppress
from pathlib import Path


# --- suppress: نادیده گرفتن exception مشخص ---
print("--- suppress ---")
with suppress(FileNotFoundError, PermissionError):
    Path("فایل_وجود_ندارد.txt").unlink()
print("ادامه برنامه بدون crash")

# --- ExitStack: چند CM به‌صورت پویا ---
print("\n--- ExitStack ---")
temp_dir = Path(__file__).parent
files = ["temp_a.txt", "temp_b.txt"]

with ExitStack() as stack:
    handles = []
    for name in files:
        path = temp_dir / name
        f = stack.enter_context(path.open("w", encoding="utf-8"))
        handles.append((path, f))

    for path, f in handles:
        f.write(f"محتوای {path.name}\n")

# همه فایل‌ها بسته شدند
for name in files:
    (temp_dir / name).unlink(missing_ok=True)

print("فایل‌های موقت ساخته و پاک شدند")
