:: پاسخ تمرین - جلسه 45: نصب‌کننده ساده
:: این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

@echo off
echo === حذف‌کننده MyApp ===
set installDir=%LOCALAPPDATA%\MyApp

if exist "%installDir%" (
    rmdir /s /q "%installDir%"
    echo پوشه نصب حذف شد.
) else (
    echo برنامه نصب نشده بود.
)

if exist "%USERPROFILE%\Desktop\MyApp.lnk" (
    del "%USERPROFILE%\Desktop\MyApp.lnk"
    echo میانبر دسکتاپ حذف شد.
)
echo حذف نصب تمام شد.
