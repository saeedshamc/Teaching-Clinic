# =====================================================================
# جلسه 14 - توابع پیشرفته با اعتبارسنجی پارامتر
# =====================================================================
function New-User {
    [CmdletBinding()]
    param(
        [Parameter(Mandatory=$true)]
        [ValidateNotNullOrEmpty()]
        [string]$Username,

        [Parameter(Mandatory=$false)]
        [ValidateRange(1,120)]
        [int]$Age = 18,

        [ValidateSet("Admin", "User", "Guest")]
        [string]$Role = "User"
    )

    Write-Host "کاربر ساخته شد: $Username, سن: $Age, نقش: $Role"
}

New-User -Username "saeed" -Age 25 -Role "Admin"

# این خط خطا می‌دهد چون Age خارج از محدوده مجاز است (عمدا کامنت شده):
# New-User -Username "test" -Age 200

# ValidateSet فقط اجازه می‌دهد مقادیر مشخص‌شده وارد شوند - جلوی خطای تایپی را می‌گیرد.

# تمرین: تابعی با ValidateSet برای وضعیت سفارش ("Pending","Shipped","Delivered") بسازید.
