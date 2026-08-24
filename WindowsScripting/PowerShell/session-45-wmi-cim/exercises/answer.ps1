# پاسخ تمرین - جلسه 45: Get-CimInstance برای اطلاعات سخت‌افزار/سیستم
# این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

Get-CimInstance Win32_NetworkAdapter | Where-Object { $_.NetEnabled -eq $true } |
    Select-Object Name, MACAddress, Speed
