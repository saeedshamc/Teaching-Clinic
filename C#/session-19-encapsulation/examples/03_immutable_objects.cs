// ============================================================
// جلسه ۱۹ — Immutable Objects
// فایل: 03_immutable_objects.cs
// ============================================================

// record — immutable by default
record Address(string City, string Street, string PostalCode);

record PersonRecord(string Name, int Age, Address Address);

var addr = new Address("تهران", "ولیعصر", "1234567890");
var person = new PersonRecord("علی", 30, addr);

// with expression — کپی با تغییر
var older = person with { Age = 31 };
var moved = person with { Address = addr with { City = "اصفهان" } };

Console.WriteLine($"اصلی: {person.Name}, {person.Age}, {person.Address.City}");
Console.WriteLine($"بزرگتر: {older.Name}, {older.Age}");
Console.WriteLine($"نقل‌مکان: {moved.Address.City}");

// کلاس immutable دستی
class Money
{
    public decimal Amount { get; }
    public string Currency { get; }

    public Money(decimal amount, string currency)
    {
        if (amount < 0) throw new ArgumentException("مبلغ منفی");
        Amount = amount;
        Currency = currency;
    }

    public Money Add(Money other)
    {
        if (Currency != other.Currency)
            throw new InvalidOperationException("ارز متفاوت");
        return new Money(Amount + other.Amount, Currency);
    }

    public override string ToString() => $"{Amount:N0} {Currency}";
}

var m1 = new Money(100_000, "IRR");
var m2 = new Money(50_000, "IRR");
var total = m1.Add(m2);
Console.WriteLine($"\n{m1} + {m2} = {total}");
