// ============================================================
// جلسه ۱۹ — Access Modifiers
// فایل: 01_access_modifiers.cs
// ============================================================

class BankAccount
{
    // private — فقط داخل این کلاس
    private double _balance;
    private string _pin;

    // public — دسترسی از همه‌جا
    public string AccountNumber { get; }
    public string Owner { get; }

    // protected — این کلاس + فرزندان (در فایل دیگر)
    protected DateTime LastTransaction;

    public BankAccount(string number, string owner, string pin, double initial)
    {
        AccountNumber = number;
        Owner = owner;
        _pin = pin;
        _balance = initial;
        LastTransaction = DateTime.Now;
    }

    // public API — رابط کنترل‌شده
    public double GetBalance() => _balance;

    public bool Deposit(double amount, string pin)
    {
        if (!VerifyPin(pin)) return false;
        if (amount <= 0) return false;
        _balance += amount;
        LastTransaction = DateTime.Now;
        return true;
    }

    public bool Withdraw(double amount, string pin)
    {
        if (!VerifyPin(pin)) return false;
        if (amount <= 0 || amount > _balance) return false;
        _balance -= amount;
        LastTransaction = DateTime.Now;
        return true;
    }

    // private — جزئیات داخلی پنهان
    private bool VerifyPin(string pin) => _pin == pin;
}

var account = new BankAccount("1001", "علی", "1234", 1_000_000);
Console.WriteLine($"{account.Owner}: {account.GetBalance():N0}");

account.Deposit(500_000, "1234");
account.Withdraw(200_000, "1234");
Console.WriteLine($"موجودی: {account.GetBalance():N0}");

// account._balance = 999999;  // ❌ compile error — private
// account._pin = "0000";      // ❌ compile error
