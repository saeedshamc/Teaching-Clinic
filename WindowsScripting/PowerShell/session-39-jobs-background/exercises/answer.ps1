# پاسخ تمرین - جلسه 39: Start-Job برای اجرای پس‌زمینه
# این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

$job1 = Start-Job -ScriptBlock { (1..1000 | Measure-Object -Sum).Sum }
$job2 = Start-Job -ScriptBlock { (1..1000 | Measure-Object -Sum).Sum }
$jobs = @($job1, $job2)
$jobs | Wait-Job | Out-Null
$results = $jobs | Receive-Job
Write-Host "نتایج Jobها: $results"
$jobs | Remove-Job
