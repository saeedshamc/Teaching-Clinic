:: =====================================================================
:: جلسه 31 - کپی پیشرفته با XCOPY و ROBOCOPY
:: =====================================================================
@echo off
mkdir SourceFolder 2>nul
echo test > SourceFolder\file1.txt
mkdir SourceFolder\Sub 2>nul
echo test > SourceFolder\Sub\file2.txt

echo === XCOPY (با زیرپوشه‌ها) ===
xcopy SourceFolder DestXcopy /E /I /Y

echo.
echo === ROBOCOPY (ابزار مدرن‌تر و قوی‌تر مایکروسافت) ===
robocopy SourceFolder DestRobo /E

rmdir /s /q SourceFolder DestXcopy DestRobo

:: /E یعنی همه زیرپوشه‌ها حتی خالی‌ها؛ /I یعنی مقصد پوشه است نه فایل؛ /Y یعنی بدون پرسش تایید.
:: ROBOCOPY امکانات بیشتری دارد: retry خودکار، لاگ‌گیری، mirror کردن با /MIR

:: تمرین: با ROBOCOPY یک پوشه را mirror کنید (یعنی مقصد دقیقا مثل مبدا شود، با پارامتر /MIR) و تفاوتش با /E را بررسی کنید.
