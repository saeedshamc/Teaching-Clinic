:: پاسخ تمرین - جلسه 34: مدیریت متغیر PATH
:: این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

@echo off
where notepad >nul 2>&1
if %errorlevel% equ 0 (
    echo notepad در PATH موجود است.
) else (
    echo notepad در PATH نیست.
)

where powershell >nul 2>&1
if %errorlevel% equ 0 (
    echo powershell در PATH موجود است.
) else (
    echo powershell در PATH نیست.
)
