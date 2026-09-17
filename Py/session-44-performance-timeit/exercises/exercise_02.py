# ============================================================
# تمرین ۲ جلسه ۴۴ — join vs +=
# ============================================================

import timeit

words = ["word"] * 1000
setup = "words = ['word'] * 1000"

concat_stmt = """
result = ''
for w in words:
    result += w
"""

join_stmt = "result = ''.join(words)"

concat_t = timeit.timeit(concat_stmt, setup=setup, number=500)
join_t = timeit.timeit(join_stmt, setup=setup, number=500)

print(f"concat (+=): {concat_t:.5f}s")
print(f"join:        {join_t:.5f}s")
print(f"join سریع‌تر: {concat_t / join_t:.1f}x")
