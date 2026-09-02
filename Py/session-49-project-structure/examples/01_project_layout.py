# ============================================================
# جلسه ۴۹ — layout پروژه
# فایل: 01_project_layout.py
# ============================================================

LAYOUT = {
    "pyproject.toml": "متادیتا، وابستگی، build",
    "README.md": "راهنمای شروع",
    ".gitignore": "venv، cache، secrets",
    "src/myapp/__init__.py": "بسته اصلی",
    "src/myapp/__main__.py": "python -m myapp",
    "src/myapp/config.py": "تنظیمات",
    "src/myapp/models/": "dataclassها",
    "src/myapp/services/": "منطق کسب‌وکار",
    "src/myapp/cli/": "رابط خط فرمان",
    "tests/conftest.py": "fixture مشترک",
    "tests/test_services/": "تست سرویس‌ها",
}

print("=== ساختار پروژه پیشنهادی ===")
for path, role in LAYOUT.items():
    print(f"{path:32} → {role}")
