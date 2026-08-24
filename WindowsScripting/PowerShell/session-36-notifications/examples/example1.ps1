# =====================================================================
# جلسه 36 - نوتیفیکیشن ویندوز
# =====================================================================
Add-Type -AssemblyName System.Windows.Forms

$notify = New-Object System.Windows.Forms.NotifyIcon
$notify.Icon = [System.Drawing.SystemIcons]::Information
$notify.Visible = $true
$notify.ShowBalloonTip(3000, "پیام تست", "این یک نوتیفیکیشن آزمایشی است", "Info")

Start-Sleep -Seconds 3
$notify.Dispose()

Write-Host "نوتیفیکیشن نمایش داده شد."

# ارسال ایمیل (نیاز به تنظیمات SMTP واقعی دارد - این فقط ساختار کد است):
# Send-MailMessage -From "bot@myapp.com" -To "me@myapp.com" -Subject "گزارش" `
#     -Body "متن پیام" -SmtpServer "smtp.gmail.com" -Port 587 -UseSsl `
#     -Credential (Get-Credential)

# تمرین: اسکریپتی بسازید که وقتی یک عملیات فرضی (مثل حلقه طولانی) تمام شد، نوتیفیکیشن نمایش دهد.
