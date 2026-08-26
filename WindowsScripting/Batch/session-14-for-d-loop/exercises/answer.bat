:: پاسخ تمرین - جلسه 14: پیمایش پوشه‌ها با FOR /D
:: این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

@echo off
setlocal enabledelayedexpansion
set count=0
for /D %%d in ("%USERPROFILE%\Documents\*") do (
    set /a count+=1
)
echo تعداد زیرپوشه‌های Documents: %count%
