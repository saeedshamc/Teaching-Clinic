# ============================================================
# جلسه ۳۰ — collections
# فایل: 01_collections.py
# ============================================================

from collections import Counter, defaultdict, namedtuple

counts = Counter(["سیب", "سیب", "موز", "سیب", "موز"])
print("شمارش:", counts)

groups = defaultdict(list)
groups["میوه"].append("سیب")
groups["میوه"].append("موز")
print("گروه‌ها:", dict(groups))

Point = namedtuple("Point", ["x", "y"])
p = Point(3, 4)
print("نقطه:", p.x, p.y)
