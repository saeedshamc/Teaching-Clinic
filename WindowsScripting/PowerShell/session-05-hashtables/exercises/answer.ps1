# پاسخ تمرین - جلسه 5: HashTable (دیکشنری کلید-مقدار)
# این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

$product = @{ Name = "Laptop"; Price = 20000000; Stock = 5 }
$product.Price = $product.Price * 1.10
Write-Host "قیمت جدید بعد از 10% افزایش: $($product.Price)"
