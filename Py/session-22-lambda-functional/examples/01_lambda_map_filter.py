# ============================================================
# جلسه ۲۲ — lambda، map و filter
# فایل: 01_lambda_map_filter.py
# ============================================================

square = lambda x: x * x
print("مربع ۵:", square(5))

numbers = [1, 2, 3, 4, 5]
doubled = list(map(lambda n: n * 2, numbers))
evens = list(filter(lambda n: n % 2 == 0, numbers))

print("دو برابر:", doubled)
print("زوج‌ها:", evens)
