# =====================================================================
# جلسه 34 - Invoke-WebRequest برای دانلود و درخواست HTTP
# =====================================================================
Write-Host "دانلود یک فایل:"
Invoke-WebRequest -Uri "https://raw.githubusercontent.com/octocat/Hello-World/master/README" -OutFile "downloaded.txt"

if (Test-Path "downloaded.txt") {
    Write-Host "دانلود موفق بود."
    Get-Content "downloaded.txt"
    Remove-Item "downloaded.txt"
}

Write-Host ""
Write-Host "بررسی کد وضعیت HTTP یک سایت:"
$response = Invoke-WebRequest -Uri "https://github.com" -UseBasicParsing
Write-Host "کد وضعیت: $($response.StatusCode)"

# تمرین: با Invoke-WebRequest بررسی کنید آیا سایت مورد نظر شما در دسترس است (کد ۲۰۰).
