# ============================================================
# جلسه ۴۰ — Packaging / pyproject.toml
# فایل: 01_packaging_info.py
# ============================================================

project = {
    "name": "teaching-clinic",
    "version": "0.1.0",
    "requires_python": ">=3.10",
    "dependencies": ["requests"],
}

print("نام بسته:", project["name"])
print("نسخه:", project["version"])
print("وابستگی‌ها:", ", ".join(project["dependencies"]))
print("نمونه pyproject.toml را در README ببینید.")
