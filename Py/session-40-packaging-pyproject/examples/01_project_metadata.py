# ============================================================
# جلسه ۴۰ — متادیتای پروژه
# فایل: 01_project_metadata.py
# ============================================================
# این dict معادل بخش [project] در pyproject.toml است
# ============================================================

PROJECT = {
    "name": "teaching-clinic-tools",
    "version": "0.1.0",
    "description": "ابزارهای آموزشی کلینیک",
    "requires_python": ">=3.10",
    "dependencies": ["requests>=2.28", "httpx>=0.27"],
    "authors": [{"name": "Teaching Clinic"}],
}

print("=== متادیتای پروژه ===")
print(f"نام:    {PROJECT['name']}")
print(f"نسخه:   {PROJECT['version']}")
print(f"Python: {PROJECT['requires_python']}")
print("وابستگی‌ها:")
for dep in PROJECT["dependencies"]:
    print(f"  - {dep}")
