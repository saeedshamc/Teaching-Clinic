# =====================================================================
# جلسه 20 - تفاوت foreach و ForEach-Object
# =====================================================================
# foreach یک ساختار زبانی است (سریع‌تر، همه داده باید در حافظه باشد)
foreach ($i in 1..5) { Write-Host "foreach: $i" }

# ForEach-Object یک Cmdlet است که در pipeline کار می‌کند (حافظه کمتر مصرف می‌کند)
1..5 | ForEach-Object { Write-Host "ForEach-Object: $_" }

Write-Host ""
Write-Host "استفاده عملی: تغییر نام گروهی فایل‌ها (شبیه‌سازی)"
$files = @("a.txt", "b.txt", "c.txt")
$files | ForEach-Object -Begin { Write-Host "شروع پردازش..." } `
                          -Process { Write-Host "در حال پردازش: $_" } `
                          -End { Write-Host "پردازش تمام شد." }

# تمرین: با ForEach-Object، برای هر عدد از ۱ تا ۱۰ بررسی کنید زوج است یا فرد و پیام مناسب چاپ کنید.
