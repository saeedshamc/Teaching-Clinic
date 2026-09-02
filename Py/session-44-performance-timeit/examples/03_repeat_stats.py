# ============================================================
# جلسه ۴۴ — repeat و آمار
# فایل: 03_repeat_stats.py
# ============================================================

import timeit
import statistics

stmt = "sorted(range(500))"
times = timeit.repeat(stmt, number=100, repeat=7)

print("زمان‌های repeat:", [round(t, 5) for t in times])
print(f"min:    {min(times):.5f}s")
print(f"mean:   {statistics.mean(times):.5f}s")
print(f"median: {statistics.median(times):.5f}s")
print("→ برای گزارش از min استفاده کنید (کمترین noise)")
