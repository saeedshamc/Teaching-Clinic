# ============================================================
# تمرین جلسه ۳۰
# هدف: با Counter پرتکرارترین کلمه را پیدا کنید.
# ============================================================

from collections import Counter

words = ["python", "git", "python", "linux", "python"]
print(Counter(words).most_common(1))
