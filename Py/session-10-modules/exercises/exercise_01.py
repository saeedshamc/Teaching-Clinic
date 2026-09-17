# ============================================================
# تمرین جلسه ۱۰ — پاسخ نمونه (کلاسی)
# ============================================================
import math
import random
from datetime import date
import os

print("sqrt:", math.sqrt(144))
print("floor:", math.floor(9.9))
print("random:", [random.randint(1, 50) for _ in range(5)])
print("today:", date.today().strftime("%Y-%m-%d"))
print("cwd:", os.getcwd())
print("README exists:", os.path.exists("../README.md"))
