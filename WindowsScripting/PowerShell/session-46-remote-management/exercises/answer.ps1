# پاسخ تمرین - جلسه 46: Invoke-Command و PSSession (مفهومی)
# این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

# نیاز به دو سیستم متصل به شبکه با WinRM فعال دارد:
# روی هر دو سیستم: Enable-PSRemoting -Force
# سپس از سیستم اول:
# Invoke-Command -ComputerName "IP_یا_نام_سیستم_دوم" -ScriptBlock { Get-Date } -Credential (Get-Credential)
Write-Host "این تمرین نیاز به محیط شبکه واقعی دارد؛ کد نمونه در کامنت بالا آورده شده است."
