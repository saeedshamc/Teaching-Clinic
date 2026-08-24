# =====================================================================
# جلسه 46 - Invoke-Command و PSSession (مفهومی)
# =====================================================================
# این جلسه مفهومی است چون نیاز به دو کامپیوتر متصل به شبکه دارد.
# دستورات زیر را روی سیستم واقعی با دسترسی مناسب اجرا کنید.

Write-Host "نمونه کد اجرای دستور روی یک کامپیوتر دیگر (نیاز به WinRM فعال):"
Write-Host @'
Invoke-Command -ComputerName "Server01" -ScriptBlock {
    Get-Process | Select-Object -First 5 Name
} -Credential (Get-Credential)
'@

Write-Host ""
Write-Host "نمونه کد ساخت یک session ماندگار:"
Write-Host @'
$session = New-PSSession -ComputerName "Server01" -Credential (Get-Credential)
Invoke-Command -Session $session -ScriptBlock { Get-Service }
Remove-PSSession $session
'@

Write-Host ""
Write-Host "برای فعال‌سازی WinRM روی یک سیستم (باید Administrator باشید):"
Write-Host "Enable-PSRemoting -Force"

# تمرین: اگر به دو سیستم در یک شبکه دسترسی دارید، WinRM را فعال کنید و یک دستور ساده را روی سیستم دوم اجرا کنید.
