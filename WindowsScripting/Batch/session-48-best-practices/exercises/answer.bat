:: پاسخ تمرین - جلسه 48: Best Practices در Batch
:: این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

@echo off
setlocal enabledelayedexpansion
:: اعمال 6 best practice روی یک اسکریپت قدیمی نمونه:
:: 1) @echo off در ابتدا  2) setlocal  3) کوتیشن دور مسیرها
:: 4) exist چک قبل از حذف  5) نام متغیر معنادار  6) exit /b در پایان زیربرنامه

set "targetFile=old_temp_data.txt"
echo test > "%targetFile%"

if exist "%targetFile%" (
    del "%targetFile%"
    echo فایل با رعایت best practiceها حذف شد.
)
endlocal
exit /b 0
