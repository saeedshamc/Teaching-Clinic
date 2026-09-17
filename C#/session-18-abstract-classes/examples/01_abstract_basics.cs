// ============================================================
// جلسه ۱۸ — Abstract Class: مبانی
// فایل: 01_abstract_basics.cs
// ============================================================

abstract class Animal
{
    public string Name { get; set; } = "";
    public int Age { get; set; }

    // abstract — فرزند باید پیاده کند
    public abstract void MakeSound();

    // virtual — پیاده‌سازی پیش‌فرض
    public virtual void Introduce() =>
        Console.WriteLine($"من {Name} هستم، {Age} سال دارم.");

    // concrete — ارث‌بری مستقیم
    public void Eat() => Console.WriteLine($"{Name} غذا می‌خورد.");
}

class Dog : Animal
{
    public string Breed { get; set; } = "";
    public override void MakeSound() => Console.WriteLine($"{Name}: ووف ووف!");
    public override void Introduce()
    {
        base.Introduce();
        Console.WriteLine($"  نژاد: {Breed}");
    }
}

class Cat : Animal
{
    public override void MakeSound() => Console.WriteLine($"{Name}: میو میو!");
}

Animal[] pets =
{
    new Dog { Name = "رکسی", Age = 3, Breed = "هاسکی" },
    new Cat { Name = "میمی", Age = 2 }
};

foreach (var pet in pets)
{
    pet.Introduce();
    pet.MakeSound();
    pet.Eat();
    Console.WriteLine();
}
