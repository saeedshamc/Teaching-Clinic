# ============================================================
# جلسه ۴۰ — ساختار package و import
# فایل: 03_package_layout.py
# ============================================================

LAYOUT = {
    "pyproject.toml": "متادیتا و build",
    "README.md": "مستند شروع",
    "src/teaching_clinic_tools/__init__.py": "بسته اصلی",
    "src/teaching_clinic_tools/core.py": "منطق کسب‌وکار",
    "src/teaching_clinic_tools/cli.py": "نقطه ورود CLI",
    "tests/test_core.py": "تست واحد",
}

print("=== src layout پیشنهادی ===")
for path, role in LAYOUT.items():
    print(f"{path:42} → {role}")


def greet(name: str) -> str:
    """تابع نمونه — در پروژه واقعی داخل core.py."""
    return f"سلام {name}"


print("\n=== import در مصرف‌کننده ===")
print("from teaching_clinic_tools.core import greet")
print("نتیجه:", greet("کلینیک"))
