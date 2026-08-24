# =====================================================================
# جلسه 45 - Get-CimInstance برای اطلاعات سخت‌افزار/سیستم
# =====================================================================
Write-Host "اطلاعات سیستم‌عامل:"
Get-CimInstance Win32_OperatingSystem | Select-Object Caption, Version, OSArchitecture

Write-Host ""
Write-Host "اطلاعات پردازنده:"
Get-CimInstance Win32_Processor | Select-Object Name, NumberOfCores

Write-Host ""
Write-Host "اطلاعات دیسک‌ها:"
Get-CimInstance Win32_LogicalDisk | Select-Object DeviceID,
    @{Name="FreeSpace(GB)"; Expression={[math]::Round($_.FreeSpace/1GB,2)}},
    @{Name="Size(GB)"; Expression={[math]::Round($_.Size/1GB,2)}}

Write-Host ""
Write-Host "اطلاعات RAM:"
$ram = Get-CimInstance Win32_ComputerSystem
Write-Host "کل RAM: $([math]::Round($ram.TotalPhysicalMemory/1GB,2)) GB"

# تمرین: با Get-CimInstance اطلاعات کارت شبکه (Win32_NetworkAdapter) را نمایش دهید.
