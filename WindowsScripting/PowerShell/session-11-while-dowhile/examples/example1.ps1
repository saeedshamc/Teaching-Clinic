# =====================================================================
# جلسه 11 - While و Do-While
# =====================================================================
$counter = 1
while ($counter -le 5) {
    Write-Host "While: $counter"
    $counter++
}

Write-Host ""
$num = 0
do {
    Write-Host "Do-While: $num"
    $num++
} while ($num -lt 3)

Write-Host ""
Write-Host "شبیه‌سازی منوی تعاملی با While:"
$choice = ""
$attempts = 0
while ($choice -ne "exit" -and $attempts -lt 3) {
    $choice = Read-Host "دستور را وارد کنید (یا exit برای خروج)"
    $attempts++
}

# تمرین: با Do-While از کاربر عدد بگیرید تا زمانی که عدد مثبت وارد کند.
