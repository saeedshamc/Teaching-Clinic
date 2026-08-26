# پاسخ تمرین - جلسه 10: حلقه ForEach روی مجموعه‌ها
# این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

$names = @("Ali", "Sara", "Reza", "Mina", "Hasan")
foreach ($name in $names) {
    Write-Host "خوش آمدید، $name عزیز!"
}
