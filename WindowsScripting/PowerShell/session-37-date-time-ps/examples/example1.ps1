# =====================================================================
# جلسه 37 - Get-Date و محاسبات زمانی
# =====================================================================
$now = Get-Date
Write-Host "تاریخ و ساعت فعلی: $now"
Write-Host "فقط سال: $($now.Year)"
Write-Host "فقط ماه: $($now.Month)"
Write-Host "نام روز هفته: $($now.DayOfWeek)"

Write-Host ""
Write-Host "فرمت سفارشی:"
Write-Host $now.ToString("yyyy-MM-dd HH:mm:ss")

Write-Host ""
Write-Host "محاسبات زمانی:"
$tomorrow = $now.AddDays(1)
$lastWeek = $now.AddDays(-7)
Write-Host "فردا: $($tomorrow.ToString('yyyy-MM-dd'))"
Write-Host "هفته قبل: $($lastWeek.ToString('yyyy-MM-dd'))"

$diff = New-TimeSpan -Start $lastWeek -End $now
Write-Host "تفاوت روزها: $($diff.Days)"

# تمرین: اسکریپتی بسازید که محاسبه کند چند روز تا پایان سال میلادی باقی مانده است.
