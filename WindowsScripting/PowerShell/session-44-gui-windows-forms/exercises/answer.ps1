# پاسخ تمرین - جلسه 44: ساخت رابط گرافیکی ساده با Windows Forms
# این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

Add-Type -AssemblyName System.Windows.Forms
$form = New-Object System.Windows.Forms.Form
$form.Text = "تست دو دکمه"

$btnClose = New-Object System.Windows.Forms.Button
$btnClose.Text = "بستن"
$btnClose.Location = New-Object System.Drawing.Point(10,120)
$btnClose.Add_Click({ $form.Close() })
$form.Controls.Add($btnClose)

Write-Host "دکمه بستن اضافه شد (برای نمایش واقعی: `$form.ShowDialog())"
