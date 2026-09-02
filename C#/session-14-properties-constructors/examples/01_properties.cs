// ============================================================
// جلسه 14 — Property و Constructor
// فایل: 01_properties.cs
// ============================================================
class User
{
    public string Name { get; set; } = "";
    public int Age { get; private set; }
    public void SetAge(int age) => Age = age >= 0 ? age : 0;
}
var u = new User { Name = "Sara" }; u.SetAge(20);
Console.WriteLine($"{u.Name} {u.Age}");
