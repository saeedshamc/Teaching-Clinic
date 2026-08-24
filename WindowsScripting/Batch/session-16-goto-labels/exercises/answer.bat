:: پاسخ تمرین - جلسه 16: GOTO و Label
:: این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

@echo off
:menu
cls
echo 1. انجام کار A
echo 2. انجام کار B
echo 3. خروج
set /p choice=انتخاب: 

if "%choice%"=="1" (
    echo کار A انجام شد.
    pause
    goto menu
)
if "%choice%"=="2" (
    echo کار B انجام شد.
    pause
    goto menu
)
if "%choice%"=="3" exit
goto menu
