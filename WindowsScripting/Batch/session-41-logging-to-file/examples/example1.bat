:: =====================================================================
:: جلسه 41 - سیستم لاگ ساده
:: =====================================================================
@echo off
set logfile=activity.log

call :write_log "اسکریپت شروع شد"
timeout /t 1 /nobreak >nul
call :write_log "در حال پردازش..."
call :write_log "اسکریپت با موفقیت تمام شد"

echo.
echo محتوای فایل لاگ:
type %logfile%
del %logfile%
goto :eof

:write_log
echo [%date% %time%] %~1 >> %logfile%
exit /b

:: تمرین: به تابع لاگ، سطح پیام (INFO/WARNING/ERROR) اضافه کنید: call :write_log "ERROR" "توضیح خطا"
