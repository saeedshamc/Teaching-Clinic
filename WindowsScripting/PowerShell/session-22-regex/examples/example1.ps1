# =====================================================================
# جلسه 22 - Regular Expressions در PowerShell
# =====================================================================
$email = "saeed@example.com"

if ($email -match '^[\w.-]+@[\w.-]+\.\w+$') {
    Write-Host "ایمیل معتبر است"
} else {
    Write-Host "ایمیل نامعتبر است"
}

$text = "شماره تماس من 09123456789 است"
if ($text -match '\d{11}') {
    Write-Host "شماره پیدا شد: $($matches[0])"
}

# جایگزینی با regex
$masked = $text -replace '\d{11}', '***********'
Write-Host "متن ماسک‌شده: $masked"

# استخراج همه تطابق‌ها
$logLine = "Error: 404, Error: 500, Error: 200"
$allMatches = [regex]::Matches($logLine, '\d{3}')
$allMatches | ForEach-Object { Write-Host "کد پیدا شده: $($_.Value)" }

# تمرین: با regex بررسی کنید آیا یک رشته فقط شامل حروف انگلیسی و فاصله است.
