# ============================================================
# جلسه ۴۹ — Project Structure
# فایل: 01_project_layout.py
# ============================================================

layout = {
    "src/myapp/__init__.py": "بسته اصلی",
    "src/myapp/main.py": "نقطه ورود",
    "src/myapp/services/": "منطق کسب‌وکار",
    "tests/": "تست‌ها",
    "README.md": "مستند شروع",
    "pyproject.toml": "متادیتا و وابستگی",
}

for path, role in layout.items():
    print(f"{path:28} -> {role}")
