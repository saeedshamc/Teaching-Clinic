// تمرین ۱–۳

var orders = new[] { new { Id = 1, CustomerId = 1, Amount = 500_000 }, new { Id = 2, CustomerId = 2, Amount = 1_200_000 } };
var customers = new[] { new { Id = 1, Name = "علی" }, new { Id = 2, Name = "مریم" } };

var joined = orders.Join(customers, o => o.CustomerId, c => c.Id, (o, c) => new { c.Name, o.Amount });
foreach (var j in joined) Console.WriteLine($"{j.Name}: {j.Amount:N0}");

var products = Enumerable.Range(1, 50).Select(i => $"محصول {i}").ToList();
int page = 2, pageSize = 10;
var page2 = products.Skip((page - 1) * pageSize).Take(pageSize);
Console.WriteLine($"\nصفحه 2: {string.Join(", ", page2)}");

var listA = new[] { "علی", "مریم", "رضا" };
var listB = new[] { "مریم", "سارا", "رضا" };
Console.WriteLine($"مشترک: {string.Join(", ", listA.Intersect(listB))}");
Console.WriteLine($"فقط A: {string.Join(", ", listA.Except(listB))}");
