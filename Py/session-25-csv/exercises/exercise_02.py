# ============================================================
# تمرین ۴–۵ جلسه ۲۵ — CSV (خانه)
# ============================================================

import csv
from pathlib import Path

PEOPLE = [
    {"name": "سعید", "age": "30", "city": "تهران"},
    {"name": "مینا", "age": "25", "city": "اصفهان"},
    {"name": "علی", "age": "20", "city": "تهران"},
    {"name": "رضا", "age": "35", "city": "شیراز"},
]

path = Path(__file__).with_name("people.csv")
tehran_path = Path(__file__).with_name("tehran.csv")

with path.open("w", encoding="utf-8", newline="") as f:
    writer = csv.DictWriter(f, fieldnames=["name", "age", "city"])
    writer.writeheader()
    writer.writerows(PEOPLE)

# --- ۴. میانگین سن ---
with path.open(encoding="utf-8", newline="") as f:
    rows = list(csv.DictReader(f))
ages = [int(r["age"]) for r in rows]
print(f"میانگین سن: {sum(ages) / len(ages):.1f}")

# --- ۵. فیلتر تهران ---
tehran_rows = [r for r in rows if r["city"] == "تهران"]
with tehran_path.open("w", encoding="utf-8", newline="") as f:
    writer = csv.DictWriter(f, fieldnames=["name", "age", "city"])
    writer.writeheader()
    writer.writerows(tehran_rows)
print(f"تهران: {[r['name'] for r in tehran_rows]}")

path.unlink(missing_ok=True)
tehran_path.unlink(missing_ok=True)
