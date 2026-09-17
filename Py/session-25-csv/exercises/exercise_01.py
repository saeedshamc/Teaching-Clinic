# ============================================================
# تمرین ۱–۳ جلسه ۲۵ — CSV (کلاسی)
# ============================================================

import csv
from pathlib import Path

path = Path(__file__).with_name("people_ex.csv")

# --- ۱–۳. نوشتن با DictWriter ---
with path.open("w", encoding="utf-8", newline="") as f:
    writer = csv.DictWriter(f, fieldnames=["name", "age"])
    writer.writeheader()
    writer.writerow({"name": "سعید", "age": 30})
    writer.writerow({"name": "مینا", "age": 25})

# --- ۲. خواندن ---
print("--- خواندن ---")
with path.open("r", encoding="utf-8", newline="") as f:
    reader = csv.DictReader(f)
    for row in reader:
        print(f"  {row['name']}: {row['age']} سال")

path.unlink(missing_ok=True)
