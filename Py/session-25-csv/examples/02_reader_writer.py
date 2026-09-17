# ============================================================
# جلسه ۲۵ — reader و writer ساده
# فایل: 02_reader_writer.py
# ============================================================

import csv
from pathlib import Path

path = Path(__file__).with_name("products.csv")

# --- writer: لیست لیست ---
rows_to_write = [
    ["name", "price", "stock"],
    ["لپ‌تاپ", "25000000", "3"],
    ["ماوس", "450000", "15"],
    ["کیبورد", "1200000", "8"],
]

with path.open("w", encoding="utf-8", newline="") as f:
    writer = csv.writer(f)
    writer.writerows(rows_to_write)

# --- reader: لیست ---
print("--- csv.reader ---")
with path.open("r", encoding="utf-8", newline="") as f:
    reader = csv.reader(f)
    header = next(reader)
    print("ستون‌ها:", header)
    for row in reader:
        name, price, stock = row
        print(f"  {name}: {int(price):,} تومان × {stock}")

# --- delimiter ; ---
semicolon_path = Path(__file__).with_name("products_semi.csv")
with semicolon_path.open("w", encoding="utf-8", newline="") as f:
    writer = csv.writer(f, delimiter=";")
    writer.writerows(rows_to_write)

print("\n--- delimiter ; ---")
with semicolon_path.open(encoding="utf-8", newline="") as f:
    for row in csv.reader(f, delimiter=";"):
        print(" ", row)

path.unlink(missing_ok=True)
semicolon_path.unlink(missing_ok=True)
