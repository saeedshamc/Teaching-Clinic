# ============================================================
# جلسه ۱۶ — تجزیه نسخه از requirements
# فایل: 03_version_parsing.py
# ============================================================

import re


def parse_package_line(line: str) -> dict | None:
    """خط requirements را به نام و محدودیت نسخه تجزیه می‌کند."""
    line = line.strip()
    if not line or line.startswith("#"):
        return None

    # الگوهای رایج: package==1.0, package>=2.0, package
    match = re.match(r"^([a-zA-Z0-9_-]+)(.*)$", line)
    if not match:
        return None

    name = match.group(1)
    constraint = match.group(2).strip() or "any"

    return {"name": name, "constraint": constraint}


SAMPLE = """
requests>=2.28.0
pandas==2.0.0
pytest
# comment
flask~=2.3.0
"""

lines = SAMPLE.strip().splitlines()
packages = [p for line in lines if (p := parse_package_line(line))]

print("📦 بسته‌های پارس‌شده:")
for pkg in packages:
    print(f"  {pkg['name']:15} → {pkg['constraint']}")

# مقایسه == و >=
print("\n📌 == نسخه ثابت (reproducible)")
print("📌 >= حداقل نسخه (انعطاف)")
print("📌 بدون محدودیت = آخرین نسخه سازگار")
