// ============================================================
// پاسخ نمونه — تمرین جلسه ۳۴
// ============================================================

public class AppLogger
{
    public void Info(string msg) => Write("INF", msg);
    public void Warn(string msg) => Write("WRN", msg);
    public void Error(string msg, Exception? ex = null)
    {
        Write("ERR", msg);
        if (ex != null) Write("ERR", ex.Message);
    }

    private void Write(string level, string msg) =>
        Console.WriteLine($"[{DateTime.Now:HH:mm:ss}] [{level}] {msg}");
}

var log = new AppLogger();

try
{
    log.Info("شروع عملیات");
    int result = int.Parse("abc"); // خطا
}
catch (Exception ex)
{
    log.Error("عملیات ناموفق", ex);
}
finally
{
    log.Info("پایان عملیات");
}

log.Warn("Retry attempt 2/3");
