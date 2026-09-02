// ============================================================
// جلسه ۱۷ — چند Interface
// فایل: 02_multiple_interfaces.cs
// ============================================================

interface IDrawable { void Draw(); }
interface IResizable { void Resize(double factor); }
interface ISaveable { void Save(string path); }

class GraphicElement : IDrawable, IResizable, ISaveable
{
    public string Name { get; set; } = "";
    public double Width { get; set; }
    public double Height { get; set; }

    public void Draw() =>
        Console.WriteLine($"رسم «{Name}» — {Width}x{Height}");

    public void Resize(double factor)
    {
        Width *= factor;
        Height *= factor;
        Console.WriteLine($"  تغییر اندازه ×{factor} → {Width}x{Height}");
    }

    public void Save(string path) =>
        Console.WriteLine($"  ذخیره در {path}");
}

interface IFlyable { void Fly(); }
interface ISwimmable { void Swim(); }

class Duck : IFlyable, ISwimmable
{
    public string Name { get; set; } = "";
    public void Fly() => Console.WriteLine($"{Name} پرواز می‌کند 🦆");
    public void Swim() => Console.WriteLine($"{Name} شنا می‌کند 🦆");
}

var shape = new GraphicElement { Name = "بنر", Width = 100, Height = 50 };
shape.Draw();
shape.Resize(1.5);
shape.Save("banner.png");

Duck duck = new Duck { Name = "دونالد" };
IFlyable flyer = duck;
ISwimmable swimmer = duck;
flyer.Fly();
swimmer.Swim();
