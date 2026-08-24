# پاسخ تمرین - جلسه 33: دسترسی به متغیرهای محیطی با $env:
# این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

if ($env:JAVA_HOME) { Write-Host "JAVA_HOME تنظیم شده: $env:JAVA_HOME" } else { Write-Host "JAVA_HOME تنظیم نشده" }
if ($env:PYTHON_HOME) { Write-Host "PYTHON_HOME تنظیم شده: $env:PYTHON_HOME" } else { Write-Host "PYTHON_HOME تنظیم نشده" }
