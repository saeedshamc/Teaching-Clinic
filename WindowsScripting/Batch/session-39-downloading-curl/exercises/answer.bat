:: پاسخ تمرین - جلسه 39: دانلود فایل از اینترنت
:: این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

@echo off
curl -s -o downloaded.txt https://raw.githubusercontent.com/octocat/Hello-World/master/README
if exist downloaded.txt (
    for /f %%c in ('find /c /v "" ^< downloaded.txt') do set linecount=%%c
    echo تعداد خطوط فایل دانلود شده: %linecount%
    del downloaded.txt
) else (
    echo دانلود ناموفق بود.
)
