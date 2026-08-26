# =====================================================================
# جلسه 23 - Get-Content و Set-Content
# =====================================================================
"خط اول" | Set-Content -Path "test.txt"
"خط دوم" | Add-Content -Path "test.txt"
"خط سوم" | Add-Content -Path "test.txt"

$content = Get-Content -Path "test.txt"
Write-Host "محتوای فایل:"
$content | ForEach-Object { Write-Host " > $_" }

Write-Host "تعداد خطوط: $($content.Count)"

# خواندن کل فایل به‌صورت یک رشته واحد
$wholeText = Get-Content -Path "test.txt" -Raw
Write-Host "طول کل متن: $($wholeText.Length) کاراکتر"

Remove-Item "test.txt"

# تمرین: فایلی با ۵ عدد تصادفی (هر خط یک عدد) بسازید و مجموع آن‌ها را با Get-Content محاسبه کنید.
