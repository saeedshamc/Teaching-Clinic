// ============================================================
// جلسه ۳۶ — DI: مفهوم پایه
// ============================================================

IOrderRepository repo = new InMemoryOrderRepository();
var service = new GoodOrderService(repo);
service.PlaceOrder(1001);
service.PlaceOrder(1002);
Console.WriteLine("\nDI = dependencies از بیرون inject می‌شوند");

public interface IOrderRepository { void Save(int orderId); }

public class InMemoryOrderRepository : IOrderRepository
{
    private readonly List<int> _orders = new();
    public void Save(int orderId)
    {
        _orders.Add(orderId);
        Console.WriteLine($"  [Repo] Order {orderId} saved. Total: {_orders.Count}");
    }
}

public class GoodOrderService
{
    private readonly IOrderRepository _repo;
    public GoodOrderService(IOrderRepository repo) => _repo = repo;

    public void PlaceOrder(int id)
    {
        Console.WriteLine($"[Service] Placing order {id}");
        _repo.Save(id);
    }
}
