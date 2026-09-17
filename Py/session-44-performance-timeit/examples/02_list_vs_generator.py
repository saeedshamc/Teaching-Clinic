# ============================================================
# جلسه ۴۴ — list vs generator
# فایل: 02_list_vs_generator.py
# ============================================================

import timeit

N = 5000
REPEAT = 500

list_stmt = f"sum([i*i for i in range({N})])"
gen_stmt = f"sum(i*i for i in range({N}))"

list_time = timeit.timeit(list_stmt, number=REPEAT)
gen_time = timeit.timeit(gen_stmt, number=REPEAT)

print(f"N={N}, repeat={REPEAT}")
print(f"list comprehension: {list_time:.5f}s")
print(f"generator:          {gen_time:.5f}s")
if list_time < gen_time:
    print("list سریع‌تر بود")
else:
    print("generator سریع‌تر بود")
