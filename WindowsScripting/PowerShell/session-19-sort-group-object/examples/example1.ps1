# =====================================================================
# جلسه 19 - Sort-Object و Group-Object
# =====================================================================
Get-Process | Sort-Object CPU -Descending | Select-Object -First 5 Name, CPU

Write-Host ""
Write-Host "گروه‌بندی فایل‌ها بر اساس پسوند:"
Get-ChildItem -Path $env:WINDIR -File -ErrorAction SilentlyContinue |
    Group-Object Extension |
    Sort-Object Count -Descending |
    Select-Object -First 5 Name, Count

Write-Host ""
$numbers = @(5, 2, 8, 1, 9, 3)
$sorted = $numbers | Sort-Object
Write-Host "مرتب‌شده صعودی: $sorted"
$sortedDesc = $numbers | Sort-Object -Descending
Write-Host "مرتب‌شده نزولی: $sortedDesc"

# تمرین: لیست پروسه‌ها را بر اساس نام مرتب کنید و فقط ۱۰ تای اول را نمایش دهید.
