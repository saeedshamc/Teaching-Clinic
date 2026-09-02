# جلسه ۱: مقدمه‌ای بر C# و .NET

## اهداف یادگیری

پس از این جلسه می‌توانید:

- بگویید C# و .NET چه هستند و چه تفاوتی دارند
- SDK را نصب و با `dotnet` کار کنید
- اولین پروژه Console را بسازید و اجرا کنید
- ساختار پایه `Program.cs` را بشناسید
- از کامنت و `Console.WriteLine` استفاده کنید

---

## C# چیست؟

**C#** زبان برنامه‌نویسی مایکروسافت است که برای ساخت انواع نرم‌افزار استفاده می‌شود:

| حوزه | مثال |
|------|------|
| وب | ASP.NET Core, Web API |
| دسکتاپ | WPF, WinForms, MAUI |
| بازی | Unity |
| ابری | Azure Functions, Microservices |
| ابزار CLI | اسکریپت‌های اتوماسیون |

---

## .NET چیست؟

- **C#** = زبان (Syntax و قواعد)
- **.NET** = پلتفرم اجرا (Runtime + کتابخانه‌های آماده)

وقتی می‌نویسید `dotnet run`، کد C# شما compile و روی **.NET Runtime** اجرا می‌شود.

---

## نصب .NET SDK

1. بروید به: [https://dotnet.microsoft.com/download](https://dotnet.microsoft.com/download)
2. **.NET SDK 8** را دانلود و نصب کنید
3. ترمینال را باز کنید:

```bash
dotnet --version
```

---

## ساخت اولین پروژه

```bash
dotnet new console -n MyFirstApp
cd MyFirstApp
dotnet run
```

خروجی پیش‌فرض:

```text
Hello, World!
```

---

## فایل‌های این جلسه

| فایل | توضیح |
|------|-------|
| [examples/01_hello_world.cs](./examples/01_hello_world.cs) | Hello World فارسی |
| [examples/02_comments.cs](./examples/02_comments.cs) | کامنت‌گذاری |
| [examples/03_console_io.cs](./examples/03_console_io.cs) | ورودی/خروجی Console |
| [exercises/question.md](./exercises/question.md) | تمرین‌ها |
| [exercises/exercise_01.cs](./exercises/exercise_01.cs) | پاسخ نمونه |

---

## اجرای مثال‌ها

```powershell
cd C#
./run-example.ps1 session-01-intro/examples/01_hello_world.cs
```

---

## توضیح کد — Hello World

```csharp
Console.WriteLine("سلام، دنیا!");
```

- `Console` → کلاس آماده .NET برای ترمینال
- `WriteLine` → چاپ متن + رفتن به خط بعد
- `"..."` → رشته (string)

---

## اشتباهات رایج

| اشتباه | درست |
|--------|------|
| `console.WriteLine` | `Console` با C بزرگ |
| فراموش کردن `;` انتهای خط | هر statement با `;` تمام شود |
| اجرا بدون SDK | ابتدا `dotnet --version` را چک کنید |

---

## نکات حرفه‌ای

- همیشه SDK LTS (مثل 8) برای پروژه آموزشی انتخاب کنید
- پروژه‌ها را در پوشه جدا بسازید، نه روی Desktop شلوغ
- از VS Code + C# Dev Kit برای IntelliSense استفاده کنید

---

## خلاصه

- ✅ C# زبان است، .NET پلتفرم
- ✅ `dotnet new console` پروژه می‌سازد
- ✅ `dotnet run` اجرا می‌کند
- ✅ `Console.WriteLine` برای خروجی

**جلسه بعد:** [جلسه ۲ — متغیرها و انواع داده](../session-02-variables-types/)
