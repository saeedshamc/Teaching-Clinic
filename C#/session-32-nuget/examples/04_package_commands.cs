// ============================================================
// جلسه ۳۲ — NuGet: دستورات CLI
// ============================================================

var commands = new Dictionary<string, string>
{
    ["dotnet new console -n App"] = "ساخت پروژه console",
    ["dotnet add package Serilog"] = "نصب package",
    ["dotnet add package xunit --version 2.9.0"] = "نصب با version مشخص",
    ["dotnet list package"] = "لیست packages مستقیم",
    ["dotnet list package --include-transitive"] = "شامل وابستگی‌های غیرمستقیم",
    ["dotnet list package --outdated"] = "packages قدیمی",
    ["dotnet remove package Serilog"] = "حذف package",
    ["dotnet restore"] = "دانلود packages",
    ["dotnet nuget locals all --list"] = "مسیر cache",
    ["dotnet pack"] = "ساخت nupkg از classlib"
};

Console.WriteLine("=== دستورات NuGet/dotnet ===\n");
foreach (var (cmd, desc) in commands)
    Console.WriteLine($"  {cmd,-50} # {desc}");

Console.WriteLine("\n=== workflow نصب ===");
Console.WriteLine("1. dotnet new console -n MyShop");
Console.WriteLine("2. cd MyShop");
Console.WriteLine("3. dotnet add package FluentValidation");
Console.WriteLine("4. using FluentValidation; در کد");
Console.WriteLine("5. dotnet build");

Console.WriteLine("\n=== packages.config (قدیمی) vs PackageReference ===");
Console.WriteLine("PackageReference در csproj — روش مدرن (.NET Core+)");
