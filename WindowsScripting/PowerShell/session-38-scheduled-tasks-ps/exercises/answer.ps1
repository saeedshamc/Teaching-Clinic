# پاسخ تمرین - جلسه 38: ساخت Scheduled Task با PowerShell
# این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

$action = New-ScheduledTaskAction -Execute "notepad.exe"
$trigger = New-ScheduledTaskTrigger -Weekly -DaysOfWeek Monday -At "08:00AM"
try {
    Register-ScheduledTask -TaskName "WeeklyMondayTask" -Action $action -Trigger $trigger -Force
    Write-Host "Task هفتگی (دوشنبه ساعت 8) ثبت شد."
    Unregister-ScheduledTask -TaskName "WeeklyMondayTask" -Confirm:$false
}
catch {
    Write-Warning "نیاز به دسترسی Administrator دارد."
}
