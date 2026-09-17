// تمرین ۴–۵

class BankAccount
{
    protected double Balance;
    public BankAccount(double initial) => Balance = initial;
    public virtual void Deposit(double amount) { if (amount > 0) Balance += amount; }
    public virtual bool Withdraw(double amount)
    {
        if (amount > 0 && amount <= Balance) { Balance -= amount; return true; }
        return false;
    }
    public double GetBalance() => Balance;
}

class SavingsAccount : BankAccount
{
    public double InterestRate { get; set; }
    public SavingsAccount(double initial) : base(initial) { }
    public void AddInterest() => Balance += Balance * InterestRate;
}

class CheckingAccount : BankAccount
{
    public double OverdraftLimit { get; set; }
    public CheckingAccount(double initial) : base(initial) { }
    public override bool Withdraw(double amount)
    {
        if (amount > 0 && amount <= Balance + OverdraftLimit)
        { Balance -= amount; return true; }
        return false;
    }
}

class Character
{
    public string Name { get; set; } = "";
    public int HP { get; set; } = 100;
    public virtual int Attack() => 10;
    public virtual void TakeDamage(int dmg) => HP = Math.Max(0, HP - dmg);
}

class Warrior : Character
{
    public int Armor { get; set; } = 5;
    public override int Attack() => 20 + Armor;
    public override void TakeDamage(int dmg) => base.TakeDamage(Math.Max(0, dmg - Armor));
}

class Mage : Character
{
    public int Mana { get; set; } = 50;
    public int CastSpell()
    {
        if (Mana < 10) return 0;
        Mana -= 10;
        return 35;
    }
}

var savings = new SavingsAccount(1_000_000) { InterestRate = 0.05 };
savings.AddInterest();
Console.WriteLine($"پس‌انداز: {savings.GetBalance():N0}");

Character[] party = { new Warrior { Name = "آرش", Armor = 8 }, new Mage { Name = "لیلا" } };
foreach (var c in party)
{
    int dmg = c.Attack();
    Console.WriteLine($"{c.Name} حمله: {dmg} | HP: {c.HP}");
}
