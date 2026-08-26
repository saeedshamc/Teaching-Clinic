# پاسخ تمرین - جلسه 22: Regular Expressions در PowerShell
# این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

$text = "Hello World"
if ($text -match '^[a-zA-Z ]+$') {
    Write-Host "فقط شامل حروف انگلیسی و فاصله است"
} else {
    Write-Host "شامل کاراکتر دیگری هم هست"
}
