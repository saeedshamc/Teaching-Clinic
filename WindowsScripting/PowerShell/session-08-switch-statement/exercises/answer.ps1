# پاسخ تمرین - جلسه 8: Switch به‌جای چندین If/ElseIf
# این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

$a = 10
$b = 5
$op = Read-Host "عملگر را وارد کنید (+,-,*,/)"

switch ($op) {
    "+" { Write-Host ($a + $b) }
    "-" { Write-Host ($a - $b) }
    "*" { Write-Host ($a * $b) }
    "/" { Write-Host ($a / $b) }
    default { Write-Host "عملگر نامعتبر" }
}
