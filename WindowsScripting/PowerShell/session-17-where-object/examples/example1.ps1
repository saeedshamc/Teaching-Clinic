# =====================================================================
# جلسه 17 - Where-Object برای فیلتر کردن داده
# =====================================================================
$numbers = 1..20

$evens = $numbers | Where-Object { $_ % 2 -eq 0 }
Write-Host "اعداد زوج: $evens"

$processes = Get-Process | Where-Object { $_.WorkingSet -gt 50MB }
Write-Host "تعداد پروسه‌های پرمصرف حافظه: $($processes.Count)"

# سینتکس ساده‌تر (بدون بلوک اسکریپت) در نسخه‌های جدید:
$bigNumbers = $numbers | Where-Object -Property Length -GT 0   # مثال ساختاری

# ترکیب چند شرط
$filtered = $numbers | Where-Object { $_ -gt 5 -and $_ -lt 15 }
Write-Host "بین 5 و 15: $filtered"

# تمرین: از میان اعداد ۱ تا ۱۰۰، اعدادی که هم بر ۳ و هم بر ۵ بخش‌پذیرند را فیلتر کنید.
