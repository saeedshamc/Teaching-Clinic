:: پاسخ تمرین - جلسه 13: جداسازی ستون‌ها با tokens و delims
:: این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

@echo off
set fruits=apple;banana;cherry
for /f "tokens=1,2,3 delims=;" %%a in ("%fruits%") do (
    echo میوه 1: %%a
    echo میوه 2: %%b
    echo میوه 3: %%c
)
