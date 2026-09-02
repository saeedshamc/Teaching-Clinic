// ============================================================
// جلسه ۴۲ — SOLID: Liskov Substitution Principle
// ============================================================

// ❌ Bad — Square نقض رفتار Rectangle
class Rectangle
{
    public virtual int Width { get; set; }
    public virtual int Height { get; set; }
    public int Area => Width * Height;
}

class Square : Rectangle
{
    public override int Width
    {
        get => base.Width;
        set { base.Width = value; base.Height = value; }
    }
    public override int Height
    {
        get => base.Height;
        set { base.Width = value; base.Height = value; }
    }
}

void PrintArea(Rectangle r)
{
    r.Width = 5;
    r.Height = 4;
    Console.WriteLine($"  Width={r.Width}, Height={r.Height}, Area={r.Area} (انتظار: 20)");
}

Console.WriteLine("=== ❌ LSP Violation ===");
PrintArea(new Square()); // Area=16 نه 20!

// ✅ Good — abstraction جدا
interface IShape { int Area { get; } }

class GoodRectangle : IShape
{
    public int Width { get; set; }
    public int Height { get; set; }
    public int Area => Width * Height;
}

class GoodSquare : IShape
{
    public int Side { get; set; }
    public int Area => Side * Side;
}

Console.WriteLine("\n=== ✅ LSP Compliant ===");
IShape rect = new GoodRectangle { Width = 5, Height = 4 };
IShape sq = new GoodSquare { Side = 5 };
Console.WriteLine($"  Rectangle Area: {rect.Area}");
Console.WriteLine($"  Square Area: {sq.Area}");

// هر IShape بدون surprise جایگزین می‌شود
void PrintShapeArea(IShape shape) => Console.WriteLine($"  Shape area: {shape.Area}");
PrintShapeArea(rect);
PrintShapeArea(sq);
