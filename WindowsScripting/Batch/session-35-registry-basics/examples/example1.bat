:: =====================================================================
:: جلسه 35 - خواندن و نوشتن Registry با دستور REG
:: =====================================================================
@echo off
echo خواندن یک مقدار از رجیستری:
reg query "HKCU\Software\Microsoft\Windows\CurrentVersion\Explorer" /v ShowStatusBar 2>nul

echo.
echo نوشتن یک مقدار (در یک کلید تست، امن):
reg add "HKCU\Software\MyTestApp" /v Version /t REG_SZ /d "1.0" /f

echo خواندن مقدار نوشته‌شده:
reg query "HKCU\Software\MyTestApp" /v Version

echo پاک‌سازی (حذف کلید تست):
reg delete "HKCU\Software\MyTestApp" /f

:: ⚠️ همیشه قبل از تغییر رجیستری واقعی، از رجیستری Backup بگیرید (reg export).

:: تمرین: یک مقدار رشته‌ای در یک کلید تست رجیستری بسازید، بخوانید و در پایان حذفش کنید.
