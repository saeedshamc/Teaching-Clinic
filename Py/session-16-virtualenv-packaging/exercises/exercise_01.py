# ============================================================
# تمرین ۱–۳ جلسه ۱۶ — venv و requirements (کلاسی)
# ============================================================

# --- ۱. requirements تحلیل داده ---
DATA_SCIENCE_REQUIREMENTS = """
pandas>=2.0.0
numpy>=1.24.0
matplotlib>=3.7.0
"""

# --- ۲. پارس requirements ---


def parse_requirements(text: str) -> list[str]:
    packages = []
    for line in text.strip().splitlines():
        line = line.strip()
        if line and not line.startswith("#"):
            packages.append(line.split(">=")[0].split("==")[0].strip())
    return packages


# --- ۳. فیلتر کامنت ---
raw = """
requests>=2.28
# pandas — فعلاً لازم نیست
pytest==7.4.0

flask>=2.3
"""
clean = parse_requirements(raw)

print("بسته‌های تحلیل داده:", parse_requirements(DATA_SCIENCE_REQUIREMENTS))
print("بسته‌های فیلترشده:", clean)
