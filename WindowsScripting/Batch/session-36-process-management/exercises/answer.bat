:: پاسخ تمرین - جلسه 36: tasklist و taskkill
:: این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

@echo off
tasklist | findstr /i "chrome.exe" >nul
if %errorlevel% equ 0 (
    echo مرورگر Chrome در حال اجراست.
) else (
    echo مرورگر Chrome اجرا نشده است.
)
