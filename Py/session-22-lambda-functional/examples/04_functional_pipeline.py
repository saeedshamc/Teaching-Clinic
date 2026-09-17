# ============================================================
# جلسه ۲۲ — pipeline داده functional
# فایل: 04_functional_pipeline.py
# ============================================================

from functools import reduce


def pipeline(data, *operations):
    """اعمال زنجیره‌ای عملیات روی داده"""
    return reduce(lambda acc, op: op(acc), operations, data)


numbers = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

# مرحله ۱: فیلتر زوج
step_filter = lambda lst: list(filter(lambda n: n % 2 == 0, lst))
# مرحله ۲: مربع
step_square = lambda lst: list(map(lambda n: n * n, lst))
# مرحله ۳: فقط بزرگ‌تر از ۱۰
step_big = lambda lst: list(filter(lambda n: n > 10, lst))

result = pipeline(numbers, step_filter, step_square, step_big)
print("pipeline:", result)  # [16, 36, 64, 100]

# --- نام‌ها: فیلتر بلند + uppercase ---
names = ["علی", "م", "مریم", "رضا", "سارا"]
long_upper = list(
    map(
        lambda n: n.upper() if n.isascii() else n,
        filter(lambda n: len(n) >= 3, names),
    )
)
print("نام‌های بلند:", long_upper)

# --- lambda در sorted محصولات ---
products = [
    {"name": "لپ‌تاپ", "price": 25_000_000},
    {"name": "ماوس", "price": 450_000},
    {"name": "کیبورد", "price": 1_200_000},
]
cheap_first = sorted(products, key=lambda p: p["price"])
print("ارزان‌ترین:", cheap_first[0]["name"])
