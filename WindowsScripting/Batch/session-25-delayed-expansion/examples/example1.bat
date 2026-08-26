:: =====================================================================
:: جلسه 25 - Delayed Expansion (!var! به‌جای %var%)
:: =====================================================================
@echo off
setlocal enabledelayedexpansion

echo بدون delayed expansion (مشکل‌دار):
set count=0
for /L %%i in (1,1,3) do (
    set /a count+=1
    echo مقدار count در حلقه: %count%
)
echo مقدار نهایی: %count%

echo.
echo با delayed expansion (درست):
set count2=0
for /L %%i in (1,1,3) do (
    set /a count2+=1
    echo مقدار count2 در حلقه: !count2!
)
echo مقدار نهایی: %count2%

:: داخل یک بلوک ()، مقدار %var% فقط یک‌بار در شروع بلوک خوانده می‌شود؛
:: !var! هر بار مقدار واقعی و به‌روز را می‌خواند.

:: تمرین: یک حلقه بنویسید که مجموع اعداد ۱ تا ۱۰ را با !var! درست محاسبه کند.
