# =====================================================================
# جلسه 42 - برنامه‌نویسی شیءگرا با Class
# =====================================================================
class Person {
    [string]$Name
    [int]$Age

    Person([string]$name, [int]$age) {
        $this.Name = $name
        $this.Age = $age
    }

    [string] Greet() {
        return "سلام، من $($this.Name) هستم و $($this.Age) سال دارم."
    }

    [bool] IsAdult() {
        return $this.Age -ge 18
    }
}

$p1 = [Person]::new("Saeed", 25)
Write-Host $p1.Greet()
Write-Host "بزرگسال است؟ $($p1.IsAdult())"

# ارث‌بری ساده
class Student : Person {
    [string]$School

    Student([string]$name, [int]$age, [string]$school) : base($name, $age) {
        $this.School = $school
    }
}

$s1 = [Student]::new("Ali", 20, "دانشگاه تهران")
Write-Host "$($s1.Greet()) در $($s1.School) درس می‌خواند."

# تمرین: کلاس Car با ویژگی‌های Brand و Speed و متد Accelerate بسازید.
