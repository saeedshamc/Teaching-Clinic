# ============================================================
# تمرین جلسه ۵ — پاسخ نمونه (کلاسی)
# ============================================================

# ۱. لیست خرید
shopping = ["نان", "شیر", "تخم‌مرغ", "پنیر", "سبزی"]
shopping.append("ماست")
shopping.pop()
print(shopping)

# ۲. کتاب
book = {"title": "Python", "author": "سعید", "year": 2026}
for k, v in book.items():
    print(k, v)

# ۳. یکتا
print(list(set([1, 2, 2, 3, 3, 3])))

# ۴. میانگین
grades = [16, 18, 14, 19, 15]
total = 0
for g in grades:
    total += g
print("میانگین:", total / len(grades))
