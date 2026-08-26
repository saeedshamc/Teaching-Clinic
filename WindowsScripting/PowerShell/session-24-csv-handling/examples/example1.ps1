# =====================================================================
# جلسه 24 - Import-Csv و Export-Csv
# =====================================================================
$students = @(
    [PSCustomObject]@{ Name = "Ali";  Score = 18 }
    [PSCustomObject]@{ Name = "Sara"; Score = 15 }
    [PSCustomObject]@{ Name = "Reza"; Score = 20 }
)

$students | Export-Csv -Path "students.csv" -NoTypeInformation -Encoding UTF8

Write-Host "محتوای فایل CSV:"
Get-Content "students.csv"

Write-Host ""
Write-Host "خواندن و فیلتر کردن:"
$passed = Import-Csv "students.csv" | Where-Object { [int]$_.Score -ge 16 }
$passed | Format-Table -AutoSize

Remove-Item "students.csv"

# تمرین: لیستی از ۵ کارمند (نام، حقوق) بسازید، در CSV ذخیره کنید و کسانی با حقوق بالای یک عدد مشخص را استخراج کنید.
