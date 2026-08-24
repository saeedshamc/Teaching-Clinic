# =====================================================================
# جلسه 28 - ساخت و پرتاب خطای سفارشی
# =====================================================================
function Test-Age {
    param([int]$Age)

    if ($Age -lt 0) {
        throw "سن نمی‌تواند منفی باشد: $Age"
    }
    if ($Age -gt 150) {
        throw "سن غیرمنطقی است: $Age"
    }
    Write-Host "سن معتبر است: $Age"
}

try {
    Test-Age -Age 25
    Test-Age -Age -5
}
catch {
    Write-Host "خطا گرفته شد: $($_.Exception.Message)" -ForegroundColor Red
}

# ساخت آبجکت خطای سفارشی با جزئیات بیشتر
try {
    $errorRecord = New-Object System.Management.Automation.ErrorRecord(
        (New-Object System.Exception("خطای سفارشی من")),
        "CustomErrorId",
        [System.Management.Automation.ErrorCategory]::InvalidData,
        $null
    )
    throw $errorRecord
}
catch {
    Write-Host "دسته‌بندی خطا: $($_.CategoryInfo.Category)"
}

# تمرین: تابعی بسازید که ایمیل ورودی را بررسی کند و اگر فرمتش غلط بود throw کند.
