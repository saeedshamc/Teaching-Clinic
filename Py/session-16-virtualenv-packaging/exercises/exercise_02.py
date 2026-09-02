# ============================================================
# تمرین ۴–۵ جلسه ۱۶ — venv و requirements (خانه)
# ============================================================


def parse_package_line(line: str) -> dict | None:
    line = line.strip()
    if not line or line.startswith("#"):
        return None
    if ">=" in line:
        name, min_ver = line.split(">=", 1)
        return {"name": name.strip(), "min": min_ver.strip()}
    if "==" in line:
        name, exact = line.split("==", 1)
        return {"name": name.strip(), "exact": exact.strip()}
    return {"name": line.strip(), "min": None}


specs = ["requests>=2.28", "pytest==7.4.0", "flask"]
for spec in specs:
    result = parse_package_line(spec)
    print(f"{spec:25} → {result}")

print("\n💡 تمرین ۵: در ترمینال اجرا کنید:")
print("  python -m venv .venv")
print("  .venv\\Scripts\\Activate.ps1")
print("  pip install requests")
print("  pip freeze > requirements.txt")
