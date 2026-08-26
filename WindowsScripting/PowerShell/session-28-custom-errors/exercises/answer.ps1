# پاسخ تمرین - جلسه 28: ساخت و پرتاب خطای سفارشی
# این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

function Test-Email {
    param([string]$Email)
    if ($Email -notmatch '^[\w.-]+@[\w.-]+\.\w+$') {
        throw "فرمت ایمیل نامعتبر است: $Email"
    }
    Write-Host "ایمیل معتبر است: $Email"
}
try {
    Test-Email -Email "test@example.com"
    Test-Email -Email "invalid-email"
}
catch {
    Write-Host "خطا: $($_.Exception.Message)" -ForegroundColor Red
}
