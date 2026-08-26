# =====================================================================
# جلسه 35 - Invoke-RestMethod برای کار با API
# =====================================================================
$repo = Invoke-RestMethod -Uri "https://api.github.com/repos/PowerShell/PowerShell"

Write-Host "نام رپو: $($repo.name)"
Write-Host "تعداد ستاره: $($repo.stargazers_count)"
Write-Host "زبان اصلی: $($repo.language)"

Write-Host ""
Write-Host "ذخیره نتیجه در فایل JSON:"
$repo | Select-Object name, stargazers_count, language | ConvertTo-Json | Set-Content "repo_info.json"
Get-Content "repo_info.json"
Remove-Item "repo_info.json"

# ارسال درخواست POST (مثال ساختاری - نیاز به API واقعی دارد)
# Invoke-RestMethod -Uri "https://api.example.com/data" -Method Post -Body $jsonBody -ContentType "application/json"

# تمرین: اطلاعات یک repo دلخواه گیت‌هاب را بگیرید و تعداد forkهایش را چاپ کنید.
