# ============================================================
# جلسه ۲۶ — pathlib: ساخت و تحلیل Path
# ============================================================

from pathlib import Path

# مسیر نسبت به همین فایل
base = Path(__file__).parent
file_path = base / "sample" / "notes.txt"

print("مسیر کامل:", file_path)
print("نام:", file_path.name)
print("stem:", file_path.stem)
print("پسوند:", file_path.suffix)
print("والد:", file_path.parent)
print("بخش‌ها:", file_path.parts)

# مسیرهای سیستمی
print("\nhome:", Path.home())
print("cwd:", Path.cwd())

# resolve
relative = Path("..") / "session-26-pathlib"
print("resolve:", relative.resolve())
