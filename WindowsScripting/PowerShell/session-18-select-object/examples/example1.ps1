# =====================================================================
# جلسه 18 - Select-Object برای انتخاب ستون‌ها
# =====================================================================
Get-Process | Select-Object -First 3 Name, Id, CPU

Write-Host ""
Write-Host "ساخت یک فیلد محاسبه‌شده:"
Get-Process | Select-Object -First 3 Name, @{Name="RAM(MB)"; Expression={[math]::Round($_.WorkingSet/1MB,1)}}

Write-Host ""
Write-Host "انتخاب یک مقدار خاص (نه کل آبجکت):"
$firstProcessName = Get-Process | Select-Object -First 1 -ExpandProperty Name
Write-Host "نام اولین پروسه: $firstProcessName"

# -Unique برای حذف موارد تکراری
$uniqueNames = Get-Process | Select-Object -ExpandProperty Name -Unique
Write-Host "تعداد نام‌های یکتای پروسه: $($uniqueNames.Count)"

# تمرین: از Get-Process فقط نام و مصرف CPU (رند شده تا ۲ رقم اعشار) ۵ پروسه اول را نمایش دهید.
