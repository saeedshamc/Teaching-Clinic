// ============================================================
// جلسه ۱۴ — Expression-bodied Properties و Methods
// فایل: 04_expression_bodies.cs
// ============================================================

class Person
{
    public string FirstName { get; set; } = "";
    public string LastName { get; set; } = "";
    public int BirthYear { get; set; }

    // Expression-bodied property
    public string FullName => $"{FirstName} {LastName}";
    public int Age => DateTime.Now.Year - BirthYear;
    public bool IsSenior => Age >= 60;

    // Expression-bodied method
    public string Greet() => $"سلام، من {FullName} هستم.";
    public string GreetFormal() => $"با احترام، {FullName} ({Age} ساله)";
}

class Temperature
{
    private double _celsius;

    public double Celsius
    {
        get => _celsius;
        set => _celsius = Math.Clamp(value, -273.15, 1000);
    }

    public double Fahrenheit
    {
        get => _celsius * 9 / 5 + 32;
        set => Celsius = (value - 32) * 5 / 9;
    }

    public string Description => _celsius switch
    {
        < 0 => "یخ‌زده",
        < 15 => "سرد",
        < 25 => "معتدل",
        < 35 => "گرم",
        _ => "داغ"
    };
}

var p = new Person { FirstName = "علی", LastName = "احمدی", BirthYear = 1990 };
Console.WriteLine(p.Greet());
Console.WriteLine($"سالمند؟ {p.IsSenior}");

var temp = new Temperature { Celsius = 22 };
Console.WriteLine($"\n{temp.Celsius}°C = {temp.Fahrenheit:F1}°F — {temp.Description}");
