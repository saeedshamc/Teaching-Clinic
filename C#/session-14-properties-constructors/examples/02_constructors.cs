// ============================================================
// جلسه ۱۴ — Constructors: Overloading و زنجیره
// فایل: 02_constructors.cs
// ============================================================

class Employee
{
    public string Name { get; set; } = "";
    public string Department { get; set; } = "";
    public double Salary { get; set; }

    // Constructor پیش‌فرض — بعد از تعریف constructor دیگر، باید صریح بنویسیم
    public Employee() { }

    // ساده — فقط نام
    public Employee(string name) : this(name, "عمومی", 0)
    {
        Console.WriteLine($"  → Employee('{name}') زنجیره شد");
    }

    // کامل
    public Employee(string name, string department, double salary)
    {
        Name = name;
        Department = department;
        Salary = salary;
    }

    public override string ToString() =>
        $"{Name} | {Department} | {Salary:N0} تومان";
}

class Rectangle
{
    public double Width { get; set; }
    public double Height { get; set; }

    public Rectangle() : this(1, 1) { }
    public Rectangle(double side) : this(side, side) { }
    public Rectangle(double width, double height)
    {
        Width = width;
        Height = height;
    }

    public double Area => Width * Height;
}

var e1 = new Employee("علی");
var e2 = new Employee("مریم", "فناوری", 18_000_000);
Console.WriteLine(e1);
Console.WriteLine(e2);

var sq = new Rectangle(5);
var rect = new Rectangle(4, 6);
Console.WriteLine($"\nمربع 5x5: مساحت={sq.Area}");
Console.WriteLine($"مستطیل 4x6: مساحت={rect.Area}");
