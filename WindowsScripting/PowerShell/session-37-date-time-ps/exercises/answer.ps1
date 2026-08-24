# پاسخ تمرین - جلسه 37: Get-Date و محاسبات زمانی
# این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

$endOfYear = Get-Date -Month 12 -Day 31 -Hour 23 -Minute 59 -Second 59
$daysLeft = (New-TimeSpan -Start (Get-Date) -End $endOfYear).Days
Write-Host "روزهای باقی‌مانده تا پایان سال: $daysLeft"
