// ============================================================
// پاسخ نمونه — تمرین جلسه ۳۲ (مفهومی)
// ============================================================

// پس از: dotnet add package Humanizer
// using Humanizer;
// "hello world".Titleize() → "Hello World"

// شبیه‌سازی Titleize بدون package:
string Titleize(string input) =>
    string.Join(" ", input.Split(' ')
        .Select(w => char.ToUpper(w[0]) + w[1..].ToLower()));

Console.WriteLine(Titleize("hello world"));
Console.WriteLine(Titleize("nuget package manager"));

Console.WriteLine("\nPackageReference در csproj:");
Console.WriteLine('<PackageReference Include="Humanizer" Version="2.14.1" />');
