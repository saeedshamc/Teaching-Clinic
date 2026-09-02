# جلسه ۴۱: Design Patterns (الگوهای طراحی) (۱.۵ ساعت)

## 🎯 اهداف یادگیری

- درک «الگوی طراحی» و زمان استفاده از آن
- پیاده‌سازی Singleton، Factory و Strategy
- شناخت Observer و Decorator در C#
- تشخیص anti-pattern و جایگزین مناسب
- ارتباط الگوها با SOLID (جلسه بعد)

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | الگوی طراحی چیست؟ GOF |
| 10–25 | Singleton — مزایا و خطرات |
| 25–40 | Factory Method و Simple Factory |
| 40–55 | Strategy — رفتار قابل تعویض |
| 55–70 | Observer و Decorator |
| 70–85 | مثال ترکیبی در پروژه وب |
| 85–90 | جمع‌بندی + تکلیف |

---

## ۱. الگوی طراحی چیست؟

**Design Pattern** راه‌حل تکرارشونده برای مشکل رایج در طراحی نرم‌افزار است.

| دسته GOF | الگوهای مهم |
|----------|-------------|
| Creational | Singleton, Factory, Builder |
| Structural | Adapter, Decorator, Facade |
| Behavioral | Strategy, Observer, Command |

**قانون طلایی:** الگو را وقتی استفاده کنید که مشکل واقعی دارید، نه برای «شیک بودن».

---

## ۲. Singleton

یک instance واحد در کل برنامه.

```csharp
public sealed class AppSettings
{
    private static readonly Lazy<AppSettings> _instance = new(() => new AppSettings());
    public static AppSettings Instance => _instance.Value;
    private AppSettings() { }
    public string AppName { get; set; } = "ClinicApp";
}
```

### ⚠️ خطرات Singleton

- تست unit سخت (state مشترک)
- coupling بالا
- در ASP.NET Core ترجیحاً **DI Singleton** بهتر است:

```csharp
builder.Services.AddSingleton<IAppSettings, AppSettings>();
```

---

## ۳. Factory Method

ساخت object بدون expose کردن logic ساخت.

```csharp
public interface IPaymentProcessor
{
    void Pay(decimal amount);
}

public class CreditCardProcessor : IPaymentProcessor
{
    public void Pay(decimal amount) => Console.WriteLine($"کارت: {amount:N0}");
}

public class WalletProcessor : IPaymentProcessor
{
    public void Pay(decimal amount) => Console.WriteLine($"کیف پول: {amount:N0}");
}

public static class PaymentProcessorFactory
{
    public static IPaymentProcessor Create(string type) => type switch
    {
        "card" => new CreditCardProcessor(),
        "wallet" => new WalletProcessor(),
        _ => throw new ArgumentException("نوع نامعتبر")
    };
}
```

---

## ۴. Strategy

الگوریتم را encapsulate و قابل تعویض کنید.

```csharp
public interface IDiscountStrategy
{
    decimal Apply(decimal price);
}

public class NoDiscount : IDiscountStrategy
{
    public decimal Apply(decimal price) => price;
}

public class MemberDiscount : IDiscountStrategy
{
    public decimal Apply(decimal price) => price * 0.9m;
}

public class OrderService
{
    private readonly IDiscountStrategy _discount;
    public OrderService(IDiscountStrategy discount) => _discount = discount;

    public decimal CalculateTotal(decimal price) => _discount.Apply(price);
}
```

**مزیت:** Open/Closed Principle — strategy جدید بدون تغییر OrderService

---

## ۵. Observer

وقتی state تغییر می‌کند، subscribers مطلع شوند.

```csharp
public interface IObserver<T>
{
    void OnNext(T value);
}

public class Subject<T>
{
    private readonly List<IObserver<T>> _observers = new();
    public void Subscribe(IObserver<T> o) => _observers.Add(o);
    public void Notify(T value)
    {
        foreach (var o in _observers) o.OnNext(value);
    }
}
```

