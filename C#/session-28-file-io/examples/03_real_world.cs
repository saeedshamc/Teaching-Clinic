// ============================================================
// جلسه ۲۸ — File I/O: log file واقعی
// ============================================================

SimpleLogger.Info("برنامه شروع شد");
SimpleLogger.Info("کاربر وارد شد: admin");
SimpleLogger.Error("اتصال به DB ناموفق");
SimpleLogger.Info("برنامه پایان یافت");

Console.WriteLine("=== لاگ امروز ===");
foreach (var line in SimpleLogger.ReadTodayLogs())
    Console.WriteLine(line);

Directory.Delete("logs", recursive: true);

public static class SimpleLogger
{
    private static readonly string LogDir = "logs";

    public static void Info(string message) => Write("INFO", message);
    public static void Error(string message) => Write("ERROR", message);

    private static void Write(string level, string message)
    {
        Directory.CreateDirectory(LogDir);
        string logFile = Path.Combine(LogDir, $"{DateTime.Today:yyyy-MM-dd}.log");
        string entry = $"[{DateTime.Now:yyyy-MM-dd HH:mm:ss}] [{level}] {message}";
        File.AppendAllText(logFile, entry + Environment.NewLine);
    }

    public static string[] ReadTodayLogs()
    {
        string logFile = Path.Combine(LogDir, $"{DateTime.Today:yyyy-MM-dd}.log");
        return File.Exists(logFile) ? File.ReadAllLines(logFile) : Array.Empty<string>();
    }
}
