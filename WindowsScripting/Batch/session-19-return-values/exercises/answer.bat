:: پاسخ تمرین - جلسه 19: شبیه‌سازی بازگشت مقدار با errorlevel
:: این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

@echo off
call :check_sign -5
if %errorlevel% equ 0 (
    echo عدد مثبت است
) else (
    echo عدد منفی است
)
goto :eof

:check_sign
if %1 geq 0 (
    exit /b 0
) else (
    exit /b 1
)
