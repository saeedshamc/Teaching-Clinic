:: پاسخ تمرین - جلسه 46: زمان‌بندی خودکار اسکریپت با schtasks
:: این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

@echo off
schtasks /create /tn "WeeklyBackup" /tr "notepad.exe" /sc weekly /d MON /st 08:00 /f
echo Task هفتگی ساخته شد. جزئیات:
schtasks /query /tn "WeeklyBackup"
schtasks /delete /tn "WeeklyBackup" /f
