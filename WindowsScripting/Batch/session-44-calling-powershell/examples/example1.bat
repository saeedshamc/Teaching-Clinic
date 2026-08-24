:: =====================================================================
:: جلسه 44 - ترکیب Batch با PowerShell
:: =====================================================================
@echo off
echo اجرای یک دستور تک‌خطی PowerShell از داخل bat:
powershell -command "Write-Host 'سلام از PowerShell!' -ForegroundColor Green"

echo.
echo گرفتن خروجی PowerShell در یک متغیر:
for /f "delims=" %%i in ('powershell -command "(Get-Date).ToString()"') do set now=%%i
echo تاریخ و ساعت از PowerShell: %now%

echo.
echo اجرای یک فایل ps1 جداگانه:
:: powershell -ExecutionPolicy Bypass -File "script.ps1"

:: این الگو (bat به‌عنوان لایه رابط ساده، PowerShell به‌عنوان موتور قدرتمند) در پروژه‌های واقعی بسیار رایج است.

:: تمرین: با PowerShell از داخل bat، تعداد پروسه‌های در حال اجرا را بگیرید و در bat چاپ کنید.
