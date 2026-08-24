# =====================================================================
# جلسه 02 - ساختار نام‌گذاری دستورات PowerShell
# =====================================================================
# هر Cmdlet از الگوی Verb-Noun پیروی می‌کند: Get-Process, Set-Location, New-Item
# این یکنواختی باعث می‌شود حدس زدن نام دستورات ساده باشد.

Write-Host "افعال رایج: Get, Set, New, Remove, Start, Stop, Test, Invoke"

# پیدا کردن همه دستورات مرتبط با یک اسم خاص
Get-Command -Noun Process | Select-Object -First 5 Name

Write-Host ""
Write-Host "پیدا کردن همه دستورات با یک فعل خاص:"
Get-Command -Verb Get -Noun Service* | Select-Object -First 3 Name

# راهنمای هر دستور:
# Get-Help Get-Process -Examples

# تمرین: با Get-Command تمام دستورات Verb=New را پیدا کنید و ۵ تای اول را چاپ کنید.
