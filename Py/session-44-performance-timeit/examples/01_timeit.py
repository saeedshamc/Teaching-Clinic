# ============================================================
# جلسه ۴۴ — timeit
# فایل: 01_timeit.py
# ============================================================

import timeit

list_time = timeit.timeit("sum([i*i for i in range(1000)])", number=500)
gen_time = timeit.timeit("sum(i*i for i in range(1000))", number=500)
print("list comprehension:", round(list_time, 5))
print("generator:", round(gen_time, 5))
