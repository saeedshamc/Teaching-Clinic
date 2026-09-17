// ============================================================
// جلسه ۱۸ — سلسله‌مراتب Shape
// فایل: 03_shape_hierarchy.cs
// ============================================================

abstract class Shape
{
    public string Color { get; set; } = "سفید";

    public abstract double GetArea();
    public abstract double GetPerimeter();

    public virtual void Draw()
    {
        Console.WriteLine($"رسم {GetType().Name} {Color}");
        Console.WriteLine($"  مساحت: {GetArea():F2} | محیط: {GetPerimeter():F2}");
    }

    public bool IsLargerThan(Shape other) => GetArea() > other.GetArea();
}

class Circle : Shape
{
    public double Radius { get; set; }
    public override double GetArea() => Math.PI * Radius * Radius;
    public override double GetPerimeter() => 2 * Math.PI * Radius;
}

class Rectangle : Shape
{
    public double Width { get; set; }
    public double Height { get; set; }
    public override double GetArea() => Width * Height;
    public override double GetPerimeter() => 2 * (Width + Height);
}

class Triangle : Shape
{
    public double Base { get; set; }
    public double Height { get; set; }
    public double SideA { get; set; }
    public double SideB { get; set; }
    public override double GetArea() => 0.5 * Base * Height;
    public override double GetPerimeter() => Base + SideA + SideB;
}

Shape[] shapes =
{
    new Circle { Color = "قرمز", Radius = 5 },
    new Rectangle { Color = "آبی", Width = 4, Height = 6 },
    new Triangle { Color = "سبز", Base = 3, Height = 4, SideA = 3, SideB = 4 }
};

foreach (var shape in shapes)
    shape.Draw();

var largest = shapes[0];
foreach (var s in shapes)
    if (s.IsLargerThan(largest)) largest = s;
Console.WriteLine($"\nبزرگترین: {largest.GetType().Name} — {largest.GetArea():F2}");
