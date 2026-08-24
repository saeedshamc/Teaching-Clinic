# =====================================================================
# جلسه 41 - کار با ماژول‌ها (Modules)
# =====================================================================
Write-Host "لیست ماژول‌های در دسترس (۵ تای اول):"
Get-Module -ListAvailable | Select-Object -First 5 Name, Version

Write-Host ""
Write-Host "Import کردن یک ماژول داخلی:"
Import-Module Microsoft.PowerShell.Management -Force
Write-Host "ماژول با موفقیت بارگذاری شد."

# ساخت یک ماژول ساده (در عمل در فایل جدا با پسوند .psm1 ذخیره می‌شود)
function MyModule_SayHello {
    param([string]$Name)
    Write-Host "سلام $Name از داخل ماژول من!"
}
MyModule_SayHello -Name "Saeed"

# برای ساخت ماژول واقعی:
# 1) توابع را در فایل MyModule.psm1 بنویسید
# 2) با Import-Module .\MyModule.psm1 آن را بارگذاری کنید

# تمرین: دو تابع ساده بنویسید و تصور کنید آن‌ها را در یک فایل .psm1 قرار می‌دهید؛ نام ماژول را MyTools.psm1 بگذارید.
