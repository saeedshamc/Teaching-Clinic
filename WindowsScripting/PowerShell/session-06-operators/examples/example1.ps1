# =====================================================================
# جلسه 06 - عملگرها در PowerShell
# =====================================================================
$a = 10
$b = 20

Write-Host "برابر: $($a -eq $b)"
Write-Host "نابرابر: $($a -ne $b)"
Write-Host "بزرگتر: $($a -gt $b)"
Write-Host "کوچکتر: $($a -lt $b)"

# عملگرهای منطقی
$isAdult = $true
$hasLicense = $false
Write-Host "AND: $($isAdult -and $hasLicense)"
Write-Host "OR: $($isAdult -or $hasLicense)"
Write-Host "NOT: $(-not $isAdult)"

# عملگر -like برای الگوی رشته (wildcard) و -match برای regex
Write-Host "'Batch' -like 'B*': $('Batch' -like 'B*')"
Write-Host "'Batch123' -match '\d+': $('Batch123' -match '\d+')"

# تمرین: با عملگرهای مقایسه بررسی کنید آیا عددی هم بزرگتر از ۱۰ و هم کوچکتر از ۱۰۰ است.
