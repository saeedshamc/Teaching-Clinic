# =====================================================================
# جلسه 15 - نحوه خروجی دادن توابع در PowerShell
# =====================================================================
function Get-EvenNumbers {
    param([int]$Max)
    foreach ($i in 1..$Max) {
        if ($i % 2 -eq 0) {
            $i   # هر مقداری که "چاپ" شود (نه با Write-Host) وارد pipeline خروجی تابع می‌شود
        }
    }
}

$evens = Get-EvenNumbers -Max 10
Write-Host "اعداد زوج: $evens"

# تفاوت مهم: Write-Host فقط چاپ می‌کند (خروجی واقعی تابع نیست)؛
# مقداری که بدون دستور خاص در یک خط قرار می‌گیرد، بخشی از خروجی/pipeline تابع است.

function Get-Numbers {
    1
    2
    3
}
$result = Get-Numbers | Measure-Object -Sum
Write-Host "مجموع خروجی تابع: $($result.Sum)"

# تمرین: تابعی بسازید که اعداد اول بین ۱ تا ۵۰ را از طریق pipeline خروجی دهد.
