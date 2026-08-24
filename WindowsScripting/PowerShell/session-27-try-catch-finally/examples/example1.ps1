# =====================================================================
# جلسه 27 - Try/Catch/Finally
# =====================================================================
function Get-SafeDivision {
    param([double]$a, [double]$b)
    try {
        if ($b -eq 0) {
            throw "تقسیم بر صفر مجاز نیست!"
        }
        return $a / $b
    }
    catch {
        Write-Warning "خطا رخ داد: $($_.Exception.Message)"
        return $null
    }
    finally {
        Write-Host "محاسبه تمام شد (finally همیشه اجرا می‌شود)"
    }
}

$result1 = Get-SafeDivision -a 10 -b 2
Write-Host "نتیجه ۱: $result1"

$result2 = Get-SafeDivision -a 10 -b 0
Write-Host "نتیجه ۲: $result2"

# گرفتن نوع خاصی از خطا
try {
    Get-Content "nonexistent_file.txt" -ErrorAction Stop
}
catch [System.Management.Automation.ItemNotFoundException] {
    Write-Warning "فایل پیدا نشد (خطای خاص گرفته شد)"
}
catch {
    Write-Warning "خطای دیگری رخ داد: $($_.Exception.Message)"
}

# تمرین: تابعی بسازید که سعی کند یک رشته را به عدد تبدیل کند و در صورت خطا پیام مناسب دهد.
