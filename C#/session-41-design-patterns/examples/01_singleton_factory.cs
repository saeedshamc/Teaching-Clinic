// ============================================================
// جلسه ۴۱ — Design Patterns
// فایل: 01_singleton_factory.cs
// ============================================================

// Singleton (ترجیح: DI در production)
sealed class AppSettings
{
    private static readonly Lazy<AppSettings> _instance = new(() => new AppSettings());
    public static AppSettings Instance => _instance.Value;
    private AppSettings() { }
    public string AppName { get; set; } = "ClinicApp";
}

Console.WriteLine("=== Singleton ===");
Console.WriteLine($"  AppName: {AppSettings.Instance.AppName}");
Console.WriteLine($"  Same instance: {ReferenceEquals(AppSettings.Instance, AppSettings.Instance)}");

// Factory Method
interface IPaymentProcessor { void Pay(decimal amount); }

class CreditCardProcessor : IPaymentProcessor
{
    public void Pay(decimal amount) => Console.WriteLine($"  💳 کارت: {amount:N0} تومان");
}

class WalletProcessor : IPaymentProcessor
{
    public void Pay(decimal amount) => Console.WriteLine($"  👛 کیف پول: {amount:N0} تومان");
}

static class PaymentProcessorFactory
{
    public static IPaymentProcessor Create(string type) => type switch
    {
        "card" => new CreditCardProcessor(),
        "wallet" => new WalletProcessor(),
        _ => throw new ArgumentException($"نوع '{type}' نامعتبر")
    };
}

Console.WriteLine("\n=== Factory ===");
PaymentProcessorFactory.Create("card").Pay(150_000);
PaymentProcessorFactory.Create("wallet").Pay(50_000);
