# =====================================================================
# جلسه 43 - استفاده مستقیم از کلاس‌های .NET
# =====================================================================
# PowerShell روی .NET ساخته شده، پس می‌توان مستقیم از کلاس‌های آن استفاده کرد

$sb = [System.Text.StringBuilder]::new()
$sb.Append("Hello ") | Out-Null
$sb.Append("World") | Out-Null
Write-Host "StringBuilder: $($sb.ToString())"

Write-Host ""
$list = [System.Collections.Generic.List[int]]::new()
$list.Add(1)
$list.Add(2)
$list.Add(3)
Write-Host "List<int>: $($list -join ', ')"

Write-Host ""
$guid = [System.Guid]::NewGuid()
Write-Host "GUID تولیدشده: $guid"

Write-Host ""
$math = [System.Math]::Pow(2, 10)
Write-Host "2 به توان 10: $math"

# تمرین: با [System.Math] جذر عدد ۱۴۴ و مقدار PI را چاپ کنید.
