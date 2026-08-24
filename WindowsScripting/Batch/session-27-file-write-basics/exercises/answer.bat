:: پاسخ تمرین - جلسه 27: نوشتن در فایل با > و >>
:: این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

@echo off
if exist rows.txt del rows.txt
for /L %%i in (1,1,5) do (
    echo ردیف شماره %%i >> rows.txt
)
type rows.txt
