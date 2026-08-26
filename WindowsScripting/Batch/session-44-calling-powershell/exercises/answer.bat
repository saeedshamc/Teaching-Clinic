:: پاسخ تمرین - جلسه 44: ترکیب Batch با PowerShell
:: این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

@echo off
for /f "delims=" %%p in ('powershell -command "(Get-Process).Count"') do set proccount=%%p
echo تعداد پروسه‌های در حال اجرا: %proccount%
