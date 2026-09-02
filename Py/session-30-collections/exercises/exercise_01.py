# ============================================================
# پاسخ نمونه — تمرین جلسه ۳۰ (ساده)
# ============================================================

from collections import Counter, defaultdict, deque, namedtuple

colors = ["قرمز", "آبی", "قرمز", "سبز", "آبی", "قرمز"]
print("پرتکرار:", Counter(colors).most_common(1)[0])

words = ["cat", "dog", "bat", "rat", "elephant"]
by_len = defaultdict(list)
for w in words:
    by_len[len(w)].append(w)
print("بر اساس طول:", dict(by_len))

Employee = namedtuple("Employee", ["name", "dept", "salary"])
emp = Employee("سعید", "IT", 50_000_000)
print("کارمند:", emp._asdict())

logs = deque(maxlen=3)
for i in range(5):
    logs.append(i)
print("deque:", list(logs))
