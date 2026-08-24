# =====================================================================
# جلسه 03 - متغیرها و انواع داده در PowerShell
# =====================================================================
$name = "Saeed"
$age = 25
$height = 1.78
$isStudent = $true

Write-Host "نام: $name, سن: $age, قد: $height, دانشجو: $isStudent"

# بررسی نوع داده هر متغیر
Write-Host "نوع name: $($name.GetType().Name)"
Write-Host "نوع age: $($age.GetType().Name)"
Write-Host "نوع isStudent: $($isStudent.GetType().Name)"

# تبدیل نوع داده (Type Casting)
$strNumber = "42"
$realNumber = [int]$strNumber
Write-Host "عدد تبدیل‌شده: $realNumber, نوع: $($realNumber.GetType().Name)"

# تمرین: سه متغیر از نوع رشته، عدد صحیح و عدد اعشاری بسازید و نوع هرکدام را چاپ کنید.
