// تمرین ۱–۳

class Animal { public string Name { get; set; } = ""; public virtual void Speak() { } }
class Dog : Animal { public override void Speak() => Console.WriteLine($"{Name}: ووف!"); }
class Cat : Animal { public override void Speak() => Console.WriteLine($"{Name}: میو!"); }
class Bird : Animal { public override void Speak() => Console.WriteLine($"{Name}: چیچی!"); }

class Shape { public virtual double GetArea() => 0; }
class Circle : Shape { public double R; public override double GetArea() => Math.PI * R * R; }
class Rectangle : Shape { public double W, H; public override double GetArea() => W * H; }

class Employee { public string Name { get; set; } = ""; public virtual double GetSalary() => 0; }
class FullTime : Employee { public double Salary; public override double GetSalary() => Salary; }
class PartTime : Employee { public double Rate; public int Hours; public override double GetSalary() => Rate * Hours; }

Animal[] zoo = { new Dog { Name = "رکسی" }, new Cat { Name = "میمی" }, new Dog { Name = "ماکس" }, new Bird { Name = "پرنیان" } };
foreach (var a in zoo) a.Speak();
Console.WriteLine($"سگ‌ها: {zoo.Count(a => a is Dog)}");

Shape[] shapes = { new Circle { R = 3 }, new Rectangle { W = 4, H = 5 }, new Circle { R = 2 } };
Console.WriteLine($"جمع مساحت: {shapes.Sum(s => s.GetArea()):F2}");
Console.WriteLine($"بزرگترین: {shapes.Max(s => s.GetArea()):F2}");

Employee[] staff = { new FullTime { Name = "علی", Salary = 15_000_000 }, new PartTime { Name = "مریم", Rate = 200_000, Hours = 60 } };
Console.WriteLine($"جمع حقوق: {staff.Sum(e => e.GetSalary()):N0}");
