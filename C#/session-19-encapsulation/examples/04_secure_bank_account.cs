// ============================================================
// جلسه ۱۹ — حساب بانکی امن
// فایل: 04_secure_bank_account.cs
// ============================================================

class SecureBankAccount
{
    private double _balance;
    private readonly List<string> _transactionLog = new();
    private int _dailyWithdrawCount;
    private const int MaxDailyWithdrawals = 5;
    private const double MaxSingleWithdraw = 5_000_000;

    public string AccountNumber { get; }
    public string Owner { get; }
    public double Balance => _balance;
    public IReadOnlyList<string> TransactionLog => _transactionLog.AsReadOnly();

    public SecureBankAccount(string number, string owner, double initial)
    {
        AccountNumber = number;
        Owner = owner;
        _balance = initial >= 0 ? initial : 0;
        Log($"حساب باز شد — موجودی اولیه: {_balance:N0}");
    }

    public bool Deposit(double amount)
    {
        if (amount <= 0) return false;
        _balance += amount;
        Log($"واریز: +{amount:N0} → موجودی: {_balance:N0}");
        return true;
    }

    public bool Withdraw(double amount)
    {
        if (amount <= 0) return false;
        if (amount > MaxSingleWithdraw)
        {
            Log($"رد برداشت — سقف {MaxSingleWithdraw:N0}");
            return false;
        }
        if (_dailyWithdrawCount >= MaxDailyWithdrawals)
        {
            Log("رد برداشت — سقف روزانه");
            return false;
        }
        if (amount > _balance) return false;

        _balance -= amount;
        _dailyWithdrawCount++;
        Log($"برداشت: -{amount:N0} → موجودی: {_balance:N0}");
        return true;
    }

    private void Log(string message) =>
        _transactionLog.Add($"[{DateTime.Now:HH:mm:ss}] {message}");
}

var acc = new SecureBankAccount("IR-1001", "علی احمدی", 2_000_000);
acc.Deposit(1_000_000);
acc.Withdraw(500_000);
acc.Withdraw(10_000_000);  // رد — سقف

Console.WriteLine($"\nموجودی نهایی: {acc.Balance:N0}");
Console.WriteLine("\n=== گزارش تراکنش‌ها ===");
foreach (var log in acc.TransactionLog)
    Console.WriteLine(log);
