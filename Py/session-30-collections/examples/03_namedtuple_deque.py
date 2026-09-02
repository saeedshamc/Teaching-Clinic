# ============================================================
# جلسه ۳۰ — namedtuple و deque
# ============================================================

from collections import deque, namedtuple

Point = namedtuple("Point", ["x", "y"])
Book = namedtuple("Book", ["title", "author", "pages"])

p = Point(3, 4)
book = Book("پایتون", "سعید", 300)

print("نقطه:", p.x, p.y)
print("کتاب:", book)
print("dict:", book._asdict())
print("کپی با تغییر:", book._replace(pages=350))

# deque — صف log
logs = deque(maxlen=4)
for i in range(6):
    logs.append(f"log #{i}")
print("\nآخرین ۴ log:")
for entry in logs:
    print(" ", entry)

# BFS ساده
queue = deque([1])
visited = []
while queue:
    node = queue.popleft()
    visited.append(node)
    if node < 3:
        queue.append(node + 1)
print("\nBFS:", visited)
