:: پاسخ تمرین - جلسه 12: FOR /F برای خواندن خط به خط یک فایل یا رشته
:: این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

@echo off
setlocal enabledelayedexpansion
(echo 10
echo 20
echo 30
echo 40
echo 50) > numbers.txt

set sum=0
for /f %%n in (numbers.txt) do (
    set /a sum+=%%n
)
echo مجموع اعداد: %sum%
del numbers.txt
