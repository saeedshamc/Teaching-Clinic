# پاسخ تمرین - جلسه 32: Registry با Get-ItemProperty
# این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

New-Item -Path "HKCU:\Software\ExerciseTest2" -Force | Out-Null
Set-ItemProperty -Path "HKCU:\Software\ExerciseTest2" -Name "Name" -Value "TestApp"
Set-ItemProperty -Path "HKCU:\Software\ExerciseTest2" -Name "CreatedDate" -Value (Get-Date).ToString()

$vals = Get-ItemProperty -Path "HKCU:\Software\ExerciseTest2"
Write-Host "Name: $($vals.Name), CreatedDate: $($vals.CreatedDate)"

Remove-Item -Path "HKCU:\Software\ExerciseTest2" -Recurse -Force
