# پاسخ تمرین - جلسه 34: Invoke-WebRequest برای دانلود و درخواست HTTP
# این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

try {
    $response = Invoke-WebRequest -Uri "https://github.com" -UseBasicParsing
    if ($response.StatusCode -eq 200) {
        Write-Host "سایت در دسترس است (200 OK)"
    }
}
catch {
    Write-Host "سایت در دسترس نیست: $($_.Exception.Message)"
}
