# ============================================================
# تمرین ۱ جلسه ۴۴ — list vs set
# ============================================================

import timeit

N = 10_000
TARGET = 9_999

setup = f"data_list = list(range({N})); data_set = set(range({N})); target = {TARGET}"
list_stmt = "target in data_list"
set_stmt = "target in data_set"

list_t = timeit.timeit(list_stmt, setup=setup, number=1000)
set_t = timeit.timeit(set_stmt, setup=setup, number=1000)

print(f"list membership: {list_t:.5f}s")
print(f"set membership:  {set_t:.5f}s")
print(f"set سریع‌تر: {list_t / set_t:.1f}x")
