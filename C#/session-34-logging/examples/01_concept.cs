// ============================================================
// جلسه ۳۴ — Logging: سطوح log
// ============================================================

Log(LogLevel.Information, "برنامه شروع شد");
Log(LogLevel.Debug, "بارگذاری تنظیمات...");
Log(LogLevel.Warning, "فایل config یافت نشد — از پیش‌فرض استفاده می‌شود");
Log(LogLevel.Error, "اتصال به DB ناموفق");
Log(LogLevel.Critical, "سیستم غیرقابل استفاده — shutdown");

Console.WriteLine("\nدر production فقط Warning+ log می‌شود.");

void Log(LogLevel level, string message)
{
    string prefix = level switch
    {
        LogLevel.Trace => "[TRC]",
        LogLevel.Debug => "[DBG]",
        LogLevel.Information => "[INF]",
        LogLevel.Warning => "[WRN]",
        LogLevel.Error => "[ERR]",
        LogLevel.Critical => "[CRT]",
        _ => "[???]"
    };
    Console.WriteLine($"{DateTime.Now:HH:mm:ss} {prefix} {message}");
}

enum LogLevel { Trace, Debug, Information, Warning, Error, Critical }
