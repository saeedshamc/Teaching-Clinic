# ============================================================
# پاسخ نمونه — تمرین جلسه ۲۶ (ساده)
# ============================================================

from pathlib import Path

# 1. تحلیل مسیر
report = Path("data/reports/2024/summary.pdf")
print("name:", report.name)
print("stem:", report.stem)
print("suffix:", report.suffix)
print("parent:", report.parent)

# 2. پوشه و فایل
base = Path(__file__).parent
data_dir = base / "data"
data_dir.mkdir(exist_ok=True)
hello = data_dir / "hello.txt"
hello.write_text("سلام از pathlib!", encoding="utf-8")

# 3. glob
examples = base.parent / "examples"
print("\nفایل‌های py:")
for f in sorted(examples.glob("*.py")):
    print(" ", f.name)

# 4. exists
target = hello
if target.exists():
    print("\nمحتوا:", target.read_text(encoding="utf-8"))
else:
    print("فایل وجود ندارد.")

hello.unlink(missing_ok=True)
data_dir.rmdir()
