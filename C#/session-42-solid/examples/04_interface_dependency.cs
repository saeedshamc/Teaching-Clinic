// ============================================================
// جلسه ۴۲ — SOLID: Interface Segregation + Dependency Inversion
// ============================================================

// --- ISP ---
// ❌ Bad
interface IWorker { void Work(); void Eat(); void Sleep(); }

class RobotBad : IWorker
{
    public void Work() => Console.WriteLine("  Robot working");
    public void Eat() { /* Robot نمی‌خورد! */ }
    public void Sleep() { /* Robot نمی‌خوابد! */ }
}

// ✅ Good
interface IWorkable { void Work(); }
interface IFeedable { void Eat(); }

class Human : IWorkable, IFeedable
{
    public void Work() => Console.WriteLine("  Human working");
    public void Eat() => Console.WriteLine("  Human eating");
}

class Robot : IWorkable
{
    public void Work() => Console.WriteLine("  Robot working");
}

Console.WriteLine("=== ISP ===");
((IWorkable)new Human()).Work();
((IWorkable)new Robot()).Work();

// --- DIP ---
// ❌ Bad
class BadOrderService
{
    private readonly List<string> _orders = new();
    public void Place(string item) => _orders.Add(item); // tightly coupled to in-memory
}

// ✅ Good
interface IOrderRepository
{
    void Save(string item);
    IReadOnlyList<string> GetAll();
}

class InMemoryOrderRepository : IOrderRepository
{
    private readonly List<string> _orders = new();
    public void Save(string item) => _orders.Add(item);
    public IReadOnlyList<string> GetAll() => _orders;
}

class GoodOrderService
{
    private readonly IOrderRepository _repo;
    public GoodOrderService(IOrderRepository repo) => _repo = repo;

    public void Place(string item)
    {
        _repo.Save(item);
        Console.WriteLine($"  Order placed: {item}");
    }
}

Console.WriteLine("\n=== DIP ===");
var repo = new InMemoryOrderRepository();
var service = new GoodOrderService(repo);
service.Place("لپ‌تاپ");
service.Place("ماوس");
Console.WriteLine($"  Total orders: {repo.GetAll().Count}");
