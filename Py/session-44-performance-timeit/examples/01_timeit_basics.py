# ============================================================
# جلسه ۴۴ — timeit پایه
# فایل: 01_timeit_basics.py
# ============================================================

import timeit

stmt = "sum([i * i for i in range(1000)])"
elapsed = timeit.timeit(stmt, number=5000)
print(f"stmt: {stmt}")
print(f"5000 تکرار → {elapsed:.4f} ثانیه")
print(f"میانگین هر بار: {elapsed / 5000 * 1_000_000:.2f} میکروثانیه")
