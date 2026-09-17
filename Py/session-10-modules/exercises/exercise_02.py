# ============================================================
# تمرین جلسه ۱۰ — پاسخ نمونه (خانه)
# ============================================================
import random
from datetime import date, timedelta
from collections import Counter

# تاس ۱۰۰ بار
rolls = [random.randint(1, 6) for _ in range(100)]
freq = Counter(rolls)
for face in sorted(freq):
    print(f"{face}: {freq[face]}")

# countdown
target = date(2026, 12, 31)
days = (target - date.today()).days
print(f"{days} روز تا پایان ۲۰۲۶")
