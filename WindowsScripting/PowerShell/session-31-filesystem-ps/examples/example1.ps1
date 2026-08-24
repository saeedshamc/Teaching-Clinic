# =====================================================================
# جلسه 31 - Get-ChildItem و Test-Path
# =====================================================================
Write-Host "لیست فایل‌ها و پوشه‌های پوشه فعلی:"
Get-ChildItem | Select-Object -First 5 Name, Length, LastWriteTime

Write-Host ""
Write-Host "جستجوی بازگشتی فایل‌های txt:"
Get-ChildItem -Path . -Filter "*.txt" -Recurse -ErrorAction SilentlyContinue | Select-Object -First 5 FullName

Write-Host ""
$path = "C:\Windows"
if (Test-Path $path) {
    Write-Host "$path وجود دارد"
}

# فیلتر بر اساس تاریخ آخرین تغییر
$oldFiles = Get-ChildItem -Path . -File | Where-Object { $_.LastWriteTime -lt (Get-Date).AddDays(-30) }
Write-Host "فایل‌های قدیمی‌تر از ۳۰ روز: $($oldFiles.Count)"

# تمرین: اسکریپتی بسازید که همه فایل‌های بزرگتر از ۱۰ مگابایت را در یک پوشه پیدا کند.
