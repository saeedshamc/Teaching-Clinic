# جلسه ۳۲: NuGet Package Manager — ۱.۵ ساعت

## 🎯 اهداف یادگیری

- درک NuGet به عنوان package manager اکوسیستم .NET
- نصب package با dotnet CLI و Visual Studio
- مدیریت versions و dependencies
- خواندن `.csproj` و `PackageReference`
- packageهای پرکاربرد: Serilog، xUnit، EF Core، Newtonsoft
- restore، update و security considerations

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | NuGet چیست؟ |
| 10–25 | dotnet add package |
| 25–40 | csproj و versions |
| 40–55 | packageهای محبوب |
| 55–70 | restore و lock |
| 70–85 | تمرین |
| 85–90 | جمع‌بندی + تکلیف |

---

## ۱. NuGet چیست؟

**NuGet** = مخزن packages برای .NET:

- کتابخانه‌های آماده (logging، ORM، testing)
- ابزارها (analyzers، source generators)
- مدیریت وابستگی transitive

سایت: [nuget.org](https://www.nuget.org)

---

## ۲. نصب Package — CLI

```powershell
dotnet new console -n MyApp
cd MyApp

dotnet add package Newtonsoft.Json
dotnet add package Serilog
dotnet add package Serilog.Sinks.Console

dotnet restore
dotnet build
```

---

## ۳. PackageReference در csproj

```xml
<Project Sdk="Microsoft.NET.Sdk">
  <PropertyGroup>
    <OutputType>Exe</OutputType>
    <TargetFramework>net8.0</TargetFramework>
  </PropertyGroup>

  <ItemGroup>
    <PackageReference Include="Newtonsoft.Json" Version="13.0.3" />
    <PackageReference Include="Serilog" Version="4.0.0" />
  </ItemGroup>
</Project>
```

---

## ۴. Version Specifiers

| Syntax | معنی |
|--------|------|
| `13.0.3` | exact version |
| `13.0.*` | latest patch |
| `[13.0,14.0)` | range |
| `*` | latest (خطرناک در production) |

**توصیه:** version ثابت در production.

---

## ۵. Packageهای پرکاربرد

| Package | کاربرد |
|---------|--------|
| `Microsoft.EntityFrameworkCore.SqlServer` | ORM |
| `xunit` + `Microsoft.NET.Test.Sdk` | Unit Test |
| `Serilog.AspNetCore` | Logging |
| `Microsoft.Extensions.DependencyInjection` | DI |
| `FluentValidation` | Validation |
| `AutoMapper` | Object mapping |
| `Polly` | Resilience |
| `Swashbuckle.AspNetCore` | Swagger |

---

## ۶. Transitive Dependencies

وقتی Package A به B وابسته است، B خودکار نصب می‌شود:

```
MyApp → Serilog.AspNetCore → Serilog → ...
```

```powershell
dotnet list package --include-transitive
```

---

## ۷. Restore

```powershell
dotnet restore          # دانلود packages
dotnet nuget locals all --list   # مسیر cache
dotnet nuget locals all --clear  # پاک cache
```

---

## ۸. Update

```powershell
dotnet list package --outdated
dotnet add package Serilog --version 4.1.0
dotnet outdated   # با tool global
```

---

## ۹. Private Feed

```xml
<PackageSource>
  <add key="nuget.org" value="https://api.nuget.org/v3/index.json" />
  <add key="company" value="https://nuget.company.com/v3/index.json" />
</PackageSource>
```

---

## ۱۰. Central Package Management

`Directory.Packages.props`:

```xml
<Project>
  <ItemGroup>
    <PackageVersion Include="Serilog" Version="4.0.0" />
    <PackageVersion Include="xunit" Version="2.9.0" />
  </ItemGroup>
</Project>
```

---

## ۱۱. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_concept.cs](./examples/01_concept.cs) | مفهوم package |
| [02_practice.cs](./examples/02_practice.cs) | شبیه‌سازی استفاده |
| [03_real_world.cs](./examples/03_real_world.cs) | csproj مفهومی |
| [04_package_commands.cs](./examples/04_package_commands.cs) | دستورات CLI |

---

## ۱۲. امنیت

- packageهای معتبر از nuget.org
- بررسی download count و maintainer
- `dotnet list package --vulnerable`
- pin versions در CI/CD

---

## ۱۳. ایجاد Package خودتان

```powershell
dotnet new classlib -n MyLibrary
dotnet pack
# خروجی: MyLibrary.1.0.0.nupkg
```

---

## ۱۴. اشتباهات رایج

| اشتباه | مشکل |
|--------|------|
| version * | breaking changes |
| package duplicate | conflict |
| فراموش restore در CI | build fail |
| package unused | bloat |
| mixing JSON libraries | confusion |

---

## ۱۵. تمرین کلاسی

1. پروژه console + `Humanizer` package
2. `dotnet list package`
3. csproj را بخوانید و PackageReference پیدا کنید

**تکلیف:** [question.md](./exercises/question.md)

---

## ۱۶. NuGet vs npm vs pip

| | NuGet | npm | pip |
|---|-------|-----|-----|
| اکوسیستم | .NET | Node | Python |
| فایل | csproj | package.json | requirements.txt |
| مخزن | nuget.org | npmjs.com | pypi.org |

---

## 📚 مطالعه تکمیلی

- [NuGet documentation](https://learn.microsoft.com/nuget/)
- [dotnet add package](https://learn.microsoft.com/dotnet/core/tools/dotnet-add-package)

---

**جلسه قبل:** [۳۱ — Namespaces](../session-31-namespaces-assemblies/) | **بعد:** [۳۳ — Unit Testing](../session-33-unit-testing/)
