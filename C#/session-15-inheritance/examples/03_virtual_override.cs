// ============================================================
// جلسه ۱۵ — virtual و override
// فایل: 03_virtual_override.cs
// ============================================================

class Shape
{
    public string Color { get; set; } = "سفید";

    public virtual double GetArea() => 0;
    public virtual double GetPerimeter() => 0;
    public virtual string Describe() => $"شکل {Color}";
}

class Circle : Shape
{
    public double Radius { get; set; }

    public override double GetArea() => Math.PI * Radius * Radius;
    public override double GetPerimeter() => 2 * Math.PI * Radius;
    public override string Describe() => $"دایره {Color} — شعاع {Radius}";
}

class Rectangle : Shape
{
    public double Width { get; set; }
    public double Height { get; set; }

    public override double GetArea() => Width * Height;
    public override double GetPerimeter() => 2 * (Width + Height);
    public override string Describe() => $"مستطیل {Width}x{Height}";
}

Shape[] shapes =
{
    new Circle { Color = "قرمز", Radius = 5 },
    new Rectangle { Color = "آبی", Width = 4, Height = 6 },
    new Circle { Color = "سبز", Radius = 3 }
};

foreach (var shape in shapes)
{
    Console.WriteLine($"{shape.Describe()}");
    Console.WriteLine($"  مساحت: {shape.GetArea():F2} | محیط: {shape.GetPerimeter():F2}");
}
