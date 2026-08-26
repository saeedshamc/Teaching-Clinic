:: پاسخ تمرین - جلسه 15: پیمایش بازگشتی (زیرپوشه‌ها) با FOR /R
:: این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

@echo off
setlocal enabledelayedexpansion
set count=0
for /R %%f in (*.txt) do (
    set /a count+=1
)
echo تعداد کل فایل‌های txt (شامل زیرپوشه‌ها): %count%
