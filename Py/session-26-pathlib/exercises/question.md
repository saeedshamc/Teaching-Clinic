# تمرین‌های جلسه ۲۶ — pathlib

## کلاسی (۳۰ دقیقه)

1. **تحلیل مسیر:** مسیر `data/reports/2024/summary.pdf` بسازید؛ `name`, `stem`, `suffix`, `parent` را چاپ کنید.
2. **پوشه data:** پوشه `data/` را با `mkdir` بسازید و فایل `hello.txt` با متن فارسی بنویسید.
3. **glob:** همه فایل‌های `.py` در پوشه `examples/` را لیست کنید.
4. **exists:** قبل از خواندن فایل، با `exists()` بررسی کنید؛ در صورت نبود پیام مناسب چاپ کنید.

## خانه (۴۵ دقیقه)

5. **بزرگترین فایل:** تابع `largest_file(folder: Path) -> Path | None` بنویسید که بزرگترین فایل را برگرداند.
6. **شمارش خطوط:** تابع `count_lines(path: Path) -> int` برای شمارش خطوط یک فایل متنی.
7. **Backup ساده:** همه `.txt` فایل‌های یک پوشه را در `backup/` کپی کنید (`shutil.copy2`).
8. **ساختار پروژه:** ثابت‌های `ROOT`, `DATA`, `LOGS` تعریف کنید و log روزانه بنویسید.

## نکات

- همیشه `encoding="utf-8"` برای متن فارسی
- `Path(__file__).parent` برای مسیر نسبت به اسکریپت
- `parents=True, exist_ok=True` برای mkdir تو در تو
- `missing_ok=True` در `unlink` برای جلوگیری از خطا

پاسخ نمونه: [exercise_01.py](./exercise_01.py) | [exercise_02.py](./exercise_02.py)
