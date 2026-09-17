# ============================================================
# جلسه ۲۶ — pathlib: خواندن و نوشتن
# ============================================================

from pathlib import Path

base = Path(__file__).parent
notes = base / "demo_notes.txt"

# نوشتن
notes.write_text("یادداشت اول\nیادداشت دوم", encoding="utf-8")
print("نوشته شد:", notes.exists())

# خواندن
content = notes.read_text(encoding="utf-8")
print("محتوا:\n", content)

# append
with notes.open("a", encoding="utf-8") as f:
    f.write("خط سوم — append\n")

lines = notes.read_text(encoding="utf-8").splitlines()
print(f"تعداد خطوط: {len(lines)}")

notes.unlink(missing_ok=True)
print("فایل پاک شد.")
