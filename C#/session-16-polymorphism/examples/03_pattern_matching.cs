// ============================================================
// جلسه ۱۶ — Pattern Matching
// فایل: 03_pattern_matching.cs
// ============================================================

abstract class Payment
{
    public double Amount { get; set; }
    public abstract string GetDescription();
}

class CreditCard : Payment
{
    public string LastFour { get; set; } = "";
    public override string GetDescription() => $"کارت ****{LastFour}";
}

class BankTransfer : Payment
{
    public string Iban { get; set; } = "";
    public override string GetDescription() => $"انتقال به {Iban}";
}

class Cash : Payment
{
    public override string GetDescription() => "پرداخت نقدی";
}

// Switch statement با pattern
string GetFee(Payment p) => p switch
{
    CreditCard => "کارمزد ۲٪",
    BankTransfer => "کارمزد ۵,۰۰۰ تومان",
    Cash => "بدون کارمزد",
    _ => "نامشخص"
};

void ProcessPayment(Payment payment)
{
    Console.WriteLine($"--- پرداخت {payment.Amount:N0} تومان ---");
    Console.WriteLine($"روش: {payment.GetDescription()}");
    Console.WriteLine($"کارمزد: {GetFee(payment)}");

    switch (payment)
    {
        case CreditCard cc when payment.Amount > 10_000_000:
            Console.WriteLine("⚠️ مبلغ بالا — تأیید دو مرحله‌ای");
            break;
        case CreditCard:
            Console.WriteLine("✅ پرداخت کارتی تأیید شد");
            break;
        case BankTransfer bt:
            Console.WriteLine($"✅ انتقال به {bt.Iban} ثبت شد");
            break;
        case Cash:
            Console.WriteLine("✅ دریافت نقدی");
            break;
    }
}

Payment[] payments =
{
    new CreditCard { Amount = 500_000, LastFour = "1234" },
    new BankTransfer { Amount = 2_000_000, Iban = "IR1234567890" },
    new Cash { Amount = 100_000 },
    new CreditCard { Amount = 15_000_000, LastFour = "5678" }
};

foreach (var p in payments)
    ProcessPayment(p);
