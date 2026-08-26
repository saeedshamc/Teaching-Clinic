# =====================================================================
# جلسه 40 - ForEach-Object -Parallel (PowerShell 7+)
# =====================================================================
# نکته: این قابلیت فقط در PowerShell 7 به بعد موجود است، نه Windows PowerShell 5.1

if ($PSVersionTable.PSVersion.Major -ge 7) {
    Write-Host "اجرای موازی روی چند آیتم:"
    1..5 | ForEach-Object -Parallel {
        Start-Sleep -Seconds 1
        "پردازش آیتم $_ تمام شد"
    } -ThrottleLimit 3
} else {
    Write-Host "نسخه فعلی PowerShell از -Parallel پشتیبانی نمی‌کند (نیاز به PowerShell 7+)"
    Write-Host "نسخه فعلی: $($PSVersionTable.PSVersion)"
    Write-Host "به‌جای آن از Start-Job (جلسه قبل) استفاده کنید."
}

# ThrottleLimit تعداد کارهای هم‌زمان را محدود می‌کند تا سیستم overload نشود.

# تمرین: اگر PowerShell 7 دارید، ۱۰ URL را به‌صورت موازی با ThrottleLimit=4 دانلود کنید.
