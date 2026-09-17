# ============================================================
# جلسه ۲۵ — export برای Excel (utf-8-sig)
# فایل: 04_excel_export.py
# ============================================================

import csv
from pathlib import Path

SALES = [
    {"product": "لپ‌تاپ", "quantity": "2", "revenue": "50000000"},
    {"product": "ماوس", "quantity": "10", "revenue": "4500000"},
    {"product": "کیبورد", "quantity": "5", "revenue": "6000000"},
]

# --- utf-8-sig: BOM برای Excel Windows ---
excel_path = Path(__file__).with_name("sales_excel.csv")

with excel_path.open("w", encoding="utf-8-sig", newline="") as f:
    writer = csv.DictWriter(f, fieldnames=["product", "quantity", "revenue"])
    writer.writeheader()
    writer.writerows(SALES)

print(f"📊 {excel_path.name} — با BOM برای Excel")
print("   encoding=utf-8-sig + newline=''")

# --- خواندن و جمع revenue ---
with excel_path.open(encoding="utf-8-sig", newline="") as f:
    rows = list(csv.DictReader(f))

total_revenue = sum(int(r["revenue"]) for r in rows)
total_qty = sum(int(r["quantity"]) for r in rows)

print(f"\nمجموع فروش: {total_revenue:,} تومان")
print(f"تعداد کل: {total_qty}")

for r in rows:
    print(f"  {r['product']}: {r['quantity']} عدد — {int(r['revenue']):,}")

excel_path.unlink(missing_ok=True)

print("\n💡 فایل را در Excel باز کنید — فارسی درست نمایش داده می‌شود")
