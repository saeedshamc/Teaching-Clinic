:: پاسخ تمرین - جلسه 21: SHIFT برای خواندن تعداد نامشخصی از آرگومان‌ها
:: این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

@echo off
setlocal enabledelayedexpansion
set count=0
:loop
if "%1"=="" goto done
set /a count+=1
shift
goto loop
:done
echo تعداد آرگومان‌های ورودی: %count%
