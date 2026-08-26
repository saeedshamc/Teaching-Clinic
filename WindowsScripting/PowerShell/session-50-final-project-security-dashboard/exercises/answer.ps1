# پاسخ تمرین - جلسه 50: پروژه نهایی: داشبورد یکپارچه سیستم و امنیت
# این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

$action = New-ScheduledTaskAction -Execute "powershell.exe" -Argument "-File C:\Scripts\dashboard.ps1"
$trigger = New-ScheduledTaskTrigger -Daily -At "07:00AM"
try {
    Register-ScheduledTask -TaskName "DailySecurityDashboard" -Action $action -Trigger $trigger -Force
    Write-Host "داشبورد امنیتی برای اجرای روزانه ساعت 7 صبح زمان‌بندی شد."
    Unregister-ScheduledTask -TaskName "DailySecurityDashboard" -Confirm:$false
}
catch {
    Write-Warning "نیاز به دسترسی Administrator دارد."
}
