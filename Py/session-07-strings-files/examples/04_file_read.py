# ============================================================
# جلسه ۷ — خواندن فایل
# فایل: 04_file_read.py
# ============================================================
import os

filename = os.path.join(os.path.dirname(__file__), "demo_output.txt")

if not os.path.exists(filename):
    print("ابتدا 03_file_write.py را اجرا کنید.")
else:
    with open(filename, "r", encoding="utf-8") as f:
        content = f.read()
    print("--- محتوای کامل ---")
    print(content)

    with open(filename, "r", encoding="utf-8") as f:
        lines = f.readlines()
    print(f"تعداد خطوط: {len(lines)}")

    with open(filename, "a", encoding="utf-8") as f:
        f.write("خط append شده\n")
    print("یک خط append شد.")
