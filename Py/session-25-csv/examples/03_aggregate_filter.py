# ============================================================
# جلسه ۲۵ — aggregate و فیلتر CSV
# فایل: 03_aggregate_filter.py
# ============================================================

import csv
from pathlib import Path

PEOPLE = [
    {"name": "سعید", "age": "30", "city": "تهران"},
    {"name": "مینا", "age": "25", "city": "اصفهان"},
    {"name": "علی", "age": "17", "city": "تهران"},
    {"name": "رضا", "age": "22", "city": "شیراز"},
    {"name": "سارا", "age": "16", "city": "تهران"},
]

path = Path(__file__).with_name("people.csv")
filtered_path = Path(__file__).with_name("adults.csv")

# --- نوشتن ---
with path.open("w", encoding="utf-8", newline="") as f:
    writer = csv.DictWriter(f, fieldnames=["name", "age", "city"])
    writer.writeheader()
    writer.writerows(PEOPLE)

# --- خواندن و aggregate ---
with path.open(encoding="utf-8", newline="") as f:
    rows = list(csv.DictReader(f))

ages = [int(r["age"]) for r in rows]
print(f"میانگین سن: {sum(ages) / len(ages):.1f}")
print(f"تعداد: {len(rows)}")

# --- فیلتر: بزرگسالان (>= 18) ---
adults = [r for r in rows if int(r["age"]) >= 18]
print(f"\nبزرگسالان ({len(adults)}):")
for a in adults:
    print(f"  {a['name']} — {a['age']} سال")

# --- شهر تهران ---
tehran = [r["name"] for r in rows if r["city"] == "تهران"]
print(f"\nتهرانی‌ها: {tehran}")

# --- نوشتن فیلترشده ---
with filtered_path.open("w", encoding="utf-8", newline="") as f:
    writer = csv.DictWriter(f, fieldnames=["name", "age", "city"])
    writer.writeheader()
    writer.writerows(adults)
print(f"\n💾 adults.csv: {len(adults)} ردیف")

path.unlink(missing_ok=True)
filtered_path.unlink(missing_ok=True)
