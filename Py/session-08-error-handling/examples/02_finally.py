# ============================================================
# جلسه ۸ — استفاده از finally
# فایل: 02_finally.py
# ============================================================

from pathlib import Path

try:
    file_path = Path(__file__).with_name("missing.txt")
    file = file_path.open("r", encoding="utf-8")
    content = file.read()
    print(content)
except FileNotFoundError:
    print("فایل پیدا نشد")
finally:
    print("این بخش همواره اجرا می‌شود")
