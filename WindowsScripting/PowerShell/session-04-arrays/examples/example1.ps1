# =====================================================================
# جلسه 04 - آرایه‌ها در PowerShell
# =====================================================================
$fruits = @("Apple", "Banana", "Cherry")
Write-Host "همه میوه‌ها: $fruits"
Write-Host "میوه اول: $($fruits[0])"
Write-Host "میوه آخر: $($fruits[-1])"
Write-Host "تعداد میوه‌ها: $($fruits.Count)"

# افزودن به آرایه (در واقع آرایه جدید ساخته می‌شود چون آرایه‌ها fixed-size هستند)
$fruits += "Orange"
Write-Host "بعد از افزودن: $fruits"

# پیمایش آرایه
foreach ($fruit in $fruits) {
    Write-Host "میوه: $fruit"
}

# آرایه اعداد و عملیات روی آن
$numbers = 1..10
$sum = ($numbers | Measure-Object -Sum).Sum
Write-Host "مجموع ۱ تا ۱۰: $sum"

# تمرین: آرایه‌ای از ۵ نمره بسازید و میانگین آن را با Measure-Object محاسبه کنید.
