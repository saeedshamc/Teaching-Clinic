# تمرین‌های جلسه ۳۵ — Configuration

## کلاسی (۳۰ دقیقه)

1. `appsettings.json` با ConnectionString, ApiUrl, MaxRetries
2. bind به `AppSettings` class
3. simulate environment override

## خانه (۴۵ دقیقه)

4. پروژه console + `Microsoft.Extensions.Configuration.Json`
5. `appsettings.Development.json` override
6. User Secrets برای connection string

## Setup

```powershell
dotnet add package Microsoft.Extensions.Configuration.Json
dotnet add package Microsoft.Extensions.Configuration.EnvironmentVariables
dotnet add package Microsoft.Extensions.Options
```

پاسخ نمونه: [exercise_01.cs](./exercise_01.cs)
