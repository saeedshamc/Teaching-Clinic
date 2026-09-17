# جلسه ۳۳: Unit Testing (xUnit) — ۱.۵ ساعت

## 🎯 اهداف یادگیری

- درک اهمیت unit test و TDD (مقدمه)
- راه‌اندازی پروژه test با xUnit
- نوشتن test با `[Fact]` و `[Theory]`
- Assertهای رایج: Equal، True، Throws
- Arrange-Act-Assert (AAA) pattern
- Mock مفهومی (بدون framework پیچیده)
- testability با dependency injection

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | چرا unit test؟ |
| 10–25 | setup xUnit |
| 25–40 | Fact و Assert |
| 40–55 | Theory و InlineData |
| 55–70 | test service layer |
| 70–85 | تمرین |
| 85–90 | جمع‌بندی + تکلیف |

---

## ۱. چرا Unit Test؟

- **Regression:** باگ قدیمی برنگردد
- **Refactoring:** با اطمینان کد را تغییر دهید
- **Documentation:** test = spec اجرایی
- **Design:** کد testable = loosely coupled

```
Code → Test → Refactor → Repeat
```

---

## ۲. ساخت پروژه Test

```powershell
dotnet new classlib -n MyApp.Core
dotnet new xunit -n MyApp.Tests
dotnet add MyApp.Tests reference MyApp.Core
dotnet add MyApp.Tests package Microsoft.NET.Test.Sdk
dotnet test
```

---

## ۳. ساختار AAA

```csharp
[Fact]
public void Add_TwoNumbers_ReturnsSum()
{
    // Arrange
    var calculator = new Calculator();

    // Act
    int result = calculator.Add(2, 3);

    // Assert
    Assert.Equal(5, result);
}
```

---

## ۴. Assertهای رایج

| Assert | کاربرد |
|--------|--------|
| `Equal(expected, actual)` | equality |
| `NotEqual` | inequality |
| `True` / `False` | boolean |
| `Null` / `NotNull` | null check |
| `Contains` | collection/string |
| `Throws<T>` | exception expected |
| `Empty` | collection empty |

---

## ۵. Theory — parameterized tests

```csharp
[Theory]
[InlineData(0, 0, 0)]
[InlineData(1, 2, 3)]
[InlineData(-1, 1, 0)]
public void Add_VariousInputs_ReturnsExpected(int a, int b, int expected)
{
    var calc = new Calculator();
    Assert.Equal(expected, calc.Add(a, b));
}
```

---

## ۶. Test Naming Convention

```
MethodName_Scenario_ExpectedResult
```

مثال: `Register_InvalidGpa_ThrowsArgumentException`

---

## ۷. Test Service با Mock ساده

```csharp
public class FakeRepository : IStudentRepository
{
    public List<Student> Data { get; } = new();
    public Task<List<Student>> GetAllAsync() => Task.FromResult(Data);
    // ...
}

[Fact]
public async Task AverageGpa_WithTwoStudents_ReturnsCorrectAverage()
{
    var repo = new FakeRepository();
    repo.Data.Add(new Student(1, "A", "SE", 18));
    repo.Data.Add(new Student(2, "B", "SE", 16));
    var service = new StudentService(repo);

    double avg = await service.AverageGpaAsync();

    Assert.Equal(17, avg);
}
```

---

## ۸. Exception Testing

```csharp
[Fact]
public void Divide_ByZero_Throws()
{
    var calc = new Calculator();
    Assert.Throws<DivideByZeroException>(() => calc.Divide(10, 0));
}
```

---

## ۹. Async Tests

```csharp
[Fact]
public async Task LoadAsync_FileExists_ReturnsData()
{
    var repo = new JsonRepository("test.json");
    var data = await repo.LoadAsync();
    Assert.NotNull(data);
}
```

---

## ۱۰. Coverage (مقدمه)

```powershell
dotnet add package coverlet.collector
dotnet test --collect:"XPlat Code Coverage"
```

هدف: 70–80% برای business logic (نه 100% blind).

---

## ۱۱. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_concept.cs](./examples/01_concept.cs) | AAA و assert ساده |
| [02_practice.cs](./examples/02_practice.cs) | Theory |
| [03_real_world.cs](./examples/03_real_world.cs) | test service |
| [04_test_runner_sim.cs](./examples/04_test_runner_sim.cs) | شبیه‌سازی runner |

---

## ۱۲. Test Pyramid

```
        / E2E \
       / Integration \
      /   Unit Tests   \
```

Unit test = پایه — سریع، isolated.

---

## ۱۳. Anti-Patterns

| Anti-Pattern | مشکل |
|--------------|------|
| test implementation | brittle |
| test private method | over-coupling |
| no assert | useless test |
| test database واقعی | slow, flaky |
| test نامفهوم | maintenance hell |

---

## ۱۴. xUnit vs NUnit vs MSTest

| | xUnit | NUnit | MSTest |
|---|-------|-------|--------|
| popularity | بالا | متوسط | Microsoft |
| parallel | default | config | config |
| ASP.NET default | ✅ | | |

---

## ۱۵. تمرین کلاسی

1. Calculator با 4 متد + 8 test
2. Theory برای `IsEven`
3. test exception برای validation

**تکلیف:** [question.md](./exercises/question.md)

---

## ۱۶. CI Integration

```yaml
# GitHub Actions
- run: dotnet test --no-build --verbosity normal
```

---

## 📚 مطالعه تکمیلی

- [xUnit documentation](https://xunit.net/)
- [Unit testing best practices](https://learn.microsoft.com/dotnet/core/testing/unit-testing-best-practices)

---

**جلسه قبل:** [۳۲ — NuGet](../session-32-nuget/) | **بعد:** [۳۴ — Logging](../session-34-logging/)
