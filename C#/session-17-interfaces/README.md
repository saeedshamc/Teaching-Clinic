# جلسه ۱۷: Interface (رابط) (۱.۵ ساعت)

## 🎯 اهداف یادگیری

- تفاوت **Interface** و **Class** — قرارداد بدون پیاده‌سازی
- تعریف و پیاده‌سازی interface با `:`
- **Multiple Interface Implementation** — چند interface روی یک کلاس
- Interface به‌عنوان نوع پارامتر — Loose Coupling
- `IEnumerable<T>` و interfaceهای استاندارد .NET
- Interface در مقابل Abstract Class — کی کدام؟

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع | فعالیت |
|------|--------|--------|
| 0–10 | مرور | Polymorphism و مشکل Downcasting |
| 10–30 | تعریف Interface | `IPlayable`, `IDrawable` |
| 30–50 | چند Interface | کلاس با چند قرارداد |
| 50–65 | Interface به‌عنوان نوع | Dependency Injection ساده |
| 65–80 | تمرین | سیستم ذخیره‌سازی |
| 80–90 | جمع‌بندی + تکلیف |

---

## ۱. Interface چیست؟

> **Interface** یک **قرارداد** است — فقط امضای متدها و propertyها، بدون پیاده‌سازی (تا C# 8).

```csharp
interface IPlayable
{
    void Play();
    void Pause();
    string Title { get; }
}

class Music : IPlayable
{
    public string Title { get; set; } = "";
    public void Play() => Console.WriteLine($"▶ پخش: {Title}");
    public void Pause() => Console.WriteLine($"⏸ توقف: {Title}");
}

class Video : IPlayable
{
    public string Title { get; set; } = "";
    public void Play() => Console.WriteLine($"▶ ویدیو: {Title}");
    public void Pause() => Console.WriteLine($"⏸ ویدیو متوقف");
}
```

---

## ۲. چرا Interface؟

```csharp
// بدون interface — وابستگی سخت
void PlayMusic(Music music) { music.Play(); }
void PlayVideo(Video video) { video.Play(); }

// با interface — یک متد برای همه
void PlayMedia(IPlayable media) { media.Play(); }

PlayMedia(new Music { Title = "آهنگ ۱" });
PlayMedia(new Video { Title = "فیلم ۱" });
```

**مزایا:**
- Loose Coupling — وابستگی کمتر
- Testability — Mock آسان
- Multiple inheritance of behavior

---

## ۳. سینتکس Interface

```csharp
interface ILogger
{
    void Log(string message);
    void LogError(string message);
}

class ConsoleLogger : ILogger
{
    public void Log(string message) =>
        Console.WriteLine($"[INFO] {message}");

    public void LogError(string message) =>
        Console.WriteLine($"[ERROR] {message}");
}

class FileLogger : ILogger
{
    public void Log(string message) =>
        File.AppendAllText("app.log", $"[INFO] {message}\n");

    public void LogError(string message) =>
        File.AppendAllText("app.log", $"[ERROR] {message}\n");
}
```

**قوانین:**
- نام interface با `I` شروع می‌شود (convention)
- همه اعضا implicit `public`
- کلاس **باید** همه اعضا را پیاده کند

---

## ۴. Multiple Interfaces

```csharp
interface IDrawable
{
    void Draw();
}

interface IResizable
{
    void Resize(double factor);
}

class ImageWidget : IDrawable, IResizable
{
    public string Name { get; set; } = "";
    public double Size { get; set; }

    public void Draw() => Console.WriteLine($"رسم {Name} ({Size}px)");
    public void Resize(double factor) => Size *= factor;
}
```

یک کلاس می‌تواند **چند interface** پیاده کند — برخلاف وراثت تک‌والدی.

---

## ۵. Interface به‌عنوان نوع

```csharp
class OrderService
{
    private readonly ILogger _logger;

    public OrderService(ILogger logger)  // Dependency Injection
    {
        _logger = logger;
    }

    public void PlaceOrder(string item)
    {
        _logger.Log($"سفارش ثبت شد: {item}");
    }
}

// استفاده
var service1 = new OrderService(new ConsoleLogger());
var service2 = new OrderService(new FileLogger());
```

---

## ۶. Interfaceهای استاندارد .NET

```csharp
// IEnumerable<T> — قابل iterate
IEnumerable<int> numbers = new List<int> { 1, 2, 3 };
foreach (int n in numbers) { }

// IComparable<T> — قابل مقایسه
class Student : IComparable<Student>
{
    public string Name { get; set; } = "";
    public double GPA { get; set; }
    public int CompareTo(Student? other) =>
        other == null ? 1 : GPA.CompareTo(other.GPA);
}

// IDisposable — آزادسازی منابع
using var reader = new StreamReader("file.txt");
```

---

## ۷. Default Interface Methods (C# 8+)

```csharp
interface IGreeter
{
    void Greet(string name);
    void GreetFormal(string name) =>  // پیاده‌سازی پیش‌فرض
        Console.WriteLine($"با احترام، {name}");
}
```

---

## ۸. Interface vs Abstract Class

| ویژگی | Interface | Abstract Class |
|--------|-----------|----------------|
| وراثت | چندتایی | تک‌والدی |
| فیلد | ندارد (تا C# 8) | دارد |
| Constructor | ندارد | دارد |
| پیاده‌سازی | فقط default (C# 8+) | partial |
| IS-A vs CAN-DO | CAN-DO | IS-A |

**قانون:** اگر رابطه IS-A و state مشترک → Abstract. اگر فقط قابلیت → Interface.

---

## ۹. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_interface_basics.cs](./examples/01_interface_basics.cs) | تعریف و پیاده‌سازی |
| [02_multiple_interfaces.cs](./examples/02_multiple_interfaces.cs) | چند interface |
| [03_dependency_injection.cs](./examples/03_dependency_injection.cs) | DI ساده |
| [04_repository_pattern.cs](./examples/04_repository_pattern.cs) | الگوی Repository |

---

## ۱۰. مثال واقعی — Repository Pattern

```csharp
interface IProductRepository
{
    void Add(string name, double price);
    IEnumerable<(string Name, double Price)> GetAll();
    bool Delete(string name);
}

class InMemoryProductRepository : IProductRepository
{
    private readonly List<(string, double)> _products = new();

    public void Add(string name, double price) =>
        _products.Add((name, price));

    public IEnumerable<(string Name, double Price)> GetAll() => _products;

    public bool Delete(string name) =>
        _products.RemoveAll(p => p.Name == name) > 0;
}
```

---

## ۱۱. اجرای مثال‌ها

```powershell
./run-example.ps1 session-17-interfaces/examples/01_interface_basics.cs
```

---

## ۱۲. اشتباهات رایج

| اشتباه | مشکل | راه‌حل |
|--------|------|--------|
| Interface خیلی بزرگ | ISP نقض | Interfaceهای کوچک |
| نام بدون `I` | Convention شکسته | `ILogger` نه `Logger` |
| فراموش پیاده‌سازی عضو | compile error | IDE کمک می‌کند |
| Interface برای IS-A | طراحی بد | Abstract Class |
| public در interface | redundant | حذف کنید |

---

## ۱۳. اصل Interface Segregation (ISP)

```csharp
// ❌ Interface بزرگ
interface IWorker
{
    void Work(); void Eat(); void Sleep();
}

// ✅ جدا
interface IWorkable { void Work(); }
interface IFeedable { void Eat(); }
```

---

## ۱۴. تمرین کلاسی

1. `IShape` با `GetArea()` — `Circle`, `Rectangle`
2. `INotifier` — `EmailNotifier`, `SmsNotifier`
3. کلاس `Smartphone` — `ICallable`, `IMessageable`

---

## ۱۵. تکلیف

**تکلیف:** [question.md](./exercises/question.md)

پاسخ: [exercise_01.cs](./exercises/exercise_01.cs) | [exercise_02.cs](./exercises/exercise_02.cs)

---

## ۱۶. خلاصه

```
interface IX { void Method(); }   → قرارداد
class C : IX { ... }              → پیاده‌سازی
IX x = new C();                   → Polymorphism
class C : IA, IB { }             → چند interface
```

---

**جلسه قبل:** [۱۶ — Polymorphism](../session-16-polymorphism/) | **بعد:** [۱۸ — Abstract Class](../session-18-abstract-classes/)
