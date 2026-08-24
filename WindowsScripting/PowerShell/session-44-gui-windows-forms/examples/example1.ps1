# =====================================================================
# جلسه 44 - ساخت رابط گرافیکی ساده با Windows Forms
# =====================================================================
Add-Type -AssemblyName System.Windows.Forms
Add-Type -AssemblyName System.Drawing

$form = New-Object System.Windows.Forms.Form
$form.Text = "برنامه تست"
$form.Size = New-Object System.Drawing.Size(300,200)
$form.StartPosition = "CenterScreen"

$label = New-Object System.Windows.Forms.Label
$label.Text = "نام خود را وارد کنید:"
$label.Location = New-Object System.Drawing.Point(10,20)
$label.AutoSize = $true
$form.Controls.Add($label)

$textbox = New-Object System.Windows.Forms.TextBox
$textbox.Location = New-Object System.Drawing.Point(10,50)
$textbox.Width = 260
$form.Controls.Add($textbox)

$button = New-Object System.Windows.Forms.Button
$button.Text = "تایید"
$button.Location = New-Object System.Drawing.Point(10,80)
$button.Add_Click({
    [System.Windows.Forms.MessageBox]::Show("سلام $($textbox.Text)!")
})
$form.Controls.Add($button)

# برای اجرای واقعی این خط را از کامنت خارج کنید (در محیط بدون GUI اجرا نمی‌شود):
# $form.ShowDialog()

Write-Host "فرم گرافیکی تعریف شد (برای نمایش، ShowDialog را از کامنت خارج کنید)."

# تمرین: یک دکمه دوم اضافه کنید که با کلیک، فرم را ببندد (Close()).
