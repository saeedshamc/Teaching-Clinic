# ============================================================
# جلسه ۱۳ — کاربرد واقعی: فیلتر و تبدیل داده محصولات
# فایل: 04_real_world_filter.py
# ============================================================

products = [
    {"name": "لپ‌تاپ", "category": "الکترونیک", "price": 25_000_000, "stock": 3},
    {"name": "ماوس", "category": "الکترونیک", "price": 450_000, "stock": 15},
    {"name": "کتاب Python", "category": "کتاب", "price": 350_000, "stock": 0},
    {"name": "کیبورد", "category": "الکترونیک", "price": 1_200_000, "stock": 8},
    {"name": "ماگ", "category": "آشپزخانه", "price": 120_000, "stock": 20},
]

# --- list comprehension: نام محصولات موجود ---
available_names = [p["name"] for p in products if p["stock"] > 0]
print("موجود:", available_names)

# --- dict comprehension: نام → قیمت (فقط الکترونیک) ---
electronics_prices = {
    p["name"]: p["price"]
    for p in products
    if p["category"] == "الکترونیک"
}
print("قیمت الکترونیک:", electronics_prices)

# --- set comprehension: دسته‌بندی‌های یکتا ---
categories = {p["category"] for p in products}
print("دسته‌ها:", categories)

# --- nested: لیست قیمت‌های تخفیف‌خورده (۱۰٪) برای موجودها ---
discounted = [
    {"name": p["name"], "final_price": int(p["price"] * 0.9)}
    for p in products
    if p["stock"] > 0 and p["price"] > 500_000
]
print("تخفیف‌دار:")
for item in discounted:
    print(f"  {item['name']}: {item['final_price']:,} تومان")
