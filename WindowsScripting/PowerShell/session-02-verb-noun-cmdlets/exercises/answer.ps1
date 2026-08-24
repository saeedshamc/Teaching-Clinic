# پاسخ تمرین - جلسه 2: ساختار نام‌گذاری دستورات PowerShell
# این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

Get-Command -Verb New | Select-Object -First 5 Name
