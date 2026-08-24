# پاسخ تمرین - جلسه 4: آرایه‌ها در PowerShell
# این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

$grades = @(18, 15, 20, 12, 17)
$avg = ($grades | Measure-Object -Average).Average
Write-Host "میانگین نمرات: $avg"
