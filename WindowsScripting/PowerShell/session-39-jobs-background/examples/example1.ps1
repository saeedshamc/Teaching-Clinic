# =====================================================================
# جلسه 39 - Start-Job برای اجرای پس‌زمینه
# =====================================================================
Write-Host "شروع یک Job در پس‌زمینه:"
$job = Start-Job -ScriptBlock {
    Start-Sleep -Seconds 3
    return "کار پس‌زمینه تمام شد!"
}

Write-Host "در حال انجام کارهای دیگر در حین اجرای Job..."
Write-Host "وضعیت Job: $($job.State)"

Write-Host "منتظر ماندن برای اتمام Job:"
Wait-Job $job | Out-Null
$result = Receive-Job $job
Write-Host "نتیجه: $result"

Remove-Job $job

Write-Host ""
Write-Host "اجرای چند Job موازی:"
$jobs = 1..3 | ForEach-Object {
    Start-Job -ScriptBlock { param($n) Start-Sleep -Seconds 1; "Job $n تمام شد" } -ArgumentList $_
}
$jobs | Wait-Job | Receive-Job

# تمرین: دو Job بسازید که هرکدام یک محاسبه ساده (مثل جمع اعداد ۱ تا ۱۰۰۰) را انجام دهند و نتیجه هرکدام را بگیرید.
