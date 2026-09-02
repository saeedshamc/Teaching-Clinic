// ============================================================
// پاسخ نمونه — تمرین‌های خانه (۵–۸)
// ============================================================

class ProductNotFoundException : Exception
{
    public int ProductId { get; }
    public ProductNotFoundException(int id)
        : base($"محصول {id} یافت نشد") => ProductId = id;
}

string GetProduct(int id) => id switch
{
    1 => "لپ‌تاپ",
    2 => "ماوس",
    _ => throw new ProductNotFoundException(id)
};

try
{
    Console.WriteLine(GetProduct(1));
    Console.WriteLine(GetProduct(99));
}
catch (ProductNotFoundException ex)
{
    Console.WriteLine($"❌ {ex.Message}");
}

// ۶. Calculator
double Calc(double x, double y, char op)
{
    return op switch
    {
        '+' => x + y,
        '-' => x - y,
        '*' => x * y,
        '/' => y == 0 ? throw new DivideByZeroException() : x / y,
        _   => throw new FormatException("عملگر نامعتبر")
    };
}

try { Console.WriteLine($"10/2={Calc(10, 2, '/')}"); }
catch (DivideByZeroException) { Console.WriteLine("÷0"); }

try { Console.WriteLine(Calc(10, 0, '/')); }
catch (DivideByZeroException) { Console.WriteLine("÷0"); }

// ۷. Retry
bool TryParseWithRetry(string input, int maxAttempts, out int result)
{
    result = 0;
    for (int i = 0; i < maxAttempts; i++)
    {
        if (int.TryParse(input, out result)) return true;
    }
    return false;
}

Console.WriteLine(TryParseWithRetry("abc", 3, out _) ? "OK" : "Give up");

// ۸. ATM
class InsufficientBalanceException : Exception
{
    public InsufficientBalanceException(string msg) : base(msg) { }
}

void Withdraw(ref decimal balance, decimal amount)
{
    if (amount > balance)
        throw new InsufficientBalanceException($"موجودی {balance:N0} کافی نیست");
    balance -= amount;
}

decimal bal = 500_000;
try
{
    Withdraw(ref bal, 100_000);
    Console.WriteLine($"موجودی: {bal:N0}");
}
catch (InsufficientBalanceException ex) { Console.WriteLine(ex.Message); }
finally { Console.WriteLine("[LOG] تراکنش پایان"); }
