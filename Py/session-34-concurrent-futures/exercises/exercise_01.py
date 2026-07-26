# ============================================================
# تمرین جلسه ۳۴
# هدف: طول چند رشته را هم‌زمان حساب کنید.
# ============================================================

from concurrent.futures import ThreadPoolExecutor

words = ["python", "git", "linux"]
with ThreadPoolExecutor() as pool:
    print(list(pool.map(len, words)))
