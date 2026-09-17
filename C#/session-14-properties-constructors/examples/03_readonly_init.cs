// ============================================================
// جلسه ۱۴ — readonly، const و init
// فایل: 03_readonly_init.cs
// ============================================================

class Circle
{
    // const — مقدار compile-time، static implicit
    public const double Pi = 3.14159265;

    // readonly — مقدار runtime، per-instance
    public readonly string Id;
    public readonly DateTime CreatedAt;

    public double Radius { get; set; }

    public Circle(double radius)
    {
        Id = Guid.NewGuid().ToString()[..8];
        CreatedAt = DateTime.Now;
        Radius = radius;
    }

    public double Area => Pi * Radius * Radius;
}

// init — فقط هنگام object initializer
class Order
{
    public int OrderId { get; init; }
    public string Customer { get; init; } = "";
    public DateTime OrderDate { get; init; } = DateTime.Now;
}

var c = new Circle(5);
Console.WriteLine($"دایره {c.Id}: شعاع={c.Radius}, مساحت={c.Area:F2}");
Console.WriteLine($"ساخته شده: {c.CreatedAt:HH:mm:ss}");
// c.Id = "new";  // ❌ readonly

var order = new Order { OrderId = 1001, Customer = "علی" };
Console.WriteLine($"\nسفارش #{order.OrderId} — {order.Customer}");
// order.OrderId = 2;  // ❌ init
