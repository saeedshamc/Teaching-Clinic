# جلسه ۱: مقدمه‌ای بر C# و .NET (۱.۵ ساعت)

## 🎯 اهداف یادگیری

پس از این جلسه دانشجو می‌تواند:

- تفاوت **C#**، **.NET** و **SDK** را توضیح دهد
- .NET SDK را نصب و صحت نصب را بررسی کند
- اولین پروژه Console را بسازد و اجرا کند
- ساختار `Program.cs` و نقش `dotnet` را بشناسد
- با `Console.WriteLine`، `Console.ReadLine` و انواع کامنت کار کند
- خطاهای رایج مبتدی را تشخیص دهد

---

## ⏱️ برنامه زمانی جلسه (۹۰ دقیقه)

| زمان | بخش | فعالیت مدرس |
|------|-----|-------------|
| 0–10 | معرفی | انگیزه، کاربرد C#، مسیر دوره |
| 10–25 | تئوری | C# vs .NET vs Runtime |
| 25–45 | لایو کد | نصب، `dotnet new`, `dotnet run` |
| 45–60 | تئوری | آناتومی پروژه و Console |
| 60–80 | تمرین کلاسی | Hello World + کارت معرفی |
| 80–90 | جمع‌بندی | تکلیف + Q&A |

---

## ۱. C# چیست؟

**C#** (سی‌شارپ) زبان برنامه‌نویسی سطح بالا و strongly-typed است که توسط مایکروسافت طراحی شده.

### چرا C# یاد بگیریم؟

| حوزه | ابزار/فریم‌ورک | مثال واقعی |
|------|----------------|------------|
| وب | ASP.NET Core | API فروشگاه، پنل ادمین |
| دسکتاپ | WPF / MAUI | نرم‌افزار حسابداری |
| بازی | Unity | بازی موبایل |
| ابری | Azure Functions | پردازش پس‌زمینه |
| Enterprise | .NET + SQL | سیستم‌های سازمانی |

### ویژگی‌های مهم C#

- **Static Typing**: نوع متغیر مشخص است → خطاهای بیشتر در زمان compile گرفته می‌شود
- **Object-Oriented**: کلاس، وراثت، Interface
- **Modern Language**: هر سال ویژگی جدید (records, pattern matching, ...)
- **Cross-Platform**: روی Windows, Linux, macOS اجرا می‌شود

---

## ۲. .NET چیست؟ (تفاوت با C#)

| مفهوم | نقش | مثال |
|--------|-----|------|
| **C#** | زبان (Syntax) | `Console.WriteLine("Hi");` |
| **.NET Runtime** | موتور اجرا | CLR |
| **.NET SDK** | ابزار توسعه | `dotnet build`, `dotnet run` |
| **BCL** | کتابخانه پایه | `System`, `Console`, `String` |

**قانون طلایی:**  
`C#` کدی است که می‌نویسید؛ `.NET` چیزی است که آن کد را اجرا می‌کند.

---

## ۳. نصب .NET SDK

### ویندوز

