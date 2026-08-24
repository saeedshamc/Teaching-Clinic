# =====================================================================
# جلسه 01 - آشنایی با PowerShell و کنسول آن
# هدف: فهمیدن تفاوت اصلی PowerShell با Batch/CMD
# =====================================================================
# برخلاف Batch که همه چیز متن ساده است، PowerShell با Object کار می‌کند.
# هر دستور (Cmdlet) یک شیء ساختاریافته برمی‌گرداند، نه فقط متن خام.

Write-Host "سلام دنیا از PowerShell!" -ForegroundColor Green
Write-Host "این خط با رنگ متفاوت نمایش داده می‌شود" -ForegroundColor Yellow

# نمایش نسخه PowerShell نصب‌شده
$PSVersionTable.PSVersion

# اجرای این فایل: powershell -ExecutionPolicy Bypass -File .\example1.ps1

# تمرین: با Write-Host نام و سن خودتان را با دو رنگ متفاوت چاپ کنید.
