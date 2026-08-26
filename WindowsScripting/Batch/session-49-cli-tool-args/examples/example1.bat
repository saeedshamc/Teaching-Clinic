:: =====================================================================
:: جلسه 49 - ابزار خط فرمان با پردازش آرگومان‌های نام‌دار
:: =====================================================================
@echo off
setlocal enabledelayedexpansion
:: اجرا: tool.bat --name Saeed --age 25

:parse_args
if "%~1"=="" goto after_parse
if /i "%~1"=="--name" (
    set "arg_name=%~2"
    shift & shift
    goto parse_args
)
if /i "%~1"=="--age" (
    set "arg_age=%~2"
    shift & shift
    goto parse_args
)
echo پارامتر ناشناخته: %~1
shift
goto parse_args

:after_parse
if not defined arg_name (
    echo خطا: پارامتر --name اجباری است
    exit /b 1
)
echo نام: %arg_name%
if defined arg_age echo سن: %arg_age%

:: این الگو دقیقا شبیه ابزارهای CLI واقعی (مثل git, npm) پارامتر می‌گیرد.

:: تمرین: به این ابزار پارامتر --city هم اضافه کنید و آن را اختیاری کنید.
