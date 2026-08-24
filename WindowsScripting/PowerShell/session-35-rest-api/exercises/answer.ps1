# پاسخ تمرین - جلسه 35: Invoke-RestMethod برای کار با API
# این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

$repo = Invoke-RestMethod -Uri "https://api.github.com/repos/microsoft/vscode"
Write-Host "نام: $($repo.name)"
Write-Host "تعداد Fork: $($repo.forks_count)"
