# پاسخ تمرین - جلسه 27: Try/Catch/Finally
# این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

function ConvertTo-SafeNumber {
    param([string]$Value)
    try {
        return [int]$Value
    }
    catch {
        Write-Warning "'$Value' یک عدد معتبر نیست."
        return $null
    }
}
ConvertTo-SafeNumber -Value "123"
ConvertTo-SafeNumber -Value "abc"
