# پاسخ تمرین - جلسه 23: Get-Content و Set-Content
# این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

1..5 | ForEach-Object { Get-Random -Minimum 1 -Maximum 100 } | Set-Content "randoms.txt"
$sum = 0
Get-Content "randoms.txt" | ForEach-Object { $sum += [int]$_ }
Write-Host "مجموع اعداد: $sum"
Remove-Item "randoms.txt"
