// ============================================================
// تمرین ۱ جلسه ۴۲ — SRP Refactor (پاسخ نمونه)
// ============================================================

class Invoice { public int Id { get; set; } public decimal Amount { get; set; } }

class InvoiceRepository
{
    public void Save(Invoice inv) => Console.WriteLine($"  DB: Invoice #{inv.Id} saved");
}

class InvoicePrinter
{
    public void Print(Invoice inv) => Console.WriteLine($"  Print: Invoice #{inv.Id} — {inv.Amount:N0}");
}

class TaxCalculator
{
    public decimal Calculate(decimal amount) => amount * 0.09m;
}

class InvoiceService
{
    private readonly InvoiceRepository _repo;
    private readonly InvoicePrinter _printer;
    private readonly TaxCalculator _tax;
    public InvoiceService(InvoiceRepository r, InvoicePrinter p, TaxCalculator t)
    { _repo = r; _printer = p; _tax = t; }

    public void Process(Invoice inv)
    {
        var tax = _tax.Calculate(inv.Amount);
        Console.WriteLine($"  Tax: {tax:N0}");
        _repo.Save(inv);
        _printer.Print(inv);
    }
}

new InvoiceService(new InvoiceRepository(), new InvoicePrinter(), new TaxCalculator())
    .Process(new Invoice { Id = 1, Amount = 1_000_000 });
