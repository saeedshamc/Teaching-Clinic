// ============================================================
// جلسه ۱۶ — Polymorphism: مبانی
// فایل: 01_polymorphism_basics.cs
// ============================================================

class Animal
{
    public string Name { get; set; } = "";
    public virtual void Speak() => Console.WriteLine($"{Name}: ...");
}

class Dog : Animal
{
    public override void Speak() => Console.WriteLine($"{Name}: ووف ووف!");
}

class Cat : Animal
{
    public override void Speak() => Console.WriteLine($"{Name}: میو میو!");
}

class Bird : Animal
{
    public override void Speak() => Console.WriteLine($"{Name}: چیچیچی!");
}

// Polymorphism: Animal[] اما رفتار واقعی هر شیء
Animal[] zoo =
{
    new Dog { Name = "رکسی" },
    new Cat { Name = "میمی" },
    new Bird { Name = "پرنیان" },
    new Dog { Name = "ماکس" }
};

Console.WriteLine("=== باغ‌وحش ===");
foreach (Animal animal in zoo)
    animal.Speak();  // Late binding — نوع runtime تعیین می‌کند

// جمع‌آوری بر اساس نوع
int dogCount = zoo.Count(a => a is Dog);
Console.WriteLine($"\nتعداد سگ: {dogCount}");
