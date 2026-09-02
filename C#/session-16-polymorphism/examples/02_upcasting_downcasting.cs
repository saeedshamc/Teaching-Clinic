// ============================================================
// جلسه ۱۶ — Upcasting و Downcasting
// فایل: 02_upcasting_downcasting.cs
// ============================================================

class Employee
{
    public string Name { get; set; } = "";
    public virtual double GetSalary() => 0;
}

class Developer : Employee
{
    public string Language { get; set; } = "";
    public override double GetSalary() => 18_000_000;
    public void WriteCode() => Console.WriteLine($"{Name} کد {Language} می‌نویسد.");
}

class Designer : Employee
{
    public string Tool { get; set; } = "";
    public override double GetSalary() => 15_000_000;
}

Employee emp = new Developer { Name = "علی", Language = "C#" };
Console.WriteLine($"حقوق: {emp.GetSalary():N0}");  // Polymorphism

// Downcasting با is
if (emp is Developer dev)
{
    dev.WriteCode();
    Console.WriteLine($"زبان: {dev.Language}");
}

// Downcasting با as
Designer? designer = emp as Designer;
Console.WriteLine($"Designer? {designer != null}");

// GetType
Console.WriteLine($"نوع runtime: {emp.GetType().Name}");

Employee[] team =
{
    new Developer { Name = "علی", Language = "C#" },
    new Designer { Name = "مریم", Tool = "Figma" },
    new Developer { Name = "رضا", Language = "Python" }
};

foreach (var member in team)
{
    Console.Write($"\n{member.Name}: ");
    if (member is Developer d)
        Console.WriteLine($"Dev — {d.Language}");
    else if (member is Designer des)
        Console.WriteLine($"Designer — {des.Tool}");
}
