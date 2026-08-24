:: =====================================================================
:: جلسه 26 - شبیه‌سازی آرایه با نام‌گذاری متغیر
:: =====================================================================
@echo off
setlocal enabledelayedexpansion

set fruit[0]=Apple
set fruit[1]=Banana
set fruit[2]=Cherry
set fruit_count=3

echo همه میوه‌ها:
for /L %%i in (0,1,2) do (
    echo میوه %%i: !fruit[%%i]!
)

:: افزودن پویا به آرایه
set /a fruit_count+=1
set fruit[3]=Orange
echo میوه جدید: !fruit[3]!

:: تمرین: آرایه‌ای از ۵ عدد بسازید و مجموع آن‌ها را با حلقه محاسبه کنید.
