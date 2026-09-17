# ============================================================
# تمرین جلسه ۷ — پاسخ نمونه (خانه)
# ============================================================
from datetime import datetime

# word count
sample = "Python is great Python is fun"
print("کلمات:", len(sample.split()))

# log append
with open("app.log", "a", encoding="utf-8") as f:
    f.write(f"{datetime.now():%Y-%m-%d %H:%M:%S} — برنامه اجرا شد\n")

# grep ساده
keyword = "Python"
text_block = "Java is ok\nPython is great\nC# is fine\n"
for line in text_block.splitlines():
    if keyword.lower() in line.lower():
        print("→", line)
