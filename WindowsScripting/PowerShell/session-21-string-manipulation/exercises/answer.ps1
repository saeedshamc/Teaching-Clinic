# پاسخ تمرین - جلسه 21: متدهای رشته در PowerShell
# این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

$text = "  PowerShell is Powerful  "
$trimmed = $text.Trim()
Write-Host "بعد از trim: '$trimmed'"
Write-Host "شروع با PowerShell: $($trimmed.StartsWith('PowerShell'))"
