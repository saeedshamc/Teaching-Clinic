# پاسخ تمرین - جلسه 26: کار با XML در PowerShell
# این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

[xml]$store = @"
<Store>
    <Product><Name>Keyboard</Name><Price>500000</Price></Product>
    <Product><Name>Mouse</Name><Price>250000</Price></Product>
    <Product><Name>Monitor</Name><Price>3000000</Price></Product>
</Store>
"@
$total = 0
$store.Store.Product | ForEach-Object { $total += [int]$_.Price }
Write-Host "مجموع قیمت محصولات: $total"
