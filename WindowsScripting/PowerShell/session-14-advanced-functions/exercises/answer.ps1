# پاسخ تمرین - جلسه 14: توابع پیشرفته با اعتبارسنجی پارامتر
# این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

function Set-OrderStatus {
    param(
        [ValidateSet("Pending","Shipped","Delivered")]
        [string]$Status
    )
    Write-Host "وضعیت سفارش: $Status"
}
Set-OrderStatus -Status "Shipped"
