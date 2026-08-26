# =====================================================================
# جلسه 16 - Pipeline: قلب تپنده PowerShell
# =====================================================================
# Pipeline (|) خروجی یک دستور را به‌عنوان ورودی به دستور بعدی می‌فرستد

Get-Process | Select-Object -First 5 Name, CPU

Write-Host ""
1..10 | ForEach-Object { Write-Host "عدد در pipeline: $_" }

Write-Host ""
"apple", "banana", "cherry" | ForEach-Object { Write-Host $_.ToUpper() }

# می‌توان چند دستور را زنجیر کرد:
Get-Process | Sort-Object CPU -Descending | Select-Object -First 3 Name, CPU

# تمرین: با Pipeline، اعداد ۱ تا ۲۰ را فیلتر کنید تا فقط مضرب‌های ۳ چاپ شوند (Where-Object).
