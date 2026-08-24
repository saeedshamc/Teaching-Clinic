:: =====================================================================
:: جلسه 33 - متغیرهای محیطی (Environment Variables)
:: =====================================================================
@echo off
echo نام کاربری: %USERNAME%
echo کامپیوتر: %COMPUTERNAME%
echo مسیر ویندوز: %WINDIR%
echo پوشه موقت: %TEMP%
echo مسیر پروفایل کاربر: %USERPROFILE%

echo.
echo تنظیم یک متغیر محیطی موقت (فقط برای این session):
set MY_VAR=Hello
echo مقدار: %MY_VAR%

echo.
echo تنظیم دائمی (سطح کاربر) با setx:
setx MY_PERMANENT_VAR "SomeValue"
echo این متغیر در ترمینال‌های بعدی هم باقی می‌ماند.

:: تمرین: یک اسکریپت بسازید که مسیر پوشه دسکتاپ کاربر را با %USERPROFILE%\Desktop نمایش دهد.
