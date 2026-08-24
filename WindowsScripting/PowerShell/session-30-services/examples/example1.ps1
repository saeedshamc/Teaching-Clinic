# =====================================================================
# جلسه 30 - Get-Service و مدیریت سرویس‌ها
# =====================================================================
Get-Service | Where-Object { $_.Status -eq "Running" } | Select-Object -First 5 Name, DisplayName

Write-Host ""
Write-Host "بررسی یک سرویس خاص (مثلا Spooler برای پرینتر):"
$spooler = Get-Service -Name "Spooler" -ErrorAction SilentlyContinue
if ($spooler) {
    Write-Host "وضعیت سرویس Spooler: $($spooler.Status)"
}

Write-Host ""
Write-Host "شمارش سرویس‌ها بر اساس وضعیت:"
Get-Service | Group-Object Status | Select-Object Name, Count

# برای متوقف/شروع سرویس نیاز به دسترسی Administrator است:
# Stop-Service -Name "ServiceName"
# Start-Service -Name "ServiceName"

# تمرین: تعداد سرویس‌های متوقف‌شده (Stopped) روی سیستم خود را بشمارید.
