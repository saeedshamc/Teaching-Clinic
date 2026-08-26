:: =====================================================================
:: جلسه 08 - IF EXIST برای بررسی فایل/پوشه
:: =====================================================================
@echo off
if exist "config.txt" (
    echo فایل config.txt پیدا شد.
) else (
    echo فایل config.txt وجود ندارد؛ در حال ساخت...
    echo settings=default > config.txt
)

if exist "C:\Windows\" (
    echo پوشه ویندوز موجود است.
)

if not exist "logs" (
    mkdir logs
    echo پوشه logs ساخته شد.
)

:: تمرین: اسکریپتی بنویسید که بررسی کند فایل report.txt هست یا نه؛ اگر نبود بسازدش و یک خط داخلش بنویسد.
