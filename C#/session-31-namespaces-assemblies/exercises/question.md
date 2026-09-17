# تمرین‌های جلسه ۳۱ — Namespaces و Assemblies

## کلاسی (۳۰ دقیقه)

1. سه namespace بسازید: `Models`, `Services`, `Helpers`
2. class `StringHelper` در Helpers با متد `Reverse`
3. alias برای دو class هم‌نام در namespace مختلف

## خانه (۴۵ دقیقه)

4. solution دو پروژه‌ای: `Library` (classlib) + `App` (console)
5. `internal` class در Library — از App قابل دسترسی نیست
6. `GlobalUsings.cs` با 5 using پرکاربرد

## dotnet CLI

```powershell
dotnet new sln -n MySolution
dotnet new classlib -n MyLib
dotnet new console -n MyApp
dotnet sln add MyLib MyApp
dotnet add MyApp reference MyLib
```

پاسخ نمونه: [exercise_01.cs](./exercise_01.cs)
