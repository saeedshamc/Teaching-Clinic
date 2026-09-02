# تمرین‌های جلسه ۳۳ — Unit Testing

## کلاسی (۳۰ دقیقه)

1. `Calculator` با Add, Subtract, Multiply, Divide + tests
2. `[Theory]` برای `IsPrime(n)` — n=2,3,4,9,11
3. test exception: Divide by zero

## خانه (۴۵ دقیقه)

4. پروژه xunit واقعی بسازید و `dotnet test` اجرا کنید
5. `StudentService` از جلسه ۳۰ — 5 test
6. Fake repository برای test isolation

## Setup

```powershell
dotnet new xunit -n MyApp.Tests
dotnet add MyApp.Tests reference MyApp.Core
dotnet test
```

پاسخ نمونه: [exercise_01.cs](./exercise_01.cs)