در C# مدرن: `IObservable<T>` / `IObserver<T>` یا Events:

```csharp
public class OrderPlacedEvent
{
    public event Action<int>? OrderPlaced;
    public void Place(int orderId) => OrderPlaced?.Invoke(orderId);
}
```

---

## ۶. Decorator

افزودن رفتار بدون تغییر کلاس اصلی.

```csharp
public interface INotifier
{
    void Send(string message);
}

public class EmailNotifier : INotifier
{
    public void Send(string message) => Console.WriteLine($"Email: {message}");
}

public class SmsDecorator : INotifier
{
    private readonly INotifier _inner;
    public SmsDecorator(INotifier inner) => _inner = inner;

    public void Send(string message)
    {
        _inner.Send(message);
        Console.WriteLine($"SMS: {message}");
    }
}
```

---

## ۷. Adapter

تبدیل interface ناسازگار.

```csharp
public interface ILogger
{
    void Log(string message);
}

public class LegacyLogger
{
    public void WriteLog(string msg, int level) { /* ... */ }
}

public class LegacyLoggerAdapter : ILogger
{
    private readonly LegacyLogger _legacy;
    public LegacyLoggerAdapter(LegacyLogger legacy) => _legacy = legacy;
    public void Log(string message) => _legacy.WriteLog(message, 1);
}
```

---

## ۸. فایل‌های این جلسه

| فایل | موضوع |
|------|--------|
| [01_singleton_factory.cs](./examples/01_singleton_factory.cs) | Singleton و Factory |
| [02_strategy.cs](./examples/02_strategy.cs) | Strategy Pattern |
| [03_observer.cs](./examples/03_observer.cs) | Observer Pattern |
| [04_decorator_adapter.cs](./examples/04_decorator_adapter.cs) | Decorator و Adapter |

---

## ۹. مثال واقعی — Payment Gateway

```
CheckoutService
    → PaymentProcessorFactory.Create("card")
    → CreditCardProcessor.Pay(100_000)
    → OrderPlacedEvent → EmailNotifier + SmsDecorator
```

---

## ۱۰. Anti-Patterns

| Anti-Pattern | مشکل | جایگزین |
|--------------|------|---------|
| God Class | همه چیز در یک کلاس | SRP، لایه‌بندی |
| Spaghetti Code | وابستگی نامشخص | DI، interfaces |
| Copy-Paste | تکرار logic | Strategy، Template Method |
| Singleton everywhere | state global | DI Scoped/Singleton |

---

## ۱۱. اشتباهات رایج

- استفاده از Singleton برای DbContext (باید Scoped باشد)
- Factory با switch بزرگ بدون extension point
- Observer بدون unsubscribe → memory leak
- Decorator بیش از حد → stack پیچیده

---

## ۱۲. تمرین کلاسی

1. Factory برای نوع notification (Email, SMS, Push)
2. Strategy برای shipping cost (Standard, Express)
3. Observer: وقتی محصول اضافه شد log کن

---

## ۱۳. نکات حرفه‌ای

- در .NET از **DI** به‌جای manual Singleton استفاده کنید
- **Strategy** + DI = ترکیب قدرتمند
- **Decorator** در Middleware ASP.NET Core استفاده می‌شود
- الگو نام دارد تا در تیم ارتباط راحت‌تر باشد
- Over-engineering از no-pattern هم بدتر است

---

## ۱۴. پیش‌نیاز و ادامه

**پیش‌نیاز:** جلسه ۱۷ (Interface)، ۴۰ (پروژه وب)

**جلسه بعد:** SOLID Principles

**تکلیف:** [question.md](./exercises/question.md)

**جلسه قبل:** [۴۰ — پروژه وب](../session-40-web-project/) | **بعد:** [۴۲ — SOLID](../session-42-solid/)
