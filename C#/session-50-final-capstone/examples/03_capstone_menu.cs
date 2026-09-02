// ============================================================
// جلسه 50 — پروژه نهایی
// فایل: 03_capstone_menu.cs
// ============================================================
var products = new Dictionary<int, string> { [1] = "Book" };
Console.WriteLine("1.List 2.Add");
var op = Console.ReadLine();
if (op == "1") foreach (var p in products) Console.WriteLine($"{p.Key}:{p.Value}");
