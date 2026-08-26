:: پاسخ تمرین - جلسه 8: IF EXIST برای بررسی فایل/پوشه
:: این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

@echo off
if not exist "report.txt" (
    echo گزارش اولیه > report.txt
    echo فایل report.txt ساخته شد.
) else (
    echo فایل report.txt از قبل وجود دارد.
)
