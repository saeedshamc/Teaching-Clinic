# ============================================================
# جلسه ۱۶ — شبیه‌سازی requirements.txt
# فایل: 01_requirements_demo.py
# ============================================================
# requirements.txt هر خط = یک بسته (با یا بدون نسخه)
# pip freeze > requirements.txt — خروجی واقعی pip
# pip install -r requirements.txt — نصب از فایل
# ============================================================

# نمونه requirements به‌صورت رشته (شبیه فایل واقعی)
REQUIREMENTS_TEXT = """
requests>=2.28.0
pandas>=2.0.0
# این خط کامنت است — pip نادیده می‌گیرد
pytest==7.4.0
"""

# پارس ساده: خطوط غیرخالی و بدون #
def parse_requirements(text: str) -> list[str]:
    lines = []
    for line in text.strip().splitlines():
        line = line.strip()
        if line and not line.startswith("#"):
            lines.append(line)
    return lines


requirements = parse_requirements(REQUIREMENTS_TEXT)

print("📦 بسته‌های پروژه:")
for pkg in requirements:
    print(f"  - {pkg}")

print("\n💡 دستورات واقعی در ترمینال:")
print("  python -m venv .venv")
print("  .venv\\Scripts\\Activate.ps1   # Windows")
print("  pip install -r requirements.txt")
print("  pip freeze > requirements.txt")

# مقایسه == (دقیق) و >= (حداقل)
print("\n📌 == نسخه ثابت | >= حداقل نسخه")
