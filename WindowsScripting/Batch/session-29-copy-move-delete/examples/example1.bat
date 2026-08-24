:: =====================================================================
:: جلسه 29 - عملیات فایل: copy, move, del
:: =====================================================================
@echo off
echo test content > original.txt

copy original.txt copy_of_original.txt
echo فایل کپی شد.

move copy_of_original.txt moved_file.txt
echo فایل جابه‌جا/تغییر نام یافت.

del original.txt
del moved_file.txt
echo فایل‌ها حذف شدند.

:: /Y پرسش تایید را حذف می‌کند: copy /Y source dest
:: برای حذف چند فایل با الگو: del *.tmp

:: تمرین: ۳ فایل txt بسازید، همه را در یک پوشه جدید کپی کنید، سپس فایل‌های اصلی را حذف کنید.
