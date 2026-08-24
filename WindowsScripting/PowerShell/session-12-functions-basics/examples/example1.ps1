# =====================================================================
# جلسه 12 - تعریف تابع ساده
# =====================================================================
function Get-Greeting {
    Write-Host "سلام از تابع Get-Greeting!"
}

function Get-Sum($a, $b) {
    return $a + $b
}

Get-Greeting
$result = Get-Sum 5 7
Write-Host "مجموع: $result"

function Show-Info {
    Write-Host "این یک تابع بدون پارامتر است"
}
Show-Info

# تمرین: تابعی به نام Get-Square بسازید که یک عدد بگیرد و مربعش را برگرداند.
