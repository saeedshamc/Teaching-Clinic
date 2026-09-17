# ============================================================
# جلسه ۳۰ — defaultdict: گروه‌بندی
# ============================================================

from collections import defaultdict

students = [
    ("سعید", "پایتون"),
    ("مریم", "پایتون"),
    ("علی", "SQL"),
    ("رضا", "پایتون"),
    ("زهرا", "SQL"),
]

by_course = defaultdict(list)
for name, course in students:
    by_course[course].append(name)

print("دانشجوها بر اساس دوره:")
for course, names in sorted(by_course.items()):
    print(f"  {course}: {', '.join(names)}")

# شمارش با int factory
word_count = defaultdict(int)
for word in "to be or not to be".split():
    word_count[word] += 1
print("\nکلمات:", dict(word_count))

# set factory — تگ‌های یکتا
tags_by_item = defaultdict(set)
tags_by_item["کتاب"].add("آموزشی")
tags_by_item["کتاب"].add("پایتون")
tags_by_item["کتاب"].add("آموزشی")
print("\nتگ کتاب:", tags_by_item["کتاب"])
