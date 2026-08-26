# پاسخ تمرین - جلسه 3: متغیرها و انواع داده در PowerShell
# این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

$a = "PowerShell"
$b = 42
$c = 3.14

Write-Host "نوع a: $($a.GetType().Name)"
Write-Host "نوع b: $($b.GetType().Name)"
Write-Host "نوع c: $($c.GetType().Name)"
