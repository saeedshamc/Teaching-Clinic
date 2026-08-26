# پاسخ تمرین - جلسه 40: ForEach-Object -Parallel (PowerShell 7+)
# این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

if ($PSVersionTable.PSVersion.Major -ge 7) {
    $urls = 1..10 | ForEach-Object { "https://example.com/file$_.txt" }
    $urls | ForEach-Object -Parallel {
        Write-Host "دانلود شبیه‌سازی‌شده: $_"
    } -ThrottleLimit 4
} else {
    Write-Host "این تمرین نیاز به PowerShell 7+ دارد."
}
