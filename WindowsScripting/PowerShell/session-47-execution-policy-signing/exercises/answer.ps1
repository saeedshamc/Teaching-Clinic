# پاسخ تمرین - جلسه 47: امنیت: Execution Policy و Code Signing
# این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

Write-Host "Execution Policy کاربر فعلی:"
Get-ExecutionPolicy -Scope CurrentUser
Write-Host "Execution Policy سطح Machine:"
Get-ExecutionPolicy -Scope LocalMachine
