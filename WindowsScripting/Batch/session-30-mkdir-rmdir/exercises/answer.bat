:: پاسخ تمرین - جلسه 30: ساخت و حذف پوشه‌ها
:: این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

@echo off
mkdir MyProject
mkdir MyProject\src
mkdir MyProject\docs
mkdir MyProject\test
mkdir MyProject\assets
echo ساختار پروژه ساخته شد:
dir MyProject /b
rmdir /s /q MyProject
