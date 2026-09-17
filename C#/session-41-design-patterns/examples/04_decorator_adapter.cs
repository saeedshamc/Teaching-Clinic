// ============================================================
// جلسه ۴۱ — Design Patterns
// فایل: 04_decorator_adapter.cs
// ============================================================

// --- Decorator ---
interface INotifier { void Send(string message); }

class EmailNotifier : INotifier
{
    public void Send(string message) => Console.WriteLine($"  📧 Email: {message}");
}

class SmsDecorator : INotifier
{
    private readonly INotifier _inner;
    public SmsDecorator(INotifier inner) => _inner = inner;

    public void Send(string message)
    {
        _inner.Send(message);
        Console.WriteLine($"  📱 SMS: {message}");
    }
}

class LoggingDecorator : INotifier
{
    private readonly INotifier _inner;
    public LoggingDecorator(INotifier inner) => _inner = inner;

    public void Send(string message)
    {
        Console.WriteLine($"  📝 Log: sending at {DateTime.Now:HH:mm:ss}");
        _inner.Send(message);
    }
}

Console.WriteLine("=== Decorator ===");
INotifier notifier = new LoggingDecorator(new SmsDecorator(new EmailNotifier()));
notifier.Send("سفارش شما ثبت شد");

// --- Adapter ---
interface ILogger { void Log(string message); }

class LegacyLogger
{
    public void WriteLog(string msg, int level)
        => Console.WriteLine($"  [Legacy L{level}] {msg}");
}

class LegacyLoggerAdapter : ILogger
{
    private readonly LegacyLogger _legacy;
    public LegacyLoggerAdapter(LegacyLogger legacy) => _legacy = legacy;
    public void Log(string message) => _legacy.WriteLog(message, 1);
}

Console.WriteLine("\n=== Adapter ===");
ILogger logger = new LegacyLoggerAdapter(new LegacyLogger());
logger.Log("سیستم راه‌اندازی شد");
