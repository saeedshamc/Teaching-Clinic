# ============================================================
# تمرین جلسه ۴۴
# هدف: حلقه for و join را برای ساخت رشته مقایسه کنید.
# ============================================================

import timeit

t1 = timeit.timeit('s=""\nfor i in range(100):\n s += str(i)', number=1000)
t2 = timeit.timeit('"".join(str(i) for i in range(100))', number=1000)
print("+= :", round(t1, 5))
print("join:", round(t2, 5))
