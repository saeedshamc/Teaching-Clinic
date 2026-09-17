// تمرین ۴–۵

interface IStorage
{
    void Save(string key, string data);
    string? Load(string key);
    bool Delete(string key);
}

class MemoryStorage : IStorage
{
    private readonly Dictionary<string, string> _data = new();
    public void Save(string key, string data) => _data[key] = data;
    public string? Load(string key) => _data.GetValueOrDefault(key);
    public bool Delete(string key) => _data.Remove(key);
}

interface IPaymentGateway
{
    bool Process(double amount);
    string GatewayName { get; }
}

class ZarinpalGateway : IPaymentGateway
{
    public string GatewayName => "Zarinpal";
    public bool Process(double amount)
    {
        Console.WriteLine($"Zarinpal: پرداخت {amount:N0} تومان");
        return amount > 0;
    }
}

class MockGateway : IPaymentGateway
{
    public string GatewayName => "Mock";
    public bool Process(double amount) { Console.WriteLine($"[TEST] {amount:N0}"); return true; }
}

class CheckoutService
{
    private readonly IPaymentGateway _gateway;
    public CheckoutService(IPaymentGateway gateway) => _gateway = gateway;
    public bool Checkout(double amount) => _gateway.Process(amount);
}

var storage = new MemoryStorage();
storage.Save("user:1", "علی");
Console.WriteLine($"Load: {storage.Load("user:1")}");

var checkout = new CheckoutService(new ZarinpalGateway());
checkout.Checkout(500_000);

var testCheckout = new CheckoutService(new MockGateway());
testCheckout.Checkout(100_000);
