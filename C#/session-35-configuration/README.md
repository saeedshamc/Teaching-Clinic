# جلسه ۳۵: Configuration (تنظیمات برنامه) — ۱.۵ ساعت

## 🎯 اهداف یادگیری

- مدیریت settings با `IConfiguration`
- فایل‌های `appsettings.json` و environment-specific
- Environment Variables و secrets
- Options Pattern با `IOptions<T>`
- binding configuration به class
- hierarchy و override settings

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | چرا configuration؟ |
| 10–25 | appsettings.json |
| 25–40 | IConfiguration |
| 40–55 | Options Pattern |
| 55–70 | environment و secrets |
| 70–85 | تمرین |
| 85–90 | جمع‌بندی + تکلیف |

---

## ۱. چرا Configuration؟

Hardcode کردن settings = بد:

```csharp
// ❌
string connStr = "Server=...;Password=secret";
```

- تغییر نیاز به recompile
- secrets در source code
- محیط dev/staging/prod متفاوت

---

## ۲. appsettings.json

```json
{
  "Logging": {
    "LogLevel": {
      "Default": "Information"
    }
  },
  "ConnectionStrings": {
    "Default": "Server=localhost;Database=MyApp"
  },
  "AppSettings": {
    "MaxRetries": 3,
    "ApiBaseUrl": "https://api.example.com"
  }
}
```

---

## ۳. IConfiguration

```csharp
var builder = new ConfigurationBuilder()
    .SetBasePath(Directory.GetCurrentDirectory())
    .AddJsonFile("appsettings.json", optional: false)
    .AddEnvironmentVariables();

IConfiguration config = builder.Build();

string url = config["AppSettings:ApiBaseUrl"];
int retries = config.GetValue<int>("AppSettings:MaxRetries");
```

Package: `Microsoft.Extensions.Configuration.Json`

---

## ۴. Binding به Class

```csharp
public class AppSettings
{
    public int MaxRetries { get; set; }
    public string ApiBaseUrl { get; set; } = "";
}

var settings = config.GetSection("AppSettings").Get<AppSettings>();
```

---

## ۵. Options Pattern

```csharp
services.Configure<AppSettings>(config.GetSection("AppSettings"));

public class MyService
{
    private readonly AppSettings _settings;
    public MyService(IOptions<AppSettings> options)
    {
        _settings = options.Value;
    }
}
```

Package: `Microsoft.Extensions.Options`

---

## ۶. Environment-specific

```
appsettings.json              # base
appsettings.Development.json  # override در dev
appsettings.Production.json   # override در prod
```

```csharp
.AddJsonFile("appsettings.json")
.AddJsonFile($"appsettings.{env}.json", optional: true)
```

---

## ۷. Environment Variables

```powershell
$env:AppSettings__ApiBaseUrl = "https://prod.api.com"
$env:ConnectionStrings__Default = "Server=prod;..."
```

`__` (double underscore) = `:` در hierarchy.

---

## ۸. User Secrets (Development)

```powershell
dotnet user-secrets init
dotnet user-secrets set "ConnectionStrings:Default" "Server=...;Password=..."
```

---

## ۹. Priority (آخرین برنده)

```
appsettings.json
  ← appsettings.{Environment}.json
    ← Environment Variables
      ← Command-line args
        ← User Secrets (dev)
```

---

## ۱۰. Strongly Typed Access

```csharp
public class DatabaseOptions
{
    public const string SectionName = "Database";
    public string Host { get; set; } = "localhost";
    public int Port { get; set; } = 5432;
}

// Register
services.Configure<DatabaseOptions>(
    config.GetSection(DatabaseOptions.SectionName));
```

---

## ۱۱. Validation

```csharp
services.AddOptions<AppSettings>()
    .Bind(config.GetSection("AppSettings"))
    .ValidateDataAnnotations()
    .ValidateOnStart();
```

---

## ۱۲. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_concept.cs](./examples/01_concept.cs) | JSON config ساده |
| [02_practice.cs](./examples/02_practice.cs) | binding |
| [03_real_world.cs](./examples/03_real_world.cs) | Options pattern |
| [04_environment_override.cs](./examples/04_environment_override.cs) | override |

---

## ۱۳. Azure Key Vault (مقدمه)

```csharp
builder.Configuration.AddAzureKeyVault(...);
```

secrets production در cloud.

---

## ۱۴. اشتباهات رایج

| اشتباه | مشکل |
|--------|------|
| secrets در git | leak |
| hardcode | inflexible |
| typo in key | null silently |
| no validation | runtime error |
| wrong priority | prod uses dev settings |

---

## ۱۵. تمرین کلاسی

1. appsettings.json با 3 setting
2. bind به class
3. environment variable override

**تکلیف:** [question.md](./exercises/question.md)

---

## 📚 مطالعه تکمیلی

- [Configuration in .NET](https://learn.microsoft.com/dotnet/core/extensions/configuration)
- [Options pattern](https://learn.microsoft.com/dotnet/core/extensions/options)

---

**جلسه قبل:** [۳۴ — Logging](../session-34-logging/) | **بعد:** [۳۶ — DI](../session-36-dependency-injection/)
