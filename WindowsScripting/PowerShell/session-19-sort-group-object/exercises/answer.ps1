# پاسخ تمرین - جلسه 19: Sort-Object و Group-Object
# این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

Get-Process | Sort-Object Name | Select-Object -First 10 Name
