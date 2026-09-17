# ============================================================
# جلسه ۲۶ — pathlib: glob و پیمایش
# ============================================================

from pathlib import Path

root = Path(__file__).parent

print("فایل‌های .py در examples:")
for py_file in sorted(root.glob("*.py")):
    print(" ", py_file.name)

print("\nهمه فایل‌ها در جلسه (rglob):")
session_root = root.parent
for path in sorted(session_root.rglob("*")):
    if path.is_file():
        rel = path.relative_to(session_root)
        print(f"  {rel} ({path.stat().st_size} B)")
