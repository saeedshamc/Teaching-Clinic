# =====================================================================
# جلسه 49 - ابزار CLI کامل با پارامترهای متعدد
# =====================================================================
[CmdletBinding()]
param(
    [Parameter(Mandatory=$true, HelpMessage="مسیر پوشه مبدا")]
    [ValidateScript({Test-Path $_})]
    [string]$SourcePath,

    [Parameter(Mandatory=$false)]
    [string]$DestPath = ".\Output",

    [ValidateSet("Copy", "Move")]
    [string]$Operation = "Copy",

    [switch]$WhatIfMode
)

Write-Host "=== ابزار مدیریت فایل ==="
Write-Host "مبدا: $SourcePath"
Write-Host "مقصد: $DestPath"
Write-Host "عملیات: $Operation"

if ($WhatIfMode) {
    Write-Host "[حالت WhatIf] هیچ تغییری واقعا اعمال نمی‌شود."
    Write-Host "اگر اجرا می‌شد: $Operation از $SourcePath به $DestPath"
} else {
    if (-not (Test-Path $DestPath)) { New-Item -ItemType Directory -Path $DestPath | Out-Null }
    if ($Operation -eq "Copy") {
        Copy-Item -Path $SourcePath -Destination $DestPath -Recurse -Force
    } else {
        Move-Item -Path $SourcePath -Destination $DestPath -Force
    }
    Write-Host "عملیات با موفقیت انجام شد."
}

# اجرا: .\example1.ps1 -SourcePath "C:\Data" -Operation Copy -WhatIfMode

# تمرین: پارامتر -Extension اضافه کنید که فقط فایل‌های با پسوند مشخص را عملیات کند.
