// ============================================================
// جلسه ۱۵ — سلسله‌مراتب واقعی: کارمندان
// فایل: 04_hierarchy_realworld.cs
// ============================================================

class Employee
{
    public string Name { get; set; } = "";
    public string Department { get; set; } = "";

    public virtual double CalculateSalary() => 0;
    public virtual string GetRole() => "کارمند";
}

class FullTimeEmployee : Employee
{
    public double MonthlySalary { get; set; }

    public override double CalculateSalary() => MonthlySalary;
    public override string GetRole() => "تمام‌وقت";
}

class PartTimeEmployee : Employee
{
    public double HourlyRate { get; set; }
    public int HoursWorked { get; set; }

    public override double CalculateSalary() => HourlyRate * HoursWorked;
    public override string GetRole() => "پاره‌وقت";
}

class Manager : FullTimeEmployee
{
    public double Bonus { get; set; }
    protected int TeamSize;

    public Manager(int teamSize) => TeamSize = teamSize;

    public override double CalculateSalary() => MonthlySalary + Bonus;
    public override string GetRole() => $"مدیر ({TeamSize} نفر)";
}

Employee[] staff =
{
    new FullTimeEmployee { Name = "علی", Department = "فناوری", MonthlySalary = 15_000_000 },
    new PartTimeEmployee { Name = "مریم", Department = "پشتیبانی", HourlyRate = 200_000, HoursWorked = 80 },
    new Manager(5) { Name = "رضا", Department = "فناوری", MonthlySalary = 25_000_000, Bonus = 5_000_000 }
};

Console.WriteLine("=== فیش حقوقی ===");
foreach (var emp in staff)
    Console.WriteLine($"{emp.Name} ({emp.GetRole()}): {emp.CalculateSalary():N0} تومان");
