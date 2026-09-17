# جلسه ۳۴: Logging (ثبت وقایع) — ۱.۵ ساعت

## 🎯 اهداف یادگیری

- اهمیت logging در production
- سطوح log: Trace، Debug، Information، Warning، Error، Critical
- `ILogger` و `ILogger<T>` در Microsoft.Extensions.Logging
- structured logging
- Serilog (مقدمه)
- log در Console App و ASP.NET Core
- best practices: چه چیزی log کنیم و چه چیزی نه

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | چرا log؟ |
| 10–25 | log levels |
| 25–40 | ILogger |
| 40–55 | structured logging |
| 55–70 | Serilog |
| 70–85 | تمرین |
| 85–90 | جمع‌بندی + تکلیف |

---

## ۱. چرا Logging؟

- **Debug:** پیدا کردن باگ در production
- **Monitor:** سلامت سیستم
- **Audit:** چه کسی چه کاری انجام داد
- **Alert:** خطاهای critical

بدون log = blind in production.

---

## ۲. Log Levels

| Level | کاربرد | مثال |
|-------|--------|------|
| Trace | جزئیات extreme | loop iteration |
| Debug | توسعه | variable values |
| Information | flow عادی | "User logged in" |
| Warning | مشکل احتمالی | retry، deprecated |
| Error | خطا | exception |
| Critical | system failure | DB down |

---

## ۳. ILogger

```csharp
public class OrderService
{
    private readonly ILogger<OrderService> _logger;

    public OrderService(ILogger<OrderService> logger)
    {
        _logger = logger;
    }

    public void ProcessOrder(int orderId)
    {
        _logger.LogInformation("Processing order {OrderId}", orderId);
        try
        {
            // ...
        }
        catch (Exception ex)
        {
            _logger.LogError(ex, "Failed to process order {OrderId}", orderId);
        }
    }
}
```

---

## ۴. Structured Logging

```csharp
// ❌ string interpolation — structured نیست
_logger.LogInformation($"Order {orderId} processed");

// ✅ template + parameters
_logger.LogInformation("Order {OrderId} processed in {ElapsedMs}ms", orderId, 42);
```

مزیت: query در Seq، Elasticsearch، Application Insights.

---

## ۵. Setup در Console

```csharp
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Logging;

var services = new ServiceCollection();
services.AddLogging(builder =>
{
    builder.AddConsole();
    builder.SetMinimumLevel(LogLevel.Debug);
});

var provider = services.BuildServiceProvider();
var logger = provider.GetRequiredService<ILogger<Program>>();
logger.LogInformation("Hello logging!");
```

Package: `Microsoft.Extensions.Logging.Console`

---

## ۶. Serilog

```powershell
dotnet add package Serilog
dotnet add package Serilog.Sinks.Console
dotnet add package Serilog.Extensions.Logging
```

```csharp
Log.Logger = new LoggerConfiguration()
    .MinimumLevel.Debug()
    .WriteTo.Console()
    .CreateLogger();

Log.Information("User {UserId} logged in", userId);
Log.Error(ex, "Unhandled exception");
```

---

## ۷. ASP.NET Core (مقدمه)

```csharp
// Program.cs — built-in
builder.Logging.AddConsole();
// Request logging middleware خودکار
```

---

## ۸. چه چیزی Log نکنیم

| ❌ لاگ نکنید | ✅ لاگ کنید |
|-------------|------------|
| Password | UserId |
| Credit card | OrderId |
| JWT token کامل | Request path |
| PII حساس | Error message |

---

## ۹. Correlation ID

```csharp
_logger.LogInformation("[{CorrelationId}] Starting request", correlationId);
```

برای trace کردن request در microservices.

---

## ۱۰. Scope

```csharp
using (_logger.BeginScope(new Dictionary<string, object> { ["OrderId"] = 42 }))
{
    _logger.LogInformation("Step 1");
    _logger.LogInformation("Step 2");
}
// هر دو log شامل OrderId=42
```

---

## ۱۱. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_concept.cs](./examples/01_concept.cs) | log levels ساده |
| [02_practice.cs](./examples/02_practice.cs) | custom logger |
| [03_real_world.cs](./examples/03_real_world.cs) | service logging |
| [04_structured_logging.cs](./examples/04_structured_logging.cs) | structured |

---

## ۱۲. Log Sinks

| Sink | مقصد |
|------|------|
| Console | terminal |
| File | rolling file |
| Seq | structured server |
| Elasticsearch | ELK stack |
| Application Insights | Azure |

---

## ۱۳. Performance

- `LogDebug` با check: `_logger.IsEnabled(LogLevel.Debug)`
- high-volume: async sinks
- sampling در production

---

## ۱۴. اشتباهات رایج

| اشتباه | مشکل |
|--------|------|
| Console.WriteLine در production | no levels, no structure |
| log everything | noise, cost |
| log sensitive data | security breach |
| exception بدون stack | debug سخت |
| wrong level | alert fatigue |

---

## ۱۵. تمرین کلاسی

1. SimpleLogger با 3 level
2. log در try/catch
3. structured message با parameters

**تکلیف:** [question.md](./exercises/question.md)

---

## 📚 مطالعه تکمیلی

- [Logging in .NET](https://learn.microsoft.com/dotnet/core/extensions/logging)
- [Serilog](https://serilog.net/)

---

**جلسه قبل:** [۳۳ — Unit Testing](../session-33-unit-testing/) | **بعد:** [۳۵ — Configuration](../session-35-configuration/)
