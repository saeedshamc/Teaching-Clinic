:: پاسخ تمرین - جلسه 32: تاریخ و ساعت سیستم
:: این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

@echo off
set logname=log_%date:~-4%%date:~3,2%%date:~0,2%_%time:~0,2%%time:~3,2%.txt
set logname=%logname: =0%
echo لاگ در تاریخ %date% ساعت %time% > "%logname%"
echo فایل ساخته شد: %logname%
del "%logname%"
