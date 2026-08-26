# =====================================================================
# جلسه 13 - پارامترهای تایپ‌دار و مقدار پیش‌فرض
# =====================================================================
function Get-FullName {
    param(
        [string]$FirstName,
        [string]$LastName = "ناشناس"
    )
    return "$FirstName $LastName"
}

Write-Host (Get-FullName -FirstName "Saeed" -LastName "Ahmadi")
Write-Host (Get-FullName -FirstName "Ali")   # استفاده از مقدار پیش‌فرض LastName

function Test-Range {
    param(
        [int]$Number,
        [int]$Min = 0,
        [int]$Max = 100
    )
    return ($Number -ge $Min -and $Number -le $Max)
}

Write-Host "آیا 50 بین 0 و 100 است؟ $(Test-Range -Number 50)"

# تمرین: تابعی بسازید که سه پارامتر (طول، عرض، واحد اندازه‌گیری با مقدار پیش‌فرض "متر") بگیرد و مساحت را چاپ کند.
