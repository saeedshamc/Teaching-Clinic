# ============================================================
# جلسه ۴۰ — ساختار pyproject.toml
# فایل: 02_pyproject_structure.py
# ============================================================

PYPROJECT_SAMPLE = '''
[project]
name = "teaching-clinic-tools"
version = "0.1.0"
description = "ابزارهای آموزشی"
readme = "README.md"
requires-python = ">=3.10"
dependencies = ["requests>=2.28"]

[project.optional-dependencies]
dev = ["pytest>=7", "ruff>=0.1"]

[project.scripts]
clinic = "teaching_clinic_tools.cli:main"

[build-system]
requires = ["setuptools>=68"]
build-backend = "setuptools.build_meta"
'''

SECTIONS = {
    "[project]": "نام، نسخه، وابستگی‌ها",
    "[project.optional-dependencies]": "گروه dev، test",
    "[project.scripts]": "دستور CLI",
    "[build-system]": "ابزار ساخت wheel/sdist",
}

print("=== بخش‌های pyproject.toml ===")
for section, desc in SECTIONS.items():
    print(f"{section:35} → {desc}")

print("\n=== نمونه کامل (برای مطالعه) ===")
print(PYPROJECT_SAMPLE)
