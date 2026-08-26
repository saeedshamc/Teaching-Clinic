:: پاسخ تمرین - جلسه 50: پروژه نهایی: ابزار کامل پشتیبان‌گیری + مانیتور + لاگ
:: این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

@echo off
schtasks /create /tn "DailyFinalBackup" /tr "\"%~dp0final_tool.bat\" \"C:\ImportantData\"" /sc daily /st 23:00 /f
echo Task روزانه برای final_tool.bat ساعت 23:00 ثبت شد.
schtasks /query /tn "DailyFinalBackup"
schtasks /delete /tn "DailyFinalBackup" /f
