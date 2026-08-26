# پاسخ تمرین - جلسه 41: کار با ماژول‌ها (Modules)
# این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

function Get-Double($n) { return $n * 2 }
function Get-Triple($n) { return $n * 3 }
Write-Host "دو برابر 5: $(Get-Double 5)"
Write-Host "سه برابر 5: $(Get-Triple 5)"
# این دو تابع را می‌توان در فایل MyTools.psm1 قرار داد و با Import-Module .\MyTools.psm1 بارگذاری کرد.
