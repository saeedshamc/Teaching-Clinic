# =====================================================================
# جلسه 08 - Switch به‌جای چندین If/ElseIf
# =====================================================================
$day = Read-Host "نام روز هفته را به انگلیسی وارد کنید (مثلا Monday)"

switch ($day) {
    "Monday"    { Write-Host "شروع هفته کاری" }
    "Friday"    { Write-Host "آخر هفته نزدیک است!" }
    "Saturday"  { Write-Host "تعطیل است" }
    "Sunday"    { Write-Host "تعطیل است" }
    default     { Write-Host "روز عادی هفته" }
}

# Switch با پشتیبانی از الگو (wildcard)
$fileName = "report.pdf"
switch -Wildcard ($fileName) {
    "*.pdf" { Write-Host "فایل PDF است" }
    "*.txt" { Write-Host "فایل متنی است" }
    "*.jpg" { Write-Host "فایل تصویری است" }
}

# تمرین: با Switch یک ماشین‌حساب ساده بسازید که بر اساس عملگر ورودی (+,-,*,/) عملیات را انجام دهد.
