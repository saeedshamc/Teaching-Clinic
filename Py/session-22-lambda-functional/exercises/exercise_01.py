# ============================================================
# تمرین ۱–۳ جلسه ۲۲ — Lambda (کلاسی)
# ============================================================

add = lambda a, b: a + b
print("جمع:", add(3, 5))

numbers = [1, 2, 3, 4, 5]
doubled = list(map(lambda n: n * 2, numbers))
print("دو برابر:", doubled)

names = ["علی", "م", "مریم", "رضا"]
long_names = list(filter(lambda n: len(n) > 3, names))
print("نام‌های بلند:", long_names)
