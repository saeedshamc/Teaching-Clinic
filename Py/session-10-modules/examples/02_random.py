# ============================================================
# جلسه ۱۰ — random
# فایل: 02_random.py
# ============================================================
import random

random.seed(42)  # تکرارپذیری در دمو

print("تاس:", random.randint(1, 6))
print("انتخاب:", random.choice(["سیب", "موز", "پرتقال"]))

deck = list(range(1, 11))
random.shuffle(deck)
print("بر shuffle:", deck[:3])

print("نمونه:", random.sample(range(100), 5))
