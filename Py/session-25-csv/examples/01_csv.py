# ============================================================
# جلسه ۲۵ — CSV
# فایل: 01_csv.py
# ============================================================

import csv
from pathlib import Path

path = Path(__file__).with_name("people.csv")

with path.open("w", encoding="utf-8", newline="") as file:
    writer = csv.DictWriter(file, fieldnames=["name", "age"])
    writer.writeheader()
    writer.writerow({"name": "سعید", "age": 30})
    writer.writerow({"name": "مینا", "age": 25})

with path.open("r", encoding="utf-8", newline="") as file:
    reader = csv.DictReader(file)
    for row in reader:
        print(row["name"], row["age"])

path.unlink(missing_ok=True)
