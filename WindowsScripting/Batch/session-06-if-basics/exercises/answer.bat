:: پاسخ تمرین - جلسه 6: شرط IF (مقایسه ساده)
:: این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

@echo off
set /p score=نمره را وارد کنید: 
if %score% geq 90 (
    echo حرف نمره: A
) else if %score% geq 80 (
    echo حرف نمره: B
) else if %score% geq 70 (
    echo حرف نمره: C
) else if %score% geq 60 (
    echo حرف نمره: D
) else (
    echo حرف نمره: F
)
