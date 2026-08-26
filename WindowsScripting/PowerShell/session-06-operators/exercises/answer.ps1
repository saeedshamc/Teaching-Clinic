# پاسخ تمرین - جلسه 6: عملگرها در PowerShell
# این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

$number = 42
if ($number -gt 10 -and $number -lt 100) {
    Write-Host "$number هم بزرگتر از 10 و هم کوچکتر از 100 است"
} else {
    Write-Host "$number در این بازه نیست"
}
