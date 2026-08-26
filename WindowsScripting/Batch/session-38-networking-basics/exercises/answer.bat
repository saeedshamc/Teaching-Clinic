:: پاسخ تمرین - جلسه 38: دستورات پایه شبکه
:: این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

@echo off
for /f "tokens=2 delims=:" %%a in ('ipconfig ^| findstr /c:"IPv4"') do (
    set ip=%%a
)
set ip=%ip: =%
echo آدرس IPv4: %ip%
