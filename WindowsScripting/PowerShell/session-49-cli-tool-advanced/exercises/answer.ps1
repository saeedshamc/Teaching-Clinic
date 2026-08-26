# پاسخ تمرین - جلسه 49: ابزار CLI کامل با پارامترهای متعدد
# این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

param(
    [string]$SourcePath = ".",
    [string]$Extension = "*.*"
)
Get-ChildItem -Path $SourcePath -Filter $Extension -File | ForEach-Object {
    Write-Host "فایل منطبق: $($_.Name)"
}
# اجرا: .\example1.ps1 -SourcePath "C:\Data" -Extension "*.txt"
