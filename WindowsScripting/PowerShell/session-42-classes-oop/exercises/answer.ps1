# پاسخ تمرین - جلسه 42: برنامه‌نویسی شیءگرا با Class
# این یکی از راه‌حل‌های ممکن است؛ راه‌حل‌های دیگر هم می‌توانند درست باشند.

class Car {
    [string]$Brand
    [int]$Speed = 0

    Car([string]$brand) { $this.Brand = $brand }

    [void] Accelerate([int]$amount) {
        $this.Speed += $amount
        Write-Host "$($this.Brand) اکنون با سرعت $($this.Speed) کیلومتر بر ساعت می‌رود."
    }
}

$myCar = [Car]::new("Toyota")
$myCar.Accelerate(30)
$myCar.Accelerate(20)
