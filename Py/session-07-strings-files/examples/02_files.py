# ============================================================
# جلسه ۷ — کار با فایل‌ها
# فایل: 02_files.py
# ============================================================

from pathlib import Path

file_path = Path(__file__).with_name("output.txt")

with file_path.open("w", encoding="utf-8") as file:
    file.write("سلام از فایل\n")
    file.write("این متن در فایل ذخیره شد.\n")

with file_path.open("r", encoding="utf-8") as file:
    content = file.read()
    print(content)
