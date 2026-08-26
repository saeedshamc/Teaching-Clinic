:: پاسخ تمرین - جلسه 42: ساخت منوی تعاملی
:: این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

@echo off
:menu
cls
echo 1. نمایش تاریخ و ساعت
echo 2. نمایش اطلاعات سیستم
echo 3. پاکسازی صفحه
echo 4. نمایش فضای دیسک C
echo 5. خروج
set /p choice=گزینه: 

if "%choice%"=="4" (
    wmic logicaldisk where "DeviceID='C:'" get FreeSpace,Size
    pause
    goto menu
)
if "%choice%"=="5" exit
goto menu
