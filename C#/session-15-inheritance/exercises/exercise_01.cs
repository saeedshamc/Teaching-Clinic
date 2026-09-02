// تمرین ۱–۳

class Vehicle
{
    public string Brand { get; set; } = "";
    public int Year { get; set; }
    public virtual void StartEngine() => Console.WriteLine($"{Brand} روشن شد.");
}

class Car : Vehicle
{
    public int Doors { get; set; }
    public override void StartEngine()
    {
        base.StartEngine();
        Console.WriteLine($"  {Doors} درب — آماده حرکت");
    }
}

class Motorcycle : Vehicle
{
    public bool HasSidecar { get; set; }
    public override void StartEngine() =>
        Console.WriteLine($"موتور {Brand} {(HasSidecar ? "با" : "بدون")} sidecar روشن شد.");
}

class Shape
{
    public virtual double GetArea() => 0;
}

class Circle : Shape
{
    public double Radius { get; set; }
    public override double GetArea() => Math.PI * Radius * Radius;
}

class Rectangle : Shape
{
    public double W, H;
    public override double GetArea() => W * H;
}

var car = new Car { Brand = "پژو", Year = 1400, Doors = 4 };
car.StartEngine();

Shape[] shapes = { new Circle { Radius = 3 }, new Rectangle { W = 4, H = 5 } };
foreach (var s in shapes) Console.WriteLine($"مساحت: {s.GetArea():F2}");
