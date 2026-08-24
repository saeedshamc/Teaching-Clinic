:: =====================================================================
:: جلسه 23 - جایگزینی متن در رشته
:: =====================================================================
@echo off
set message=Hello World, Hello Batch

echo اصلی: %message%
echo جایگزین شده: %message:Hello=Hi%

set path_var=C:\Users\Saeed\Documents
echo مسیر اصلی: %path_var%
echo با اسلش لینوکسی: %path_var:\=/%

:: تمرین: در رشته "I like Python and Python is great" کلمه Python را با Batch جایگزین کنید.
