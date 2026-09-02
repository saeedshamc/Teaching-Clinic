// تمرین ۱–۳

class Temperature
{
    private double _celsius;
    public double Celsius
    {
        get => _celsius;
        set => _celsius = value < -273.15 ? -273.15 : value;
    }
    public double Fahrenheit
    {
        get => _celsius * 9 / 5 + 32;
        set => Celsius = (value - 32) * 5 / 9;
    }
}

class Book
{
    public string Title { get; }
    public string Author { get; }
    public int Pages { get; }
    public bool IsLong => Pages > 300;

    public Book(string title, string author, int pages)
    {
        Title = title; Author = author; Pages = pages;
    }
}

class BankAccount
{
    public string Owner { get; }
    public double Balance { get; private set; }

    public BankAccount(string owner, double initialBalance)
    {
        Owner = owner;
        Balance = initialBalance >= 0 ? initialBalance : 0;
    }

    public void Deposit(double amount) { if (amount > 0) Balance += amount; }
    public bool Withdraw(double amount)
    {
        if (amount > 0 && amount <= Balance) { Balance -= amount; return true; }
        return false;
    }
}

var t = new Temperature { Celsius = 25 };
Console.WriteLine($"{t.Celsius}°C = {t.Fahrenheit:F1}°F");

var book = new Book("کلین کد", "رابرت مارتین", 464);
Console.WriteLine($"{book.Title}: بلند؟ {book.IsLong}");

var acc = new BankAccount("علی", 1_000_000);
acc.Deposit(500_000);
acc.Withdraw(200_000);
Console.WriteLine($"{acc.Owner}: {acc.Balance:N0}");
