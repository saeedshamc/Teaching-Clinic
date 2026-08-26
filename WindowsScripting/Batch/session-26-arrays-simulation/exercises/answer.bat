:: پاسخ تمرین - جلسه 26: شبیه‌سازی آرایه با نام‌گذاری متغیر
:: این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

@echo off
setlocal enabledelayedexpansion
set num[0]=5
set num[1]=12
set num[2]=8
set num[3]=20
set num[4]=3
set sum=0
for /L %%i in (0,1,4) do (
    set /a sum+=!num[%%i]!
)
echo مجموع آرایه: !sum!
