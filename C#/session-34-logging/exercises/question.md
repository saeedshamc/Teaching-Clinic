# تمرین‌های جلسه ۳۴ — Logging

## کلاسی (۳۰ دقیقه)

1. `IAppLogger` با Info, Warn, Error
2. log در try/catch/finally
3. structured log با `{UserId}` و `{Action}`

## خانه (۴۵ دقیقه)

4. Serilog + Console sink نصب و تست
5. File sink — log در `app.log`
6. log level filter — فقط Warning+

## Packages

```powershell
dotnet add package Microsoft.Extensions.Logging.Console
dotnet add package Serilog.Sinks.Console
```

پاسخ نمونه: [exercise_01.cs](./exercise_01.cs)
