:: پاسخ تمرین - جلسه 35: خواندن و نوشتن Registry با دستور REG
:: این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

@echo off
reg add "HKCU\Software\ExerciseTest" /v TestValue /t REG_SZ /d "HelloRegistry" /f
echo مقدار نوشته شد. در حال خواندن:
reg query "HKCU\Software\ExerciseTest" /v TestValue
echo حذف کلید تست:
reg delete "HKCU\Software\ExerciseTest" /f
