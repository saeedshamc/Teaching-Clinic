# =====================================================================
# جلسه 50 - پروژه نهایی: داشبورد یکپارچه سیستم و امنیت
# این پروژه همه مهارت‌های ۴۹ جلسه قبل را ترکیب می‌کند
# =====================================================================
param([string]$ReportPath = "system_dashboard.html")

function Get-SystemSummary {
    $summary = [ordered]@{}

    $os = Get-CimInstance Win32_OperatingSystem
    $summary["سیستم عامل"] = $os.Caption
    $summary["حافظه آزاد (GB)"] = [math]::Round($os.FreePhysicalMemory/1MB, 2)

    $disk = Get-CimInstance Win32_LogicalDisk -Filter "DeviceID='C:'"
    $summary["فضای آزاد دیسک C (GB)"] = [math]::Round($disk.FreeSpace/1GB, 2)

    $topProcesses = Get-Process | Sort-Object CPU -Descending | Select-Object -First 5 -ExpandProperty Name
    $summary["پرمصرف‌ترین پروسه‌ها"] = ($topProcesses -join ", ")

    $summary["Execution Policy"] = (Get-ExecutionPolicy).ToString()

    try {
        $defender = Get-MpComputerStatus -ErrorAction Stop
        $summary["آنتی‌ویروس فعال"] = $defender.AntivirusEnabled
    } catch {
        $summary["آنتی‌ویروس فعال"] = "نامشخص (دسترسی کافی نیست)"
    }

    return $summary
}

function Export-DashboardHtml {
    param($data)
    $rows = ($data.GetEnumerator() | ForEach-Object {
        "<tr><td>$($_.Key)</td><td>$($_.Value)</td></tr>"
    }) -join "`n"

    $html = @"
<html dir='rtl'><head><meta charset='utf-8'>
<style>
body { font-family: Tahoma; background:#1e1e2e; color:#eee; padding:20px; }
table { width:100%; border-collapse:collapse; }
td { padding:10px; border-bottom:1px solid #444; }
h1 { color:#7ee787; }
</style></head><body>
<h1>داشبورد سیستم — $(Get-Date -Format 'yyyy-MM-dd HH:mm')</h1>
<table>$rows</table>
</body></html>
"@
    $html | Set-Content -Path $ReportPath -Encoding UTF8
    Write-Host "گزارش HTML ذخیره شد: $ReportPath"
}

$data = Get-SystemSummary
Export-DashboardHtml -data $data

Write-Host ""
Write-Host "خلاصه در کنسول:"
$data.GetEnumerator() | ForEach-Object { Write-Host "$($_.Key): $($_.Value)" }

# این پروژه از: CIM، پروسه‌ها، Execution Policy، Defender، توابع، HashTable مرتب،
# و تولید گزارش HTML (همه چیزهایی که در این دوره یاد گرفتید) استفاده می‌کند.

# تمرین نهایی: این اسکریپت را با Task Scheduler (جلسه ۳۸) تنظیم کنید تا هر روز خودکار اجرا شود.
