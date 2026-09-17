// ============================================================
// جلسه ۴۰ — ساخت پروژه وب
// فایل: 03_di_registration.cs — Dependency Injection
// ============================================================

interface IEmailSender { void Send(string to, string subject); }
interface INotificationService { void NotifyNewUser(string email); }

class ConsoleEmailSender : IEmailSender
{
    public void Send(string to, string subject)
        => Console.WriteLine($"  📧 Email → {to}: {subject}");
}

class NotificationService : INotificationService
{
    private readonly IEmailSender _email;
    public NotificationService(IEmailSender email) => _email = email;

    public void NotifyNewUser(string email)
        => _email.Send(email, "خوش آمدید!");
}

// DI Container ساده (شبیه IServiceProvider)
class ServiceContainer
{
    private readonly Dictionary<Type, Func<object>> _factories = new();

    public void AddSingleton<TInterface, TImpl>() where TImpl : TInterface, new()
        => _factories[typeof(TInterface)] = () => new TImpl();

    public void AddScoped<TInterface, TImpl>() where TImpl : TInterface, new()
        => _factories[typeof(TInterface)] = () => new TImpl();

    public void AddScoped<TInterface>(Func<ServiceContainer, TInterface> factory)
        => _factories[typeof(TInterface)] = () => factory(this)!;

    public T GetRequired<T>()
        => (T)_factories[typeof(T)]();
}

// Composition Root (Program.cs)
var services = new ServiceContainer();
services.AddSingleton<IEmailSender, ConsoleEmailSender>();
services.AddScoped<INotificationService>(sp =>
    new NotificationService(sp.GetRequired<IEmailSender>()));

Console.WriteLine("=== Program.cs — Service Registration ===");
Console.WriteLine("  AddSingleton<IEmailSender, ConsoleEmailSender>");
Console.WriteLine("  AddScoped<INotificationService, NotificationService>");

Console.WriteLine("\n=== Controller استفاده می‌کند ===");
var notifier = services.GetRequired<INotificationService>();
notifier.NotifyNewUser("user@example.com");

Console.WriteLine("\n=== Lifetime ===");
Console.WriteLine("  Singleton: یک instance برای کل app");
Console.WriteLine("  Scoped: یک instance per request");
Console.WriteLine("  Transient: instance جدید هر بار");
