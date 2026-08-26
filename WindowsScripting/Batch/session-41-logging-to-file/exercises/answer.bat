:: پاسخ تمرین - جلسه 41: سیستم لاگ ساده
:: این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

@echo off
set logfile=leveled.log
call :log "INFO" "برنامه شروع شد"
call :log "WARNING" "یک هشدار جزئی رخ داد"
call :log "ERROR" "یک خطای مهم رخ داد"
type %logfile%
del %logfile%
goto :eof

:log
echo [%date% %time%] [%~1] %~2 >> %logfile%
exit /b
