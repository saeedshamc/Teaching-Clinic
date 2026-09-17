// تمرین ۱–۳

class SecureBankAccount
{
    private double _balance;
    public string Owner { get; }
    public double Balance => _balance;
    public SecureBankAccount(string owner, double initial) { Owner = owner; _balance = Math.Max(0, initial); }
    public bool Deposit(double a) { if (a <= 0) return false; _balance += a; return true; }
    public bool Withdraw(double a) { if (a <= 0 || a > _balance) return false; _balance -= a; return true; }
}

class Product
{
    private double _price; private int _stock;
    public string Name { get; set; } = "";
    public double Price { get => _price; set { if (value > 0) _price = value; } }
    public int Stock { get => _stock; private set => _stock = Math.Max(0, value); }
    public void Restock(int q) { if (q > 0) Stock += q; }
    public bool TrySell(int q) { if (q <= 0 || q > Stock) return false; Stock -= q; return true; }
}

class Team
{
    private readonly List<string> _members = new();
    public IReadOnlyList<string> Members => _members.AsReadOnly();
    public void AddMember(string name) { if (!string.IsNullOrWhiteSpace(name)) _members.Add(name); }
    public bool RemoveMember(string name) => _members.Remove(name);
}

var acc = new SecureBankAccount("علی", 1_000_000);
acc.Deposit(500_000); acc.Withdraw(200_000);
Console.WriteLine($"{acc.Owner}: {acc.Balance:N0}");

var p = new Product { Name = "ماوس", Price = 450_000 }; p.Restock(10); p.TrySell(3);
Console.WriteLine($"{p.Name}: موجودی {p.Stock}");

var team = new Team(); team.AddMember("علی"); team.AddMember("مریم");
Console.WriteLine($"تیم: {string.Join(", ", team.Members)}");
