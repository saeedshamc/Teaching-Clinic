# پاسخ تمرین - جلسه 24: Import-Csv و Export-Csv
# این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

$employees = @(
    [PSCustomObject]@{Name="Ali"; Salary=15000000}
    [PSCustomObject]@{Name="Sara"; Salary=22000000}
    [PSCustomObject]@{Name="Reza"; Salary=18000000}
    [PSCustomObject]@{Name="Mina"; Salary=30000000}
    [PSCustomObject]@{Name="Hasan"; Salary=12000000}
)
$employees | Export-Csv "employees.csv" -NoTypeInformation -Encoding UTF8
$highEarners = Import-Csv "employees.csv" | Where-Object { [int]$_.Salary -gt 18000000 }
$highEarners | Format-Table -AutoSize
Remove-Item "employees.csv"
