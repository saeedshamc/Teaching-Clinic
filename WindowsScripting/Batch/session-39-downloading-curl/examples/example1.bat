:: =====================================================================
:: جلسه 39 - دانلود فایل از اینترنت
:: =====================================================================
@echo off
echo دانلود یک فایل نمونه با curl (در ویندوز ۱۰/۱۱ پیش‌فرض نصب است):
curl -o downloaded.txt https://raw.githubusercontent.com/octocat/Hello-World/master/README

if exist downloaded.txt (
    echo دانلود موفق بود.
    type downloaded.txt
    del downloaded.txt
) else (
    echo دانلود ناموفق بود.
)

:: روش جایگزین با bitsadmin (قدیمی‌تر ولی همه‌جا موجود):
:: bitsadmin /transfer myDownload /download /priority normal "URL" "%cd%\file.txt"

:: تمرین: اسکریپتی بسازید که یک فایل متنی از اینترنت دانلود کند و تعداد خطوطش را با find /c چاپ کند.
