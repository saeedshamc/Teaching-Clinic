:: پاسخ تمرین - جلسه 47: تکنیک‌های دیباگ
:: این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

@echo off
setlocal enabledelayedexpansion
:: نسخه باگ‌دار (کامنت شده):
:: set count=0
:: for /L %%i in (1,1,3) do (
::     set /a count+=1
::     echo مقدار غلط: %count%
:: )

:: نسخه رفع‌شده:
set count=0
for /L %%i in (1,1,3) do (
    set /a count+=1
    echo مقدار درست: !count!
)
echo باگ با استفاده از !count! به‌جای %%count%% رفع شد.
