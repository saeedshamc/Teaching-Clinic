// ============================================================
// جلسه ۱۷ — Dependency Injection ساده
// فایل: 03_dependency_injection.cs
// ============================================================

interface ILogger
{
    void Log(string message);
    void LogError(string message);
}

class ConsoleLogger : ILogger
{
    public void Log(string message) => Console.WriteLine($"[INFO] {DateTime.Now:HH:mm:ss} {message}");
    public void LogError(string message) => Console.WriteLine($"[ERROR] {DateTime.Now:HH:mm:ss} {message}");
}

class MemoryLogger : ILogger
{
    private readonly List<string> _logs = new();
    public void Log(string message) => _logs.Add($"[INFO] {message}");
    public void LogError(string message) => _logs.Add($"[ERROR] {message}");
    public IReadOnlyList<string> GetLogs() => _logs;
}

class OrderService
{
    private readonly ILogger _logger;

    // DI از طریق constructor
    public OrderService(ILogger logger) => _logger = logger;

    public void PlaceOrder(string product, double price)
    {
        _logger.Log($"سفارش «{product}» به مبلغ {price:N0} ثبت شد");
        if (price <= 0)
            _logger.LogError("مبلغ نامعتبر!");
    }
}

// استفاده با logger مختلف — بدون تغییر OrderService
Console.WriteLine("=== Console Logger ===");
var consoleService = new OrderService(new ConsoleLogger());
consoleService.PlaceOrder("لپ‌تاپ", 25_000_000);

Console.WriteLine("\n=== Memory Logger ===");
var memLogger = new MemoryLogger();
var memService = new OrderService(memLogger);
memService.PlaceOrder("ماوس", 450_000);
memService.PlaceOrder("خطا", -100);
foreach (var log in memLogger.GetLogs())
    Console.WriteLine(log);
