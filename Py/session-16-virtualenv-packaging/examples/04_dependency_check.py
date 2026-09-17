# ============================================================
# جلسه ۱۶ — بررسی وابستگی پروژه
# فایل: 04_dependency_check.py
# ============================================================

# شبیه‌سازی: بسته‌های نصب‌شده در venv
INSTALLED = {
    "requests": "2.31.0",
    "pandas": "2.0.3",
    "pytest": "7.4.0",
}

# requirements پروژه
REQUIRED = [
    "requests>=2.28.0",
    "pandas>=2.0.0",
    "numpy>=1.24.0",  # نصب نشده!
]


def parse_min_version(spec: str) -> tuple[str, str | None]:
    """package>=1.0 → (package, 1.0)"""
    if ">=" in spec:
        name, version = spec.split(">=", 1)
        return name.strip(), version.strip()
    return spec.strip(), None


def version_tuple(v: str) -> tuple[int, ...]:
    return tuple(int(x) for x in v.split("."))


def check_dependencies(required: list[str], installed: dict[str, str]) -> list[str]:
    """لیست مشکلات وابستگی را برمی‌گرداند."""
    issues = []
    for spec in required:
        name, min_ver = parse_min_version(spec)
        if name not in installed:
            issues.append(f"❌ {name} نصب نشده")
        elif min_ver and version_tuple(installed[name]) < version_tuple(min_ver):
            issues.append(f"⚠ {name}: نسخه {installed[name]} < {min_ver}")
        else:
            issues.append(f"✅ {name}={installed[name]}")
    return issues


print("🔍 بررسی وابستگی‌ها:\n")
for msg in check_dependencies(REQUIRED, INSTALLED):
    print(f"  {msg}")

print("\n💡 در عمل: pip check — بررسی conflict")
print("💡 pip install -r requirements.txt — نصب همه")
