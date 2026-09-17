// ============================================================
// جلسه ۳۴ — Logging: Custom Logger
// ============================================================

var logger = new ConsoleLogger(LogLevel.Debug);
logger.Info("کاربر وارد شد: admin");
logger.Warn("حافظه cache 80% پر است");
logger.Error("خطا در پردازش سفارش", new InvalidOperationException("موجودی کافی نیست"));

public interface IAppLogger
{
    void Info(string message);
    void Warn(string message);
    void Error(string message, Exception? ex = null);
}

public class ConsoleLogger : IAppLogger
{
    private readonly LogLevel _minLevel;
    public ConsoleLogger(LogLevel minLevel = LogLevel.Information) => _minLevel = minLevel;

    public void Info(string message) => Write(LogLevel.Information, message);
    public void Warn(string message) => Write(LogLevel.Warning, message);
    public void Error(string message, Exception? ex = null)
    {
        Write(LogLevel.Error, message);
        if (ex is not null) Write(LogLevel.Error, $"  → {ex.GetType().Name}: {ex.Message}");
    }

    private void Write(LogLevel level, string msg)
    {
        if (level < _minLevel) return;
        Console.WriteLine($"[{level,-12}] {msg}");
    }
}

enum LogLevel { Debug, Information, Warning, Error }
