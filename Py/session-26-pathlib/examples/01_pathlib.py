# ============================================================
# جلسه ۲۶ — pathlib
# فایل: 01_pathlib.py
# ============================================================

from pathlib import Path

base = Path(__file__).parent
file_path = base / "notes.txt"
file_path.write_text("یادداشت نمونه", encoding="utf-8")

print("نام فایل:", file_path.name)
print("پسوند:", file_path.suffix)
print("وجود دارد؟", file_path.exists())
print("محتوا:", file_path.read_text(encoding="utf-8"))

file_path.unlink(missing_ok=True)
