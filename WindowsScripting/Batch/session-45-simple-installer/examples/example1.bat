:: =====================================================================
:: جلسه 45 - نصب‌کننده ساده
:: =====================================================================
@echo off
echo === نصب‌کننده MyApp ===
set installDir=%LOCALAPPDATA%\MyApp

if exist "%installDir%" (
    echo برنامه قبلا نصب شده است.
    exit /b 0
)

mkdir "%installDir%"
echo فایل‌های برنامه در حال کپی...
echo این یک فایل نمونه است > "%installDir%\app_data.txt"

echo ایجاد میانبر روی دسکتاپ...
powershell -command "$s=(New-Object -COM WScript.Shell).CreateShortcut('%USERPROFILE%\Desktop\MyApp.lnk'); $s.TargetPath='%installDir%\app_data.txt'; $s.Save()"

echo نصب با موفقیت در %installDir% انجام شد!

:: تمرین: یک uninstaller.bat جدا بسازید که پوشه نصب و میانبر دسکتاپ را حذف کند.
