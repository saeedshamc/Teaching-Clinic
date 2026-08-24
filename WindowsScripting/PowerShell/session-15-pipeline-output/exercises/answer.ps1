# پاسخ تمرین - جلسه 15: نحوه خروجی دادن توابع در PowerShell
# این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

function Get-Primes {
    param([int]$Max)
    foreach ($n in 2..$Max) {
        $isPrime = $true
        for ($i = 2; $i -lt $n; $i++) {
            if ($n % $i -eq 0) { $isPrime = $false; break }
        }
        if ($isPrime) { $n }
    }
}
$primes = Get-Primes -Max 50
Write-Host "اعداد اول تا 50: $primes"
