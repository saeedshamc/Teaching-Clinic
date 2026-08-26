:: پاسخ تمرین - جلسه 24: محاسبه طول رشته (بدون تابع مستقیم؛ با ترفند)
:: این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

@echo off
setlocal enabledelayedexpansion
set /p str=یک رشته وارد کنید: 
set len=0
:count_loop
if not "!str:~%len%,1!"=="" (
    set /a len+=1
    goto count_loop
)
echo طول رشته وارد شده: %len%
