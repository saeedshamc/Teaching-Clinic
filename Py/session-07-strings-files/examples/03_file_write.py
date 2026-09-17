# ============================================================
# جلسه ۷ — نوشتن فایل
# فایل: 03_file_write.py
# ============================================================
import os

# فایل در همان پوشه examples
filename = os.path.join(os.path.dirname(__file__), "demo_output.txt")

lines = [
    "سلام از Python",
    "این فایل با encoding=utf-8 نوشته شده",
    "خط سوم — پایان",
]

with open(filename, "w", encoding="utf-8") as f:
    for line in lines:
        f.write(line + "\n")

print(f"نوشتن در {filename} انجام شد.")
