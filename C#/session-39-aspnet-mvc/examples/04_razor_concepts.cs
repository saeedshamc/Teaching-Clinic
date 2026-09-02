// ============================================================
// جلسه ۳۹ — ASP.NET MVC
// فایل: 04_razor_concepts.cs — مفاهیم Razor (شبیه‌سازی)
// ============================================================

record Product(int Id, string Name, decimal Price);

// Razor View Engine — رندر HTML از template
string RenderProductList(IEnumerable<Product> products, string? query = null)
{
    var lines = new List<string>
    {
        "<!DOCTYPE html>",
        "<html><body>",
        "<h1>لیست محصولات</h1>"
    };

    if (!string.IsNullOrEmpty(query))
        lines.Add($"<p>نتایج جستجو برای: <strong>{query}</strong></p>");

    lines.Add("<table border='1'>");
    lines.Add("<tr><th>Id</th><th>نام</th><th>قیمت</th></tr>");

    foreach (var p in products)
    {
        lines.Add($"  <tr>");
        lines.Add($"    <td>{p.Id}</td>");
        lines.Add($"    <td>{p.Name}</td>");
        lines.Add($"    <td>{p.Price:N0}</td>");
        lines.Add($"    <td><a href='/Product/Details/{p.Id}'>جزئیات</a></td>");
        lines.Add($"  </tr>");
    }

    lines.Add("</table></body></html>");
    return string.Join("\n", lines);
}

var products = new List<Product>
{
    new(1, "لپ‌تاپ", 25_000_000),
    new(2, "ماوس", 450_000),
    new(3, "کیبورد", 1_200_000)
};

Console.WriteLine("=== Razor: Index View ===");
Console.WriteLine(RenderProductList(products));

Console.WriteLine("\n=== Razor: Search View (q=ما) ===");
var filtered = products.Where(p => p.Name.Contains("ما")).ToList();
Console.WriteLine(RenderProductList(filtered, query: "ما"));

// Tag Helper شبیه‌سازی
Console.WriteLine("\n=== Tag Helper: asp-action ===");
Console.WriteLine("<form asp-action=\"Create\" method=\"post\">");
Console.WriteLine("  <input asp-for=\"Name\" />");
Console.WriteLine("  <span asp-validation-for=\"Name\"></span>");
Console.WriteLine("</form>");
