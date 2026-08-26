# پاسخ تمرین - جلسه 48: Start-Transcript برای ثبت کامل یک session
# این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

function Write-AppLog {
    param([string]$Message, [string]$Level = "INFO")
    $line = "[$(Get-Date -Format 'yyyy-MM-dd HH:mm:ss')] [$Level] $Message"
    Add-Content -Path "scenario.log" -Value $line
    Write-Host $line
}
Write-AppLog "برنامه شروع شد"
Write-AppLog "در حال پردازش داده‌ها..."
Write-AppLog "یک هشدار جزئی رخ داد" -Level "WARNING"
Write-AppLog "برنامه با موفقیت پایان یافت"
Remove-Item "scenario.log"
