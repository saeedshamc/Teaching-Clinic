:: پاسخ تمرین - جلسه 10: حلقه عددی FOR /L
:: این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

@echo off
echo جدول ضرب عدد 7:
for /L %%i in (1,1,10) do (
    set /a result=7 * %%i
    setlocal enabledelayedexpansion
    echo 7 x %%i = !result!
    endlocal
)
