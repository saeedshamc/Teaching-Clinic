# ============================================================
# تمرین جلسه ۷ — پاسخ نمونه (کلاسی)
# ============================================================

text = "Python Programming"
print(text.upper())
print(text.lower())

parts = "a,b,c,d".split(",")
print(" | ".join(parts))

# نوشتن (مسیر نسبی exercises)
with open("notes.txt", "w", encoding="utf-8") as f:
    f.write("یادداشت ۱\n")
    f.write("یادداشت ۲\n")
    f.write("یادداشت ۳\n")

with open("notes.txt", "r", encoding="utf-8") as f:
    line_count = sum(1 for _ in f)
print("تعداد خط:", line_count)
