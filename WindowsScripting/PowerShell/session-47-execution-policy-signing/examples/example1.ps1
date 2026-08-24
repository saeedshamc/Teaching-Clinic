# =====================================================================
# جلسه 47 - امنیت: Execution Policy و Code Signing
# =====================================================================
Write-Host "سطح فعلی Execution Policy:"
Get-ExecutionPolicy -List

Write-Host ""
Write-Host "توضیح سطوح:"
Write-Host "Restricted   -> هیچ اسکریپتی اجرا نمی‌شود"
Write-Host "AllSigned    -> فقط اسکریپت‌های امضاشده"
Write-Host "RemoteSigned -> محلی آزاد، دانلودی باید امضا شده باشد"
Write-Host "Unrestricted -> همه چیز اجرا می‌شود (توصیه نمی‌شود)"

# تنظیم برای کاربر فعلی:
# Set-ExecutionPolicy RemoteSigned -Scope CurrentUser

Write-Host ""
Write-Host "بررسی امضای دیجیتال یک اسکریپت:"
# Get-AuthenticodeSignature -FilePath "script.ps1"

Write-Host ""
Write-Host "⚠️ نکته امنیتی: Execution Policy یک لایه ایمنی است، نه دیوار امنیتی مطلق."
Write-Host "همیشه باید با آنتی‌ویروس و Script Block Logging (جلسات امنیتی) ترکیب شود."

# تمرین: Execution Policy فعلی کاربر خودتان را بررسی کنید و تفاوت آن با سطح Machine را بررسی کنید (-Scope).
