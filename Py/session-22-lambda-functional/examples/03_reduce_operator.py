# ============================================================
# جلسه ۲۲ — reduce و operator
# فایل: 03_reduce_operator.py
# ============================================================

from functools import reduce
from operator import add, mul, itemgetter


numbers = [1, 2, 3, 4, 5]

# --- reduce: تجمیع تدریجی ---
total = reduce(lambda acc, x: acc + x, numbers)
product = reduce(lambda acc, x: acc * x, numbers)
print("جمع:", total)
print("ضرب:", product)

# --- operator module — بدون lambda ---
print("جمع (operator):", reduce(add, numbers))
print("ضرب (operator):", reduce(mul, numbers))

# --- reduce با مقدار اولیه ---
concat = reduce(lambda a, b: a + " " + b, ["سلام", "دنیا", "پایتون"])
print("concat:", concat)

longest = reduce(
    lambda a, b: a if len(a) >= len(b) else b,
    ["سلام", "پایتون", "کد"],
)
print("بلندترین:", longest)

# --- itemgetter به‌جای lambda ---
words = ["banana", "apple", "cherry"]
by_length = sorted(words, key=itemgetter(0))  # بر اساس حرف اول
print("مرتب حرف اول:", by_length)
