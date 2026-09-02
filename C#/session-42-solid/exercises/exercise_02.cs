// ============================================================
// تمرین ۲ جلسه ۴۲ — OCP + DIP (پاسخ نمونه)
// ============================================================

interface IPaymentMethod { void Pay(decimal amount); }

class CardPayment : IPaymentMethod
{
    public void Pay(decimal amount) => Console.WriteLine($"  💳 Card: {amount:N0}");
}

class WalletPayment : IPaymentMethod
{
    public void Pay(decimal amount) => Console.WriteLine($"  👛 Wallet: {amount:N0}");
}

class PaymentService
{
    private readonly IPaymentMethod _method;
    public PaymentService(IPaymentMethod method) => _method = method;
    public void Checkout(decimal amount) => _method.Pay(amount);
}

// OCP: CryptoPayment بدون تغییر PaymentService
class CryptoPayment : IPaymentMethod
{
    public void Pay(decimal amount) => Console.WriteLine($"  ₿ Crypto: {amount:N0}");
}

new PaymentService(new CardPayment()).Checkout(500_000);
new PaymentService(new WalletPayment()).Checkout(200_000);
new PaymentService(new CryptoPayment()).Checkout(100_000);
