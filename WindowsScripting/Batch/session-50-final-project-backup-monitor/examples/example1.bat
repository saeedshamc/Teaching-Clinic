:: =====================================================================
:: جلسه 50 - پروژه نهایی: ابزار کامل پشتیبان‌گیری + مانیتور + لاگ
:: این پروژه همه مهارت‌های ۴۹ جلسه قبل را ترکیب می‌کند
:: =====================================================================
@echo off
setlocal enabledelayedexpansion

set "source=%~1"
set "backupRoot=Backups"
set "logfile=tool.log"

if "%source%"=="" (
    echo استفاده: final_tool.bat "مسیر پوشه مبدا"
    exit /b 1
)

if not exist "%source%" (
    call :log "خطا: مسیر مبدا یافت نشد: %source%"
    exit /b 1
)

call :log "شروع فرآیند پشتیبان‌گیری"

set "backupName=%backupRoot%\backup_%date:~-4%%date:~3,2%%date:~0,2%_%time:~0,2%%time:~3,2%"
set "backupName=%backupName: =0%"

mkdir "%backupRoot%" 2>nul
xcopy "%source%" "%backupName%" /E /I /Y >nul

if %errorlevel% equ 0 (
    call :log "پشتیبان‌گیری موفق: %backupName%"
) else (
    call :log "خطا در پشتیبان‌گیری"
    exit /b 1
)

echo.
echo === گزارش نهایی ===
for /f %%c in ('dir "%backupName%" /s /b /a-d ^| find /c /v ""') do (
    call :log "تعداد فایل‌های پشتیبان‌گیری‌شده: %%c"
)

echo فرآیند با موفقیت تمام شد. جزئیات در %logfile%
goto :eof

:log
echo [%date% %time%] %~1 >> %logfile%
echo %~1
exit /b

:: این پروژه شامل: پارامتر ورودی، مدیریت خطا، تاریخ/ساعت، xcopy، تابع لاگ، و شمارش فایل با for /f است.
:: تمرین نهایی: به این ابزار قابلیت زمان‌بندی خودکار روزانه با schtasks (جلسه ۴۶) اضافه کنید.
