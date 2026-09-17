// ============================================================
// جلسه ۳۲ — NuGet: ساختار csproj (مفهومی)
// ============================================================

// محتوای نمونه MyApp.csproj پس از dotnet add package:

const string sampleCsproj = """
<Project Sdk="Microsoft.NET.Sdk">
  <PropertyGroup>
    <OutputType>Exe</OutputType>
    <TargetFramework>net8.0</TargetFramework>
    <ImplicitUsings>enable</ImplicitUsings>
    <Nullable>enable</Nullable>
  </PropertyGroup>

  <ItemGroup>
    <PackageReference Include="Serilog" Version="4.0.0" />
    <PackageReference Include="Serilog.Sinks.Console" Version="6.0.0" />
    <PackageReference Include="xunit" Version="2.9.0" />
  </ItemGroup>

  <ItemGroup>
    <ProjectReference Include="..\MyLib\MyLib.csproj" />
  </ItemGroup>
</Project>
""";

Console.WriteLine("=== نمونه csproj ===");
Console.WriteLine(sampleCsproj);

Console.WriteLine("=== PackageReference vs ProjectReference ===");
Console.WriteLine("PackageReference → NuGet package (DLL از nuget.org)");
Console.WriteLine("ProjectReference → پروژه دیگر در solution");

Console.WriteLine("\n=== دستورات ===");
Console.WriteLine("dotnet add package Serilog");
Console.WriteLine("dotnet add reference ../MyLib/MyLib.csproj");
Console.WriteLine("dotnet list package");
Console.WriteLine("dotnet restore");
