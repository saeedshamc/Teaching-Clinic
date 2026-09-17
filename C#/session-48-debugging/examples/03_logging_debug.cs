// ============================================================
// جلسه ۴۸ — Structured Logging
// ============================================================

enum LogLevel { Debug, Information, Warning, Error }

class Logger
{
    private readonly LogLevel _minLevel;
    public Logger(LogLevel minLevel) => _minLevel = minLevel;

    public void Log(LogLevel level, string template, params object[] args)
    {
        if (level < _minLevel) return;
        var msg = string.Format(template, args);
        var prefix = level switch
        {
            LogLevel.Debug => "DBG",
            LogLevel.Information => "INF",
            LogLevel.Warning => "WRN",
            LogLevel.Error => "ERR",
            _ => "???"
        };
        Console.WriteLine($"  [{prefix}] {DateTime.Now:HH:mm:ss.fff} {msg}");
    }

    public void LogDebug(string t, params object[] a) => Log(LogLevel.Debug, t, a);
    public void LogInfo(string t, params object[] a) => Log(LogLevel.Information, t, a);
    public void LogWarning(string t, params object[] a) => Log(LogLevel.Warning, t, a);
    public void LogError(Exception ex, string t, params object[] a)
        => Log(LogLevel.Error, $"{string.Format(t, a)} — {ex.Message}");
}

class OrderService
{
    private readonly Logger _logger;
    public OrderService(Logger logger) => _logger = logger;

    public void ProcessOrder(int orderId, decimal total)
    {
        _logger.LogInfo("Processing order {OrderId} with total {Total:N0}", orderId, total);

        if (total <= 0)
        {
            _logger.LogWarning("Invalid total {Total} for order {OrderId}", total, orderId);
            return;
        }

        try
        {
            if (total > 100_000_000)
                throw new InvalidOperationException("Amount exceeds limit");
            _logger.LogInfo("Order {OrderId} completed successfully", orderId);
        }
        catch (Exception ex)
        {
            _logger.LogError(ex, "Failed to process order {OrderId}", orderId);
        }
    }
}

var logger = new Logger(LogLevel.Debug);
var service = new OrderService(logger);

Console.WriteLine("=== Structured Logging ===");
service.ProcessOrder(101, 500_000);
service.ProcessOrder(102, -100);
service.ProcessOrder(103, 200_000_000);
