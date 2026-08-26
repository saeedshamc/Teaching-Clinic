# پاسخ تمرین - جلسه 29: Get-Process و Stop-Process
# این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

Get-Process | Sort-Object WorkingSet -Descending | Select-Object -First 5 Name, WorkingSet
