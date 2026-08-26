# =====================================================================
# جلسه 29 - Get-Process و Stop-Process
# =====================================================================
Get-Process | Sort-Object CPU -Descending | Select-Object -First 5 Name, CPU, Id

Write-Host ""
Write-Host "بررسی وجود یک پروسه خاص:"
$notepad = Get-Process -Name "notepad" -ErrorAction SilentlyContinue
if ($notepad) {
    Write-Host "نوت‌پد در حال اجراست با PID: $($notepad.Id)"
} else {
    Write-Host "نوت‌پد در حال اجرا نیست"
}

Write-Host ""
Write-Host "اجرای یک پروسه جدید:"
Start-Process notepad
Start-Sleep -Seconds 2

Write-Host "بستن پروسه:"
Get-Process -Name "notepad" -ErrorAction SilentlyContinue | Stop-Process -Force

# تمرین: اسکریپتی بسازید که ۵ پروسه‌ای که بیشترین حافظه (WorkingSet) را مصرف می‌کنند نمایش دهد.
