# =====================================================================
# جلسه 32 - Registry با Get-ItemProperty
# =====================================================================
$regPath = "HKCU:\Software\Microsoft\Windows\CurrentVersion\Explorer"
if (Test-Path $regPath) {
    Write-Host "کلید رجیستری موجود است"
}

Write-Host ""
Write-Host "ساخت یک کلید تست:"
New-Item -Path "HKCU:\Software\MyTestApp" -Force | Out-Null
Set-ItemProperty -Path "HKCU:\Software\MyTestApp" -Name "Version" -Value "1.0"

$value = Get-ItemProperty -Path "HKCU:\Software\MyTestApp" -Name "Version"
Write-Host "مقدار خوانده‌شده: $($value.Version)"

Write-Host "پاکسازی:"
Remove-Item -Path "HKCU:\Software\MyTestApp" -Recurse -Force

# تمرین: یک کلید تست با دو مقدار (Name و CreatedDate) بسازید، بخوانید و در پایان حذف کنید.
