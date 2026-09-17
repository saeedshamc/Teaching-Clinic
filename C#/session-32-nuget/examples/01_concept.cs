// ============================================================
// جلسه ۳۲ — NuGet: مفهوم package
// ============================================================

// NuGet = مدیریت کتابخانه‌های خارجی برای .NET
// بدون NuGet باید DLLها را دستی دانلود و reference کنید

// مثال: اگر Newtonsoft.Json نصب باشد:
// dotnet add package Newtonsoft.Json
// using Newtonsoft.Json;
// string json = JsonConvert.SerializeObject(obj);

// در این مثال بدون package خارجی — فقط System.Text.Json (built-in)
using System.Text.Json;

var data = new { Name = "NuGet Demo", Version = "1.0" };
string json = JsonSerializer.Serialize(data);
Console.WriteLine("Built-in JSON (بدون NuGet):");
Console.WriteLine(json);

Console.WriteLine("\nPackageهای رایج که با NuGet نصب می‌شوند:");
Console.WriteLine("  - xunit (تست)");
Console.WriteLine("  - Serilog (لاگ)");
Console.WriteLine("  - EF Core (دیتابیس)");
Console.WriteLine("  - FluentValidation");

Console.WriteLine("\nنصب: dotnet add package <PackageName>");
