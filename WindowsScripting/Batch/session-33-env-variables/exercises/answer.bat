:: پاسخ تمرین - جلسه 33: متغیرهای محیطی (Environment Variables)
:: این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

@echo off
echo مسیر دسکتاپ: %USERPROFILE%\Desktop
if exist "%USERPROFILE%\Desktop" (
    echo این مسیر معتبر است.
)
