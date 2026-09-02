// ============================================================
// جلسه ۱۸ — شخصیت‌های بازی
// فایل: 04_game_characters.cs
// ============================================================

abstract class GameCharacter
{
    public string Name { get; set; } = "";
    public int HP { get; protected set; } = 100;
    public int MaxHP { get; protected set; } = 100;

    public abstract int Attack();
    public abstract string ClassName { get; }

    public virtual void TakeDamage(int damage)
    {
        HP = Math.Max(0, HP - damage);
        Console.WriteLine($"  {Name} {damage} آسیب دید → HP: {HP}/{MaxHP}");
    }

    public bool IsAlive => HP > 0;

    public void ShowStatus() =>
        Console.WriteLine($"[{ClassName}] {Name} — HP: {HP}/{MaxHP}");
}

class Warrior : GameCharacter
{
    public int Armor { get; set; } = 10;
    public override string ClassName => "جنگجو";
    public override int Attack() => 25 + Armor / 2;

    public override void TakeDamage(int damage)
    {
        int reduced = Math.Max(0, damage - Armor);
        base.TakeDamage(reduced);
    }
}

class Mage : GameCharacter
{
    public int Mana { get; set; } = 50;
    public override string ClassName => "جادوگر";
    public override int Attack() => Mana >= 15 ? CastSpell() : 5;

    private int CastSpell()
    {
        Mana -= 15;
        return 40;
    }
}

class Archer : GameCharacter
{
    public int Arrows { get; set; } = 20;
    public override string ClassName => "کماندار";
    public override int Attack()
    {
        if (Arrows <= 0) return 3;
        Arrows--;
        return 20;
    }
}

GameCharacter[] party =
{
    new Warrior { Name = "آرش", Armor = 15 },
    new Mage { Name = "لیلا", Mana = 50 },
    new Archer { Name = "کاوه", Arrows = 10 }
};

Console.WriteLine("=== نبرد ===");
foreach (var hero in party)
{
    hero.ShowStatus();
    int dmg = hero.Attack();
    Console.WriteLine($"  حمله: {dmg} آسیب");
}

party[0].TakeDamage(30);
party[1].TakeDamage(20);
