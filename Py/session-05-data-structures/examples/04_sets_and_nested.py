# ============================================================
# جلسه ۵ — مجموعه و ساختار تو در تو
# فایل: 04_sets_and_nested.py
# ============================================================

# حذف تکراری
raw = [1, 2, 2, 3, 3, 3, 4]
unique = list(set(raw))
print("یکتا:", unique)

# عملیات مجموعه
a = {1, 2, 3, 4}
b = {3, 4, 5, 6}
print("اجتماع:", a | b)
print("اشتراک:", a & b)
print("تفاضل a-b:", a - b)

# nested — لیست دانشجوها
students = [
    {"name": "علی", "grade": 18},
    {"name": "مریم", "grade": 19},
    {"name": "رضا", "grade": 17},
]

for s in students:
    print(f"{s['name']}: {s['grade']}")

avg = sum(s["grade"] for s in students) / len(students)
print(f"میانگین: {avg:.1f}")
