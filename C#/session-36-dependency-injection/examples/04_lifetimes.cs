// ============================================================
// جلسه ۳۶ — DI: Service Lifetimes
// ============================================================

var singleton = new CounterService();
Console.WriteLine("=== Singleton ===");
Console.WriteLine($"Id={singleton.Id}, Count={++singleton.Count}");
Console.WriteLine($"Id={singleton.Id}, Count={++singleton.Count}");

Console.WriteLine("\n=== Transient ===");
for (int i = 0; i < 3; i++)
{
    var t = new CounterService();
    t.Increment();
    Console.WriteLine($"  Id={t.Id}, Count={t.Count}");
}

public class CounterService
{
    public int Id { get; } = Random.Shared.Next(1000, 9999);
    public int Count { get; private set; }
    public void Increment() => Count++;
}
