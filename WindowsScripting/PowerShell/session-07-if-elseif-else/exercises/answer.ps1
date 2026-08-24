# پاسخ تمرین - جلسه 7: شرط‌ها در PowerShell
# این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

$age = [int](Read-Host "سن خود را وارد کنید")
if ($age -lt 13) { Write-Host "کودک" }
elseif ($age -lt 20) { Write-Host "نوجوان" }
elseif ($age -lt 60) { Write-Host "بزرگسال" }
else { Write-Host "سالمند" }
