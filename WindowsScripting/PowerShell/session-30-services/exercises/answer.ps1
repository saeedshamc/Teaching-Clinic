# پاسخ تمرین - جلسه 30: Get-Service و مدیریت سرویس‌ها
# این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

$stopped = Get-Service | Where-Object { $_.Status -eq "Stopped" }
Write-Host "تعداد سرویس‌های متوقف‌شده: $($stopped.Count)"
