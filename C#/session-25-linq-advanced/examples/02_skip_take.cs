// ============================================================
// جلسه ۲۵ — Skip و Take (Pagination)
// فایل: 02_skip_take.cs
// ============================================================

var allProducts = Enumerable.Range(1, 50)
    .Select(i => new { Id = i, Name = $"محصول {i}", Price = i * 100_000 })
    .ToList();

// Pagination
int pageSize = 10;

void ShowPage(int page)
{
    var pageData = allProducts
        .Skip((page - 1) * pageSize)
        .Take(pageSize);

    Console.WriteLine($"\n=== صفحه {page} (از {(allProducts.Count + pageSize - 1) / pageSize}) ===");
    foreach (var p in pageData)
        Console.WriteLine($"  #{p.Id}: {p.Name} — {p.Price:N0}");
}

ShowPage(1);
ShowPage(3);
ShowPage(5);

// TakeWhile / SkipWhile
var numbers = new[] { 1, 2, 3, 4, 5, 4, 3, 2, 1 };
Console.WriteLine($"\nTakeWhile < 4: {string.Join(", ", numbers.TakeWhile(n => n < 4))}");
Console.WriteLine($"SkipWhile < 4: {string.Join(", ", numbers.SkipWhile(n => n < 4))}");

// Top N
var top5 = allProducts.OrderByDescending(p => p.Price).Take(5);
Console.WriteLine("\n=== 5 گران‌ترین ===");
foreach (var p in top5)
    Console.WriteLine($"  {p.Name}: {p.Price:N0}");

// Chunk (.NET 6+)
Console.WriteLine("\n=== Chunk (3تایی) ===");
foreach (var chunk in allProducts.Take(9).Chunk(3))
    Console.WriteLine($"  [{string.Join(", ", chunk.Select(p => p.Id))}]");
