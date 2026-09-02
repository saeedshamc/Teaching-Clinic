# جلسه ۳۶: Dependency Injection (DI) — ۱.۵ ساعت

## 🎯 اهداف یادگیری

- درک Dependency Injection و Inversion of Control
- Service lifetimes: Singleton، Scoped، Transient
- `IServiceCollection` و `ServiceProvider`
- Constructor Injection (روش ترجیحی)
- register و resolve services
- DI در ASP.NET Core
- testability با DI

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | مشکل tight coupling |
| 10–25 | DI concept |
| 25–40 | Microsoft DI container |
| 40–55 | lifetimes |
| 55–70 | register patterns |
| 70–85 | تمرین |
| 85–90 | جمع‌بندی + تکلیف |

---

## ۱. مشکل — Tight Coupling

```csharp
// ❌
public class OrderService
{
    private readonly SqlOrderRepository _repo = new(); // hard dependency
}
```

- test سخت (نمی‌توان mock کرد)
- تغییر implementation = تغییر OrderService
- new() everywhere

---

## ۲. راه‌حل — Dependency Injection

```csharp
public class OrderService
{
    private readonly IOrderRepository _repo;

    public OrderService(IOrderRepository repo) // inject
    {
        _repo = repo;
    }
}
```

**IoC:** control of dependency creation به container سپرده می‌شود.

---

## ۳. Microsoft.Extensions.DependencyInjection

```powershell
dotnet add package Microsoft.Extensions.DependencyInjection
```

```csharp
var services = new ServiceCollection();
services.AddSingleton<IOrderRepository, SqlOrderRepository>();
services.AddScoped<OrderService>();
services.AddTransient<IEmailSender, SmtpEmailSender>();

var provider = services.BuildServiceProvider();
var orderService = provider.GetRequiredService<OrderService>();
```

---

## ۴. Service Lifetimes

| Lifetime | instance | کاربرد |
|----------|----------|--------|
| **Singleton** | یک instance کل app | cache، config |
| **Scoped** | یک instance per request/scope | DbContext، UnitOfWork |
| **Transient** | هر resolve = instance جدید | lightweight services |

```csharp
services.AddSingleton<ICache, MemoryCache>();
services.AddScoped<IUserService, UserService>();
services.AddTransient<IValidator, OrderValidator>();
```

---

## ۵. Scoped با using

```csharp
using var scope = provider.CreateScope();
var service = scope.ServiceProvider.GetRequiredService<IUserService>();
// scope dispose → scoped services dispose
```

---

## ۶. Register Patterns

```csharp
// interface → implementation
services.AddTransient<IRepository, EfRepository>();

// concrete class
services.AddTransient<OrderService>();

// factory
services.AddTransient<IConnection>(sp =>
{
    var config = sp.GetRequiredService<IConfiguration>();
    return new SqlConnection(config.GetConnectionString("Default"));
});

// instance
services.AddSingleton<IAppSettings>(existingSettings);
```

---

## ۷. Multiple Interfaces

```csharp
services.AddTransient<IOrderRepository, SqlOrderRepository>();
services.AddTransient<IReadOnlyOrderRepository>(sp =>
    sp.GetRequiredService<IOrderRepository>());
```

---

## ۸. ASP.NET Core DI

```csharp
var builder = WebApplication.CreateBuilder(args);
builder.Services.AddControllers();
builder.Services.AddScoped<IProductService, ProductService>();
// container خودکار resolve می‌کند
```

---

## ۹. Anti-Patterns

| Anti-Pattern | مشکل |
|--------------|------|
| Service Locator | hidden dependencies |
| Property Injection | optional deps unclear |
| Singleton → Scoped | captive dependency |
| too many deps (>5) | God class |
| `new` in service | defeats DI |

---

## ۱۰. Captive Dependency

```csharp
// ❌ Singleton holds Scoped
services.AddSingleton<BadService>(); // depends on DbContext (Scoped)

// ✅ match lifetimes
services.AddScoped<GoodService>();
```

---

## ۱۱. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_concept.cs](./examples/01_concept.cs) | DI پایه |
| [02_practice.cs](./examples/02_practice.cs) | manual container |
| [03_real_world.cs](./examples/03_real_world.cs) | layered app |
| [04_lifetimes.cs](./examples/04_lifetimes.cs) | singleton/scoped/transient |

---

## ۱۲. Composition Root

یک نقطه — معمولاً `Program.cs`:

```csharp
// register all services here
// business code فقط constructor injection
```

---

## ۱۳. Testing با DI

```csharp
var services = new ServiceCollection();
services.AddTransient<IRepository, FakeRepository>();
services.AddTransient<OrderService>();
var svc = services.BuildServiceProvider().GetRequiredService<OrderService>();
```

---

## ۱۴. Keyed Services (.NET 8+)

```csharp
services.AddKeyedSingleton<ICache, RedisCache>("redis");
services.AddKeyedSingleton<ICache, MemoryCache>("memory");

var redis = provider.GetRequiredKeyedService<ICache>("redis");
```

---

## ۱۵. تمرین کلاسی

1. IMessageSender + EmailSender + SmsSender
2. NotificationService با DI
3. Fake sender برای test

**تکلیف:** [question.md](./exercises/question.md)

---

## 📚 مطالعه تکمیلی

- [Dependency injection in .NET](https://learn.microsoft.com/dotnet/core/extensions/dependency-injection)
- SOLID — Dependency Inversion Principle

---

**جلسه قبل:** [۳۵ — Configuration](../session-35-configuration/) | **بعد:** [۳۷ — EF Core](../session-37-ef-core/)
