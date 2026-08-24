# =====================================================================
# جلسه 25 - ConvertTo-Json و ConvertFrom-Json
# =====================================================================
$config = @{
    AppName = "MyTool"
    Version = "1.0"
    Settings = @{
        Debug = $true
        MaxUsers = 100
    }
}

$json = $config | ConvertTo-Json -Depth 5
Write-Host "خروجی JSON:"
Write-Host $json

$json | Set-Content "config.json"

$loaded = Get-Content "config.json" -Raw | ConvertFrom-Json
Write-Host ""
Write-Host "خواندن دوباره از فایل:"
Write-Host "نام برنامه: $($loaded.AppName)"
Write-Host "حداکثر کاربران: $($loaded.Settings.MaxUsers)"

Remove-Item "config.json"

# تمرین: یک آبجکت شامل اطلاعات ۳ کتاب (نام، نویسنده، سال) بسازید و به JSON تبدیل کنید.
