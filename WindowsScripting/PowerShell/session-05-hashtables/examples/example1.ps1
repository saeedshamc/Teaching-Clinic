# =====================================================================
# جلسه 05 - HashTable (دیکشنری کلید-مقدار)
# =====================================================================
$person = @{
    Name = "Ali"
    Age  = 30
    City = "Tehran"
}

Write-Host "نام: $($person.Name)"
Write-Host "سن: $($person['Age'])"

# افزودن یک کلید جدید
$person["Job"] = "Developer"
Write-Host "شغل: $($person.Job)"

# پیمایش تمام کلیدها و مقادیر
foreach ($key in $person.Keys) {
    Write-Host "$key -> $($person[$key])"
}

# حذف یک کلید
$person.Remove("City")
Write-Host "بعد از حذف City، تعداد کلیدها: $($person.Count)"

# تمرین: HashTable برای یک محصول (نام، قیمت، موجودی) بسازید و قیمت را ۱۰٪ افزایش دهید.
