# جلسه ۳۱: Namespaces و Assemblies — ۱.۵ ساعت

## 🎯 اهداف یادگیری

- درک namespace برای سازماندهی کد
- تفاوت namespace، assembly و project
- using directives و alias
- file-scoped namespace (C# 10+)
- global using
- ساختار solution چند پروژه‌ای (مفهومی)
- internal vs public visibility

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | چرا namespace؟ |
| 10–25 | تعریف و استفاده |
| 25–40 | using و alias |
| 40–55 | assembly و DLL |
| 55–70 | multi-project solution |
| 70–85 | تمرین |
| 85–90 | جمع‌بندی + تکلیف |

---

## ۱. Namespace چیست؟

**Namespace** = فضای نام منطقی برای جلوگیری از تداخل اسم class:

```csharp
namespace MyApp.Models
{
    public class User { }
}

namespace MyApp.Services
{
    public class UserService { }
}
```

---

## ۲. File-scoped Namespace (C# 10+)

```csharp
namespace MyApp.Models;  // بدون آکولاد

public class Product { }
public class Category { }
```

---

## ۳. Using Directives

```csharp
using System;
using System.Collections.Generic;
using System.Linq;
using MyApp.Models;
using MyApp.Services;

// یا global using در فایل جدا:
// global using System;
```

---

## ۴. Alias

```csharp
using Text = System.Text;
using ModelUser = MyApp.Models.User;

var user = new ModelUser();
```

---

## ۵. Assembly چیست؟

| مفهوم | توضیح |
|--------|--------|
| **Project** | واحد build در Visual Studio / dotnet CLI |
| **Assembly** | خروجی compile — `.dll` یا `.exe` |
| **Namespace** | سازماندهی منطقی داخل assembly |

یک assembly می‌تواند چند namespace داشته باشد.
یک namespace می‌تواند در چند assembly باشد.

---

## ۶. Reference بین پروژه‌ها

```
Solution/
  MyApp.Core/        → MyApp.Core.dll
  MyApp.Infrastructure/
  MyApp.Console/     → references Core
```

```xml
<!-- MyApp.Console.csproj -->
<ItemGroup>
  <ProjectReference Include="..\MyApp.Core\MyApp.Core.csproj" />
</ItemGroup>
```

---

## ۷. Access Modifiers

| Modifier | سطح |
|----------|------|
| `public` | همه assemblyها |
| `internal` | فقط همین assembly |
| `private` | فقط همین class |
| `protected` | subclass |

```csharp
[assembly: InternalsVisibleTo("MyApp.Tests")]
```

---

## ۸. ساختار پیشنهادی

```
CompanyName.ProjectName/
  Domain/
    Entities/
    Interfaces/
  Application/
    Services/
    DTOs/
  Infrastructure/
    Repositories/
  Presentation/
    Program.cs
```

---

## ۹. Root Namespace

در `.csproj`:

```xml
<PropertyGroup>
  <RootNamespace>MyCompany.MyApp</RootNamespace>
</PropertyGroup>
```

---

## ۱۰. Global Usings

فایل `GlobalUsings.cs`:

```csharp
global using System;
global using System.Collections.Generic;
global using System.Linq;
global using System.Threading.Tasks;
```

---

## ۱۱. Conflict Resolution

```csharp
// دو class با نام User در namespaceهای مختلف
using AdminUser = MyApp.Admin.User;
using PublicUser = MyApp.Public.User;
```

---

## ۱۲. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_concept.cs](./examples/01_concept.cs) | namespace پایه |
| [02_practice.cs](./examples/02_practice.cs) | using و alias |
| [03_real_world.cs](./examples/03_real_world.cs) | ساختار لایه‌ای |
| [04_assembly_concept.cs](./examples/04_assembly_concept.cs) | assembly مفهومی |

---

## ۱۳. dotnet CLI — multi-project

```powershell
dotnet new sln -n MySolution
dotnet new classlib -n MyApp.Core
dotnet new console -n MyApp.Console
dotnet sln add MyApp.Core MyApp.Console
dotnet add MyApp.Console reference MyApp.Core
dotnet build
```

---

## ۱۴. Metadata Assembly

```csharp
Console.WriteLine(typeof(string).Assembly.FullName);
// System.Private.CoreLib, Version=...
```

---

## ۱۵. اشتباهات رایج

| اشتباه | مشکل |
|--------|------|
| namespace نامناسب | `ClassLibrary1` |
| همه در یک namespace | شلوغی |
| circular reference | build fail |
| public everything | API surface بزرگ |
| فراموش ProjectReference | type not found |

---

## ۱۶. Naming Convention

```
Company.Product.Layer
// Acme.Shop.Domain
// Acme.Shop.Application
// Acme.Shop.Infrastructure
```

---

## ۱۷. تمرین کلاسی

1. سه namespace: Models, Services, Utils
2. alias برای class تکراری
3. file-scoped namespace

**تکلیف:** [question.md](./exercises/question.md)

---

## 📚 مطالعه تکمیلی

- [Namespaces (C#)](https://learn.microsoft.com/dotnet/csharp/language-reference/keywords/namespace)
- [Assemblies in .NET](https://learn.microsoft.com/dotnet/standard/assembly/)

---

**جلسه قبل:** [۳۰ — پروژه پیشرفته](../session-30-advanced-project/) | **بعد:** [۳۲ — NuGet](../session-32-nuget/)
