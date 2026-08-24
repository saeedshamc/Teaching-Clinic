:: پاسخ تمرین - جلسه 31: کپی پیشرفته با XCOPY و ROBOCOPY
:: این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

@echo off
mkdir Src 2>nul
echo test > Src\a.txt

echo === تست با /E ===
xcopy Src Dest1 /E /I /Y >nul
dir Dest1 /b

echo === تست با /MIR ===
robocopy Src Dest2 /MIR >nul
dir Dest2 /b

echo تفاوت: /MIR مقصد را کاملا آینه مبدا می‌کند (فایل‌های اضافه در مقصد حذف می‌شوند)، /E فقط اضافه می‌کند.
rmdir /s /q Src Dest1 Dest2
