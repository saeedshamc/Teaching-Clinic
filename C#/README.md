# دوره آموزشی کامل C# — از صفر تا حرفه‌ای

> تمام توضیحات به زبان فارسی نوشته شده‌اند.  
> این دوره شامل **۵۰ جلسه** پیشرونده، مثال‌های قابل اجرا و تمرین عملی است.

---

## معرفی دوره

C# (سی‌شارپ) زبان اصلی اکوسیستم **.NET** است و برای ساخت اپلیکیشن‌های دسکتاپ، وب، API، بازی (Unity) و سرویس‌های ابری استفاده می‌شود. این دوره شما را از اولین برنامه `Console` تا مفاهیم حرفه‌ای ASP.NET Core و معماری نرم‌افزار جلو می‌برد.

---

## مخاطب هدف

- مبتدیان مطلق برنامه‌نویسی
- توسعه‌دهندگانی که از JavaScript/Python/Java می‌آیند
- دانشجویان و جویندگان کار در حوزه .NET

---

## پیش‌نیازها

| ابزار | نسخه پیشنهادی | توضیح |
|-------|----------------|-------|
| .NET SDK | 8.0 یا بالاتر | [dotnet.microsoft.com/download](https://dotnet.microsoft.com/download) |
| VS Code یا Visual Studio | آخرین نسخه | ویرایشگر/IDE |
| C# Dev Kit (VS Code) | اختیاری ولی مفید | IntelliSense بهتر |

### تأیید نصب

```bash
dotnet --version
dotnet --list-sdks
```

---

## ساختار هر جلسه

```
session-NN-slug/
├── README.md              توضیح کامل جلسه
├── examples/
│   ├── 01_*.cs            مثال‌های قابل اجرا
│   └── 02_*.cs
└── exercises/
    ├── question.md        صورت تمرین
    └── exercise_01.cs       پاسخ/راهنمای حل
```

---

## نحوه اجرای مثال‌ها

### روش ۱: اسکریپت کمکی (پیشنهادی)

```powershell
# ویندوز (PowerShell)
cd C#
./run-example.ps1 session-01-intro/examples/01_hello_world.cs
```

```bash
# لینوکس/macOS
chmod +x run-example.sh
./run-example.sh session-01-intro/examples/01_hello_world.cs
```

### روش ۲: پروژه Console دستی

```bash
dotnet new console -n Playground
cd Playground
# محتوای examples/01_hello_world.cs را داخل Program.cs کپی کنید
dotnet run
```

---

## فهرست جلسات

### بخش اول: مبانی (۱–۱۲)

| # | عنوان | موضوعات |
|---|--------|---------|
| [۱](./session-01-intro/) | مقدمه و اولین برنامه | C#، .NET، SDK، Hello World |
| [۲](./session-02-variables-types/) | متغیرها و انواع داده | int, string, bool, var |
| [۳](./session-03-operators/) | عملگرها | حسابی، مقایسه‌ای، منطقی |
| [۴](./session-04-control-flow/) | شرط‌ها | if/else, switch |
| [۵](./session-05-loops/) | حلقه‌ها | for, while, foreach |
| [۶](./session-06-methods/) | متدها | پارامتر، return، overload |
| [۷](./session-07-arrays/) | آرایه‌ها | یک‌بعدی، چندبعدی |
| [۸](./session-08-strings/) | رشته‌ها | interpolation، متدها |
| [۹](./session-09-null-nullable/) | Null و Nullable | `?`, null-coalescing |
| [۱۰](./session-10-exceptions/) | مدیریت خطا | try/catch/finally |
| [۱۱](./session-11-collections/) | مجموعه‌ها | List, Dictionary |
| [۱۲](./session-12-beginner-project/) | پروژه مبتدی | ماشین‌حساب CLI |

### بخش دوم: شیءگرایی (۱۳–۲۰)

| # | عنوان | موضوعات |
|---|--------|---------|
| [۱۳](./session-13-classes/) | کلاس و شیء | class, object, field |
| [۱۴](./session-14-properties-constructors/) | Property و سازنده | get/set, ctor |
| [۱۵](./session-15-inheritance/) | وراثت | base, override |
| [۱۶](./session-16-polymorphism/) | چندریختی | virtual, abstract |
| [۱۷](./session-17-interfaces/) | Interface | قرارداد، پیاده‌سازی |
| [۱۸](./session-18-abstract-classes/) | کلاس انتزاعی | abstract method |
| [۱۹](./session-19-encapsulation/) | کپسوله‌سازی | access modifiers |
| [۲۰](./session-20-oop-project/) | پروژه OOP | سیستم کتابخانه |

### بخش سوم: C# پیشرفته (۲۱–۳۰)

| # | عنوان | موضوعات |
|---|--------|---------|
| [۲۱](./session-21-generics/) | Generic | `<T>`, constraints |
| [۲۲](./session-22-delegates/) | Delegate | Func, Action |
| [۲۳](./session-23-events/) | Event | event handler |
| [۲۴](./session-24-linq-basics/) | LINQ پایه | Where, Select |
| [۲۵](./session-25-linq-advanced/) | LINQ پیشرفته | GroupBy, Join |
| [۲۶](./session-26-extension-methods/) | Extension Method | syntax و کاربرد |
| [۲۷](./session-27-async-await/) | Async/Await | Task, async |
| [۲۸](./session-28-file-io/) | فایل | File, StreamReader |
| [۲۹](./session-29-json-serialization/) | JSON | System.Text.Json |
| [۳۰](./session-30-advanced-project/) | پروژه پیشرفته | Task Manager |

### بخش چهارم: اکوسیستم .NET (۳۱–۴۰)

| # | عنوان | موضوعات |
|---|--------|---------|
| [۳۱](./session-31-namespaces-assemblies/) | Namespace و Assembly | using, DLL |
| [۳۲](./session-32-nuget/) | NuGet | package management |
| [۳۳](./session-33-unit-testing/) | Unit Test | xUnit, Assert |
| [۳۴](./session-34-logging/) | Logging | ILogger |
| [۳۵](./session-35-configuration/) | Configuration | appsettings.json |
| [۳۶](./session-36-dependency-injection/) | DI | IoC container |
| [۳۷](./session-37-ef-core/) | EF Core | DbContext, migration |
| [۳۸](./session-38-web-api/) | Web API | Minimal API |
| [۳۹](./session-39-aspnet-mvc/) | ASP.NET MVC | Controller, View |
| [۴۰](./session-40-web-project/) | پروژه وب | REST API کامل |

### بخش پنجم: حرفه‌ای (۴۱–۵۰)

| # | عنوان | موضوعات |
|---|--------|---------|
| [۴۱](./session-41-design-patterns/) | Design Patterns | Singleton, Factory |
| [۴۲](./session-42-solid/) | SOLID | اصول طراحی |
| [۴۳](./session-43-repository-pattern/) | Repository | لایه داده |
| [۴۴](./session-44-middleware/) | Middleware | pipeline |
| [۴۵](./session-45-authentication/) | Authentication | JWT basics |
| [۴۶](./session-46-rest-best-practices/) | REST | استاندارد API |
| [۴۷](./session-47-performance/) | Performance | profiling |
| [۴۸](./session-48-debugging/) | Debugging | breakpoint, watch |
| [۴۹](./session-49-deployment/) | Deployment | publish, Docker intro |
| [۵۰](./session-50-final-capstone/) | پروژه نهایی | سیستم مدیریت محصول |

---

## خروجی یادگیری

پس از پایان دوره می‌توانید:

- برنامه Console و API با C# بنویسید
- OOP و LINQ را در پروژه واقعی به کار ببرید
- با EF Core و ASP.NET Core کار کنید
- کد تست‌پذیر و قابل نگهداری بنویسید

---

**شروع کنید از:** [جلسه ۱ — مقدمه](./session-01-intro/)
