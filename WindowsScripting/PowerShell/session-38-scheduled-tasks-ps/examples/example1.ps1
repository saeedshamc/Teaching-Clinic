# =====================================================================
# جلسه 38 - ساخت Scheduled Task با PowerShell
# =====================================================================
$action = New-ScheduledTaskAction -Execute "notepad.exe"
$trigger = New-ScheduledTaskTrigger -Daily -At "09:00AM"
$settings = New-ScheduledTaskSettingsSet -StartWhenAvailable

Write-Host "ثبت Task جدید (نیاز به دسترسی Administrator):"
try {
    Register-ScheduledTask -TaskName "MyPSTask" -Action $action -Trigger $trigger -Settings $settings -Force
    Write-Host "Task با موفقیت ثبت شد."

    Write-Host "نمایش اطلاعات Task:"
    Get-ScheduledTask -TaskName "MyPSTask" | Select-Object TaskName, State

    Write-Host "حذف Task (پاکسازی):"
    Unregister-ScheduledTask -TaskName "MyPSTask" -Confirm:$false
}
catch {
    Write-Warning "این عملیات نیاز به دسترسی Administrator دارد: $($_.Exception.Message)"
}

# تمرین: یک Trigger هفتگی (Weekly) بسازید که هر دوشنبه ساعت ۸ صبح اجرا شود.
