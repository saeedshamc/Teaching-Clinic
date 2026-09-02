// ============================================================
// جلسه ۳۴ — Logging: logging در service
// ============================================================

var svc = new OrderService(new SimpleLogger());
svc.ProcessOrder(101, 250_000);
svc.ProcessOrder(102, -100);

public interface ILogger
{
    void LogInformation(string template, params object[] args);
    void LogError(Exception ex, string template, params object[] args);
}

public class SimpleLogger : ILogger
{
    public void LogInformation(string template, params object[] args) =>
        Console.WriteLine("[INF] " + string.Format(template.Replace("{OrderId}", "{0}").Replace("{Amount}", "{1}"), args));

    public void LogError(Exception ex, string template, params object[] args) =>
        Console.WriteLine("[ERR] " + string.Format(template.Replace("{OrderId}", "{0}"), args) + $" | {ex.Message}");
}

public class OrderService
{
    private readonly ILogger _logger;
    public OrderService(ILogger logger) => _logger = logger;

    public void ProcessOrder(int orderId, decimal amount)
    {
        _logger.LogInformation("Processing order {OrderId} amount {Amount}", orderId, amount);
        if (amount <= 0)
        {
            _logger.LogError(new ArgumentException("Invalid amount"), "Order {OrderId} failed validation", orderId);
            return;
        }
        _logger.LogInformation("Order {OrderId} completed successfully", orderId);
    }
}
