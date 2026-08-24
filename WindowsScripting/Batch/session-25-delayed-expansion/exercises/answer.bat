:: پاسخ تمرین - جلسه 25: Delayed Expansion (!var! به‌جای %var%)
:: این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

@echo off
setlocal enabledelayedexpansion
set sum=0
for /L %%i in (1,1,10) do (
    set /a sum+=%%i
)
echo مجموع اعداد 1 تا 10: !sum!
