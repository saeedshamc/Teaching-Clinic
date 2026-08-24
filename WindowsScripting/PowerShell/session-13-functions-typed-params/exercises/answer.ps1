# پاسخ تمرین - جلسه 13: پارامترهای تایپ‌دار و مقدار پیش‌فرض
# این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

function Get-Area {
    param([double]$Length, [double]$Width, [string]$Unit = "متر")
    $area = $Length * $Width
    Write-Host "مساحت: $area مربع $Unit"
}
Get-Area -Length 5 -Width 3
