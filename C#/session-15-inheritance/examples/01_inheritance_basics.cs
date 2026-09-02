// ============================================================
// جلسه ۱۵ — وراثت: مبانی
// فایل: 01_inheritance_basics.cs
// ============================================================

class Animal
{
    public string Name { get; set; } = "";
    public int Age { get; set; }

    public void Eat() => Console.WriteLine($"{Name} غذا می‌خورد.");
    public void Sleep() => Console.WriteLine($"{Name} می‌خوابد.");
}

class Dog : Animal
{
    public string Breed { get; set; } = "";

    public void Bark() => Console.WriteLine($"{Name}: ووف ووف! ({Breed})");
}

class Cat : Animal
{
    public bool IsIndoor { get; set; }

    public void Meow() => Console.WriteLine($"{Name}: میو میو!");
}

Dog rex = new Dog { Name = "رکسی", Age = 3, Breed = "هاسکی" };
Cat mimi = new Cat { Name = "میمی", Age = 2, IsIndoor = true };

rex.Eat();    // از Animal
rex.Bark();   // مخصوص Dog
mimi.Meow();

// Dog IS-A Animal — می‌توان به Animal ارجاع داد
Animal animal = rex;
animal.Eat();
