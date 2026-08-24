:: پاسخ تمرین - جلسه 11: پیمایش فایل‌ها با FOR
:: این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

@echo off
setlocal enabledelayedexpansion
set count=0
for %%f in (*.txt) do (
    set /a count+=1
)
echo تعداد فایل‌های txt: %count%
