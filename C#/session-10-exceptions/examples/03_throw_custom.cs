// ============================================================
// جلسه ۱۰ — throw و Custom Exception
// ============================================================

// --- Custom Exception ---
class InsufficientBalanceException : Exception
{
    public decimal Balance { get; }
    public decimal Requested { get; }

    public InsufficientBalanceException(decimal balance, decimal requested)
        : base($"موجودی {balance:N0} کافی نیست (درخواست: {requested:N0})")
    {
        Balance = balance;
        Requested = requested;
    }
}

// --- throw در validation ---
void ValidateAge(int age)
{
    if (age < 0)
        throw new ArgumentOutOfRangeException(nameof(age), "سن نمی‌تواند منفی باشد");
    if (age > 150)
        throw new ArgumentOutOfRangeException(nameof(age), "سن غیرواقعی");
}

try
{
    ValidateAge(-5);
}
catch (ArgumentOutOfRangeException ex)
{
    Console.WriteLine($"❌ {ex.ParamName}: {ex.Message}");
}

Console.WriteLine();

// --- ATM simulation ---
void Withdraw(decimal balance, decimal amount)
{
    if (amount <= 0)
        throw new ArgumentException("مبلغ باید مثبت باشد", nameof(amount));
    if (amount > balance)
        throw new InsufficientBalanceException(balance, amount);
    Console.WriteLine($"✅ برداشت {amount:N0} — موجودی: {balance - amount:N0}");
}

decimal balance = 500_000;
try
{
    Withdraw(balance, 200_000);
    Withdraw(balance, 400_000);  // exception
}
catch (InsufficientBalanceException ex)
{
    Console.WriteLine($"❌ {ex.Message}");
}
catch (ArgumentException ex)
{
    Console.WriteLine($"❌ {ex.Message}");
}

Console.WriteLine();

// --- rethrow ---
void ProcessWithRethrow()
{
    try
    {
        int.Parse("not-a-number");
    }
    catch (FormatException ex)
    {
        Console.WriteLine($"Log: {ex.Message}");
        throw;  // stack trace حفظ — نه throw ex
    }
}

try
{
    ProcessWithRethrow();
}
catch (FormatException ex)
{
    Console.WriteLine($"Caught again: {ex.Message}");
}

Console.WriteLine();

// --- ArgumentNullException ---
void RequireName(string? name)
{
    if (name is null)
        throw new ArgumentNullException(nameof(name));
    Console.WriteLine($"Hello, {name}!");
}

try { RequireName(null); }
catch (ArgumentNullException ex) { Console.WriteLine($"❌ {ex.ParamName} is null"); }

RequireName("علی");
