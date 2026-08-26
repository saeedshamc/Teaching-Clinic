:: =====================================================================
:: جلسه 46 - زمان‌بندی خودکار اسکریپت با schtasks
:: =====================================================================
@echo off
echo ساخت یک Task زمان‌بندی‌شده (اجرای روزانه ساعت 9 صبح):
schtasks /create /tn "MyDailyBackup" /tr "notepad.exe" /sc daily /st 09:00 /f

echo.
echo نمایش لیست Task ساخته‌شده:
schtasks /query /tn "MyDailyBackup"

echo.
echo حذف Task (پاکسازی تست):
schtasks /delete /tn "MyDailyBackup" /f

:: /sc daily یعنی تکرار روزانه؛ گزینه‌های دیگر: hourly, weekly, monthly, once
:: /f یعنی بدون پرسش تایید (force)

:: تمرین: یک Task بسازید که هفته‌ای یک‌بار (روز دوشنبه) یک اسکریپت پشتیبان‌گیری را اجرا کند.
