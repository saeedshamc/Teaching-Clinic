# =====================================================================
# جلسه 07 - شرط‌ها در PowerShell
# =====================================================================
$score = Read-Host "نمره خود را وارد کنید"
$score = [int]$score

if ($score -ge 90) {
    Write-Host "عالی!" -ForegroundColor Green
} elseif ($score -ge 70) {
    Write-Host "خوب" -ForegroundColor Yellow
} elseif ($score -ge 50) {
    Write-Host "قابل قبول" -ForegroundColor DarkYellow
} else {
    Write-Host "نیاز به تلاش بیشتر" -ForegroundColor Red
}

# شرط تک‌خطی (ternary-like) با if
$status = if ($score -ge 50) { "قبول" } else { "مردود" }
Write-Host "وضعیت نهایی: $status"

# تمرین: با گرفتن سن از کاربر، رده سنی (کودک/نوجوان/بزرگسال/سالمند) را چاپ کنید.
