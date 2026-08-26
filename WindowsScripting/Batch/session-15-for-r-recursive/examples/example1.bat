:: =====================================================================
:: جلسه 15 - پیمایش بازگشتی (زیرپوشه‌ها) با FOR /R
:: =====================================================================
@echo off
mkdir TestArea\Sub1 2>nul
mkdir TestArea\Sub2 2>nul
echo test> TestArea\a.txt
echo test> TestArea\Sub1\b.txt
echo test> TestArea\Sub2\c.txt

echo همه فایل‌های txt در تمام زیرپوشه‌ها:
for /R TestArea %%f in (*.txt) do (
    echo پیدا شد: %%f
)

rmdir /s /q TestArea

:: تمرین: با FOR /R تعداد کل فایل‌های .txt در یک پوشه و همه زیرپوشه‌هایش را بشمارید.
