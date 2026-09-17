// ============================================================
// جلسه ۳۴ — Logging: Structured Logging
// ============================================================

var logger = new StructuredLogger();

logger.Log("Information", "User {UserId} logged in from {IpAddress}",
    new Dictionary<string, object> { ["UserId"] = 42, ["IpAddress"] = "192.168.1.1" });

logger.Log("Information", "Order {OrderId} processed in {ElapsedMs}ms",
    new Dictionary<string, object> { ["OrderId"] = 1001, ["ElapsedMs"] = 235 });

logger.Log("Error", "Payment failed for Order {OrderId}: {Reason}",
    new Dictionary<string, object> { ["OrderId"] = 1002, ["Reason"] = "Insufficient funds" });

Console.WriteLine("\n=== Query: OrderId=1001 ===");
foreach (var entry in logger.QueryByProperty("OrderId", 1001))
    Console.WriteLine($"  {entry.Message}");

public record LogEntry(DateTime Timestamp, string Level, string Message, Dictionary<string, object> Properties);

public class StructuredLogger
{
    private readonly List<LogEntry> _entries = new();

    public void Log(string level, string template, Dictionary<string, object> props)
    {
        string message = template;
        foreach (var (key, value) in props)
            message = message.Replace($"{{{key}}}", value.ToString());

        _entries.Add(new LogEntry(DateTime.Now, level, message, props));
        Console.WriteLine($"[{level}] {message}");
    }

    public IEnumerable<LogEntry> QueryByProperty(string key, object value) =>
        _entries.Where(e => e.Properties.TryGetValue(key, out var v) && v.Equals(value));
}
