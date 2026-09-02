# ============================================================
# پاسخ نمونه — تمرین جلسه ۳۰ (پیشرفته)
# ============================================================

import re
from collections import ChainMap, Counter, defaultdict

LINES = [
    "پایتون زبان برنامه‌نویسی است",
    "collections ماژول مفیدی است",
    "پایتون و collections",
]


def word_frequency(lines: list[str]) -> Counter:
    tokens = []
    for line in lines:
        tokens.extend(re.findall(r"[\w\u0600-\u06FF]+", line.lower()))
    return Counter(tokens)


def inverted_index(lines: list[str]) -> dict[str, set[int]]:
    index = defaultdict(set)
    for i, line in enumerate(lines, start=1):
        for word in set(re.findall(r"[\w\u0600-\u06FF]+", line.lower())):
            index[word].add(i)
    return dict(index)


freq = word_frequency(LINES)
print("Top 3:", freq.most_common(3))

index = inverted_index(LINES)
print("'پایتون' در سطرها:", index.get("پایتون", set()))

defaults = {"debug": False, "lang": "fa"}
user = {"debug": True}
config = ChainMap(user, defaults)
print("config:", dict(config))

c1 = Counter(a=2, b=1)
c2 = Counter(a=1, c=3)
print("جمع:", c1 + c2)
print("تفریق:", c1 - c2)
