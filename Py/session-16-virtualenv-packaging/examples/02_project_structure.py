# ============================================================
# جلسه ۱۶ — چک‌لیست ساختار پروژه
# فایل: 02_project_structure.py
# ============================================================

from pathlib import Path

# __file__ = مسیر فایل جاری — پایه ساختار پروژه
CURRENT_FILE = Path(__file__)
SESSION_DIR = CURRENT_FILE.parent.parent
EXAMPLES_DIR = CURRENT_FILE.parent

print("📁 ساختار جلسه ۱۶:")
print(f"  فایل جاری:     {CURRENT_FILE.name}")
print(f"  پوشه examples: {EXAMPLES_DIR.name}")
print(f"  پوشه جلسه:     {SESSION_DIR.name}")

# چک‌لیست پوشه‌های استاندارد پروژه
EXPECTED_FOLDERS = ["src", "tests", "docs"]
PROJECT_ROOT = SESSION_DIR  # در پروژه واقعی root جدا است

print("\n📋 چک‌لیست پروژه Python:")
checklist = [
    ("requirements.txt", "لیست وابستگی‌ها"),
    (".gitignore", "فایل‌های نادیده git"),
    ("README.md", "مستندات پروژه"),
    (".venv/", "محیط مجازی (در git نیست)"),
    ("src/", "کد اصلی"),
    ("tests/", "تست‌ها"),
]

for item, desc in checklist:
    print(f"  [{'✓' if item.endswith('/') else '○'}] {item:20} — {desc}")

print("\n💡 import محلی — وقتی src/ دارید:")
print("  PYTHONPATH=src python -m myapp")
print("  یا pip install -e .  (editable install)")
