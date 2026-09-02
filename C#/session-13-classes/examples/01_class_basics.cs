// ============================================================
// جلسه 13 — کلاس و شیء
// فایل: 01_class_basics.cs
// ============================================================
class Person
{
    public string Name = "";
    public void SayHello() => Console.WriteLine($"Hi {Name}");
}
var p = new Person { Name = "Ali" };
p.SayHello();
