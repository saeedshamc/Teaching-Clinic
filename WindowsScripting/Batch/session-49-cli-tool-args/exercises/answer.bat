:: پاسخ تمرین - جلسه 49: ابزار خط فرمان با پردازش آرگومان‌های نام‌دار
:: این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

@echo off
setlocal enabledelayedexpansion
:parse_args
if "%~1"=="" goto after_parse
if /i "%~1"=="--name" ( set "arg_name=%~2" & shift & shift & goto parse_args )
if /i "%~1"=="--city" ( set "arg_city=%~2" & shift & shift & goto parse_args )
shift
goto parse_args

:after_parse
echo نام: %arg_name%
if defined arg_city (echo شهر: %arg_city%) else (echo شهر وارد نشده - اختیاری بود)
:: اجرا: tool.bat --name Saeed --city Tehran
