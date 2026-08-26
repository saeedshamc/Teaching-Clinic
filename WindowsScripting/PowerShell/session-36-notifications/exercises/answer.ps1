# پاسخ تمرین - جلسه 36: نوتیفیکیشن ویندوز
# این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

Add-Type -AssemblyName System.Windows.Forms
Write-Host "شروع عملیات طولانی..."
for ($i = 1; $i -le 5; $i++) { Start-Sleep -Milliseconds 500 }

$notify = New-Object System.Windows.Forms.NotifyIcon
$notify.Icon = [System.Drawing.SystemIcons]::Information
$notify.Visible = $true
$notify.ShowBalloonTip(3000, "پایان عملیات", "عملیات با موفقیت تمام شد", "Info")
Start-Sleep -Seconds 3
$notify.Dispose()
