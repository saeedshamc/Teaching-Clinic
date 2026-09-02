// ============================================================
// تمرین ۲ جلسه ۴۱ — Observer + Decorator (پاسخ نمونه)
// ============================================================

class OrderService
{
    public event Action<int>? OrderPlaced;
    public void Place(int orderId)
    {
        Console.WriteLine($"  ✓ Order #{orderId} placed");
        OrderPlaced?.Invoke(orderId);
    }
}

interface INotifier { void Send(string msg); }

class ConsoleNotifier : INotifier
{
    public void Send(string msg) => Console.WriteLine($"    Notify: {msg}");
}

class AuditDecorator : INotifier
{
    private readonly INotifier _inner;
    public AuditDecorator(INotifier inner) => _inner = inner;

    public void Send(string msg)
    {
        Console.WriteLine($"    [Audit] before send");
        _inner.Send(msg);
        Console.WriteLine($"    [Audit] after send");
    }
}

var orders = new OrderService();
orders.OrderPlaced += id => Console.WriteLine($"    [Log] OrderPlaced event: {id}");
orders.OrderPlaced += id => Console.WriteLine($"    [Inventory] Update stock for order {id}");

INotifier notifier = new AuditDecorator(new ConsoleNotifier());
orders.OrderPlaced += id => notifier.Send($"سفارش {id} ثبت شد");

orders.Place(101);
