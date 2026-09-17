// ============================================================
// جلسه ۱۳ — چند شیء مستقل از یک کلاس
// فایل: 02_multiple_objects.cs
// ============================================================

class BankAccount
{
    public string Owner = "";
    public double Balance;

    public void Deposit(double amount)
    {
        if (amount > 0)
            Balance += amount;
    }

    public bool Withdraw(double amount)
    {
        if (amount > 0 && amount <= Balance)
        {
            Balance -= amount;
            return true;
        }
        return false;
    }

    public void PrintStatus() =>
        Console.WriteLine($"{Owner}: موجودی = {Balance:N0} تومان");
}

// هر new یک شیء جدا در حافظه می‌سازد
BankAccount acc1 = new BankAccount { Owner = "علی", Balance = 1_000_000 };
BankAccount acc2 = new BankAccount { Owner = "رضا", Balance = 500_000 };

acc1.Deposit(200_000);
acc2.Withdraw(100_000);

acc1.PrintStatus();  // علی: 1,200,000
acc2.PrintStatus();  // رضا: 400,000

// Reference Type: کپی reference نه کپی شیء
BankAccount acc3 = acc1;
acc3.Deposit(50_000);
Console.WriteLine("\nبعد از واریز به acc3:");
acc1.PrintStatus();  // علی هم تغییر کرد — همان شیء!
