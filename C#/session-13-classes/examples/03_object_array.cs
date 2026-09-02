// ============================================================
// جلسه 13 — کلاس و شیء
// فایل: 03_object_array.cs
// ============================================================
class Book { public string Title = ""; }
var books = new Book[] { new() { Title = "C#" }, new() { Title = "LINQ" } };
foreach (var b in books) Console.WriteLine(b.Title);
