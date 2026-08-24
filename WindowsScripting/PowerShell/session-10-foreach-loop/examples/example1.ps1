# =====================================================================
# جلسه 10 - حلقه ForEach روی مجموعه‌ها
# =====================================================================
$colors = @("Red", "Green", "Blue")

foreach ($color in $colors) {
    Write-Host "رنگ: $color"
}

# ForEach روی محدوده اعداد
foreach ($num in 1..5) {
    Write-Host "عدد: $num"
}

# ForEach روی HashTable
$scores = @{ Ali = 18; Sara = 20; Reza = 15 }
foreach ($entry in $scores.GetEnumerator()) {
    Write-Host "$($entry.Key): $($entry.Value)"
}

# تمرین: آرایه‌ای از ۵ اسم بسازید و با ForEach هرکدام را با پیام خوش‌آمدگویی چاپ کنید.
