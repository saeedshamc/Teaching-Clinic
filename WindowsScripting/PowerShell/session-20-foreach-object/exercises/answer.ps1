# پاسخ تمرین - جلسه 20: تفاوت foreach و ForEach-Object
# این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

1..10 | ForEach-Object {
    if ($_ % 2 -eq 0) {
        Write-Host "$_ زوج است"
    } else {
        Write-Host "$_ فرد است"
    }
}