1. [dotnet.microsoft.com/download](https://dotnet.microsoft.com/download)
2. **.NET 8 SDK** (LTS) را دانلود کنید
3. نصب → ترمinal جدید باز کنید

```powershell
dotnet --version
dotnet --list-sdks
```

### VS Code (پیشنهادی)

- Extension: **C# Dev Kit** (Microsoft)
- Extension: **C#** (Microsoft)

---

## ۴. اولین پروژه

```bash
dotnet new console -n MyFirstApp
cd MyFirstApp
dotnet run
```

### خروجی پیش‌فرض

```text
Hello, World!
```

### آناتومی پروژه

```
MyFirstApp/
├── MyFirstApp.csproj    # تنظیمات پروژه (TargetFramework, Package)
├── Program.cs           # نقطه شروع برنامه
└── obj/ bin/            # خروجی build (دستی ویرایش نکنید)
```

### فایل `Program.cs` (C# 10+)

```csharp
// Top-level statements: بدون نیاز به class Program و static void Main
Console.WriteLine("Hello, World!");
```

**قبلاً (سبک کلاسیک):**

```csharp
namespace MyFirstApp
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Hello, World!");
        }
    }
}
```

در دوره ما از **top-level statements** استفاده می‌کنیم (خواناتر برای مبتدی).

---

## ۵. کلاس Console — ورودی و خروجی

### WriteLine vs Write

```csharp
Console.Write("سلام ");
Console.Write("دنیا");
Console.WriteLine();        // فقط رفتن به خط بعد

Console.WriteLine("خط جدید");
```

| متد | رفتار |
|-----|--------|
| `WriteLine(text)` | چاپ + `\n` |
| `Write(text)` | چاپ بدون `\n` |
| `ReadLine()` | خواندن یک خط از کاربر |
| `ReadKey()` | خواندن یک کلید |

### مثال تعاملی

```csharp
Console.Write("نام: ");
string? name = Console.ReadLine();
Console.WriteLine($"سلام {name}!");
```

**نکته:** `ReadLine()` ممکن است `null` برگرداند → در جلسات بعد با validation کار می‌کنیم.

---

## ۶. کامنت‌گذاری

```csharp
// کامنت تک‌خطی

/*
  کامنت چندخطی
  برای توضیح الگوریتم
*/

/// XML Doc — برای مستندسازی API (حرفه‌ای)
```

**چرا کامنت؟**

- توضیح «چرا» نه فقط «چه»
- کمک به هم‌تیمی و خود آینده‌تان

---

## ۷. فایل‌های این جلسه

| فایل | موضوع |
|------|--------|
| [examples/01_hello_world.cs](./examples/01_hello_world.cs) | Hello World |
| [examples/02_comments.cs](./examples/02_comments.cs) | کامنت‌ها |
| [examples/03_console_io.cs](./examples/03_console_io.cs) | ورودی/خروجی |
| [examples/04_project_structure_demo.cs](./examples/04_project_structure_demo.cs) | ساختار برنامه |
| [exercises/question.md](./exercises/question.md) | تمرین‌ها |
| [exercises/exercise_01.cs](./exercises/exercise_01.cs) | پاسخ تمرین ۱ |
| [exercises/exercise_02.cs](./exercises/exercise_02.cs) | پاسخ تمرین ۲ |

### اجرا

```powershell
cd C#
./run-example.ps1 session-01-intro/examples/01_hello_world.cs
```

---

## ۸. توضیح خط‌به‌خط — Hello World

```csharp
Console.WriteLine("سلام، دنیا!");
```

1. `Console` → کلاس static در namespace `System`
2. `.` → دسترسی به عضو کلاس
3. `WriteLine` → متد نمایش
4. `("...")` → آرگومان از نوع `string`
5. `;` → پایان دستور (اجباری)

---

## ۹. اشتباهات رایج (Common Pitfalls)

### ❌ حساسیت به حروف

```csharp
console.WriteLine("Hi");  // خطا: Console با C بزرگ
```

### ❌ فراموش کردن سمی‌کolon

```csharp
Console.WriteLine("Hi")   // خطای compile
```

### ❌ اجرا در پوشه اشتباه

```bash
# باید داخل پوشه پروژه یا با run-example باشید
dotnet run
```

### ❌ اشتباه گرفتن SDK با Runtime

- فقط Runtime → `dotnet run` ممکن است build نکند
- **SDK** برای توسعه لازم است

---

## ۱۰. نکات حرفه‌ای (Best Practices)

- پروژه هر جلسه را در پوشه جدا بسازید
- نام پروژه معنادار: `Session01_Hello` نه `Test1`
- قبل از کلاس: `dotnet --version` را چک کنید
- خروجی ترمینال را برای دانشجو screenshot بگیرید

---

## ۱۱. تمرین کلاسی (در جلسه)

1. برنامه‌ای بنویسید که ۳ خط مشخصات شما را چاپ کند
2. از کاربر نام بگیرید و پیام خوش‌آمد بگویید
3. یک ASCII Art ساده (۳ خط) چاپ کنید

---

## ۱۲. تکلیف خانه

- [question.md](./exercises/question.md) را کامل کنید
- در `NOTES.md` شخصی بنویسید: تفاوت C# و .NET چیست؟
- ویدیو/مستند رسمی: [Microsoft Learn - C#](https://learn.microsoft.com/dotnet/csharp/)

---

## ۱۳. خلاصه جلسه

- ✅ C# زبان است، .NET پلتفرم اجرا
- ✅ SDK شامل build tools است
- ✅ `dotnet new console` + `dotnet run`
- ✅ `Console.WriteLine` / `ReadLine`
- ✅ کامنت برای خوانایی کد

**جلسه بعد:** [جلسه ۲ — متغیرها و انواع داده](../session-02-variables-types/)
