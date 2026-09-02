// ============================================================
// جلسه ۳۳ — Unit Testing: ساختار پروژه xUnit
// ============================================================

Console.WriteLine("=== ساختار پروژه Test ===");
Console.WriteLine("""
MySolution/
  src/
    MyApp.Core/
      Calculator.cs
      StudentService.cs
  tests/
    MyApp.Tests/
      CalculatorTests.cs
      StudentServiceTests.cs
""");

Console.WriteLine("=== دستورات ===");
Console.WriteLine("dotnet new xunit -n MyApp.Tests");
Console.WriteLine("dotnet add MyApp.Tests reference MyApp.Core");
Console.WriteLine("dotnet test");
Console.WriteLine("dotnet test --filter \"FullyQualifiedName~Calculator\"");

Console.WriteLine("\n=== نمونه xUnit Test ===");
const string sampleTest = """
[Fact]
public void Add_ReturnsSum()
{
    var calc = new Calculator();
    Assert.Equal(5, calc.Add(2, 3));
}

[Theory]
[InlineData(2, true)]
[InlineData(3, false)]
public void IsEven_Works(int n, bool expected)
{
    Assert.Equal(expected, MathUtils.IsEven(n));
}
""";
Console.WriteLine(sampleTest);

Console.WriteLine("=== Naming: Method_Scenario_Expected ===");
