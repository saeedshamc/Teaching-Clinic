# =====================================================================
# جلسه 48 - Start-Transcript برای ثبت کامل یک session
# =====================================================================
Start-Transcript -Path "session_log.txt"

Write-Host "این خط و همه دستورات بعدی در فایل لاگ ثبت می‌شوند."
$x = 5 + 3
Write-Host "نتیجه محاسبه: $x"
Get-Date

Stop-Transcript

Write-Host ""
Write-Host "محتوای فایل لاگ:"
Get-Content "session_log.txt" | Select-Object -First 10
Remove-Item "session_log.txt"

# روش دوم: لاگ‌گیری دستی و ساخت‌یافته
function Write-AppLog {
    param([string]$Message, [string]$Level = "INFO")
    $line = "[$(Get-Date -Format 'yyyy-MM-dd HH:mm:ss')] [$Level] $Message"
    Add-Content -Path "app.log" -Value $line
    Write-Host $line
}
Write-AppLog "برنامه شروع شد"
Write-AppLog "یک هشدار رخ داد" -Level "WARNING"
Remove-Item "app.log"

# تمرین: با Write-AppLog یک سناریوی کامل (شروع، پردازش، پایان) را لاگ بگیرید.
