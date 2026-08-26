# =====================================================================
# جلسه 26 - کار با XML در PowerShell
# =====================================================================
[xml]$xmlData = @"
<Library>
    <Book>
        <Title>PowerShell Basics</Title>
        <Author>John Doe</Author>
    </Book>
    <Book>
        <Title>Advanced Scripting</Title>
        <Author>Jane Smith</Author>
    </Book>
</Library>
"@

$xmlData.Library.Book | ForEach-Object {
    Write-Host "کتاب: $($_.Title) - نویسنده: $($_.Author)"
}

# ساخت XML جدید و ذخیره در فایل
$xmlData.Save("library.xml")
Write-Host ""
Write-Host "فایل XML ذخیره شد."

$reloaded = [xml](Get-Content "library.xml")
Write-Host "تعداد کتاب‌های بارگذاری‌شده: $($reloaded.Library.Book.Count)"

Remove-Item "library.xml"

# تمرین: یک XML برای ۳ محصول فروشگاه (نام، قیمت) بسازید و قیمت‌ها را جمع بزنید.
