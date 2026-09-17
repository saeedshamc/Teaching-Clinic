// ============================================================
// جلسه ۲۴ — Query Syntax
// فایل: 04_query_syntax.cs
// ============================================================

class Employee
{
    public string Name { get; set; } = "";
    public string Department { get; set; } = "";
    public double Salary { get; set; }
    public int YearsOfService { get; set; }
}

var employees = new List<Employee>
{
    new() { Name = "علی", Department = "فناوری", Salary = 18_000_000, YearsOfService = 3 },
    new() { Name = "مریم", Department = "فناوری", Salary = 22_000_000, YearsOfService = 5 },
    new() { Name = "رضا", Department = "فروش", Salary = 15_000_000, YearsOfService = 2 },
    new() { Name = "سارا", Department = "فناوری", Salary = 20_000_000, YearsOfService = 4 },
    new() { Name = "امیر", Department = "فروش", Salary = 12_000_000, YearsOfService = 1 },
    new() { Name = "لیلا", Department = "HR", Salary = 14_000_000, YearsOfService = 6 }
};

// Query Syntax — معادل Method Syntax
var highEarners = from e in employees
                  where e.Salary > 15_000_000
                  orderby e.Salary descending
                  select new { e.Name, e.Department, e.Salary };

Console.WriteLine("=== حقوق بالا (Query Syntax) ===");
foreach (var e in highEarners)
    Console.WriteLine($"  {e.Name} ({e.Department}): {e.Salary:N0}");

// GroupBy با Query Syntax
var deptStats = from e in employees
                group e by e.Department into g
                select new
                {
                    Department = g.Key,
                    Count = g.Count(),
                    AvgSalary = g.Average(e => e.Salary),
                    TotalSalary = g.Sum(e => e.Salary)
                };

Console.WriteLine("\n=== آمار دپارتمان ===");
foreach (var d in deptStats.OrderByDescending(d => d.AvgSalary))
    Console.WriteLine($"  {d.Department}: {d.Count} نفر, میانگین={d.AvgSalary:N0}, جمع={d.TotalSalary:N0}");

// Join (معرفی) — Method Syntax رایج‌تر
var projects = new[]
{
    new { Name = "پروژه A", Dept = "فناوری" },
    new { Name = "پروژه B", Dept = "فروش" }
};

var deptProjects = from p in projects
                   join e in employees on p.Dept equals e.Department
                   select new { p.Name, Employee = e.Name, p.Dept };

Console.WriteLine("\n=== پروژه‌ها و کارمندان ===");
foreach (var dp in deptProjects)
    Console.WriteLine($"  {dp.Name} — {dp.Employee} ({dp.Dept})");

// let — متغیر میانی
var senior = from e in employees
             let annual = e.Salary * 12
             where e.YearsOfService >= 3
             orderby annual descending
             select new { e.Name, Annual = annual, e.YearsOfService };

Console.WriteLine("\n=== ارشد (3+ سال) ===");
foreach (var s in senior)
    Console.WriteLine($"  {s.Name}: سالانه={s.Annual:N0}, سابقه={s.YearsOfService} سال");
