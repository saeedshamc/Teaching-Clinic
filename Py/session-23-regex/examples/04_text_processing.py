# ============================================================
# جلسه ۲۳ — پردازش متن با regex
# فایل: 04_text_processing.py
# ============================================================

import re

log_text = """
2024-01-15 10:23:45 INFO User login: ali
2024-01-15 10:24:01 ERROR Database connection failed
2024-01-15 10:25:30 INFO User login: maryam
2024-01-15 10:26:00 WARNING Disk space low: 85%
"""

# --- استخراج خطوط ERROR ---
errors = re.findall(r"ERROR (.+)$", log_text, re.MULTILINE)
print("خطاها:", errors)

# --- استخراج timestamp و level ---
log_pattern = re.compile(
    r"(?P<date>\d{4}-\d{2}-\d{2}) (?P<time>\d{2}:\d{2}:\d{2}) "
    r"(?P<level>\w+) (?P<message>.+)$"
)

print("\nپارس لاگ:")
for match in log_pattern.finditer(log_text.strip()):
    d = match.groupdict()
    print(f"  [{d['level']}] {d['date']} {d['time']}: {d['message'][:40]}")

# --- پاکسازی whitespace ---
messy = "  سلام    دنیا   \n  پایتون  "
clean = re.sub(r"\s+", " ", messy).strip()
print(f"\nتمیز: '{clean}'")

# --- حذف HTML ساده ---
html = "<p>سلام</p><div>دنیا</div>"
plain = re.sub(r"<[^>]+>", "", html)
print("بدون HTML:", plain)
