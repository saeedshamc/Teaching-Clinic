// تمرین ۴–۵

class Employee
{
    public string Name { get; set; } = "";
    public string Department { get; set; } = "";
    public double Salary { get; set; }

    public Employee() { }
    public Employee(string name) : this(name, "عمومی", 0) { }
    public Employee(string name, string dept, double salary)
    {
        Name = name; Department = dept; Salary = salary;
    }

    public double AnnualSalary => Salary * 12;
    public string Level => Salary switch
    {
        < 10_000_000 => "Junior",
        < 20_000_000 => "Mid",
        _ => "Senior"
    };
}

class TimeSpan24
{
    private int _hours, _minutes;

    public int Hours
    {
        get => _hours;
        set => _hours = Math.Clamp(value, 0, 23);
    }

    public int Minutes
    {
        get => _minutes;
        set => _minutes = Math.Clamp(value, 0, 59);
    }

    public int TotalMinutes
    {
        get => _hours * 60 + _minutes;
        set { _hours = value / 60 % 24; _minutes = value % 60; }
    }

    public string Display => $"{_hours:D2}:{_minutes:D2}";

    public TimeSpan24() { }
    public TimeSpan24(int hours, int minutes) { Hours = hours; Minutes = minutes; }
    public TimeSpan24(int totalMinutes) => TotalMinutes = totalMinutes;
}

var emp = new Employee("مریم", "فناوری", 15_000_000);
Console.WriteLine($"{emp.Name}: {emp.Level} | سالانه: {emp.AnnualSalary:N0}");

var time = new TimeSpan24(14, 45);
Console.WriteLine($"زمان: {time.Display} ({time.TotalMinutes} دقیقه)");
time.TotalMinutes = 90;
Console.WriteLine($"بعد از 90 دقیقه: {time.Display}");
