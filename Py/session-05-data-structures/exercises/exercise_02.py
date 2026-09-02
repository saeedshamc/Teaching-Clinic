# ============================================================
# تمرین جلسه ۵ — پاسخ نمونه (خانه)
# ============================================================

# ۵. دفترچه تلفن
phonebook = {"علی": "09121111111", "مریم": "09122222222"}
name = "علی"
print(phonebook.get(name, "یافت نشد"))

# ۶. دانشجوها
students = [
    {"name": "علی", "grade": 18},
    {"name": "مریم", "grade": 19},
    {"name": "رضا", "grade": 17},
]
best = max(students, key=lambda s: s["grade"])
avg = sum(s["grade"] for s in students) / len(students)
print("بهترین:", best["name"], best["grade"])
print("میانگین:", avg)

# ۷. فراوانی حروف
text = "programming"
freq = {}
for ch in text:
    freq[ch] = freq.get(ch, 0) + 1
print(freq)
