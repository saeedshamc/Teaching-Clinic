# =====================================================================
# جلسه 21 - متدهای رشته در PowerShell
# =====================================================================
$text = "Hello PowerShell World"

Write-Host "بزرگ: $($text.ToUpper())"
Write-Host "کوچک: $($text.ToLower())"
Write-Host "طول: $($text.Length)"
Write-Host "شامل PowerShell: $($text.Contains('PowerShell'))"
Write-Host "جایگزینی: $($text.Replace('World', 'Everyone'))"
Write-Host "برش: $($text.Substring(0,5))"
Write-Host "تقسیم به کلمات:"
$words = $text.Split(" ")
$words | ForEach-Object { Write-Host " - $_" }

Write-Host "الحاق: $("Hello" + " " + "World")"
Write-Host "تکرار: $("Ab" * 3)"

# تمرین: رشته "  PowerShell is Powerful  " را trim کنید و بررسی کنید با "PowerShell" شروع می‌شود یا نه.
