# =====================================================================
# جلسه 33 - دسترسی به متغیرهای محیطی با $env:
# =====================================================================
Write-Host "نام کاربری: $env:USERNAME"
Write-Host "کامپیوتر: $env:COMPUTERNAME"
Write-Host "پوشه موقت: $env:TEMP"

Write-Host ""
Write-Host "تنظیم موقت یک متغیر محیطی:"
$env:MY_VAR = "HelloFromPowerShell"
Write-Host "مقدار: $env:MY_VAR"

Write-Host ""
Write-Host "همه متغیرهای محیطی (۵ تای اول):"
Get-ChildItem Env: | Select-Object -First 5 Name, Value

# تنظیم دائمی (سطح کاربر) با .NET
[System.Environment]::SetEnvironmentVariable("MY_PERMANENT_VAR", "SomeValue", "User")

# تمرین: بررسی کنید آیا متغیر محیطی JAVA_HOME یا PYTHON_HOME روی سیستم شما تنظیم شده است.
