:: پاسخ تمرین - جلسه 29: عملیات فایل: copy, move, del
:: این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

@echo off
echo test1 > f1.txt
echo test2 > f2.txt
echo test3 > f3.txt
mkdir NewFolder 2>nul
copy f1.txt NewFolder\ >nul
copy f2.txt NewFolder\ >nul
copy f3.txt NewFolder\ >nul
del f1.txt f2.txt f3.txt
echo فایل‌ها کپی و اصلی‌ها حذف شدند.
dir NewFolder /b
rmdir /s /q NewFolder
