# جلسه ۴۲: SOLID Principles (۱.۵ ساعت)

## 🎯 اهداف یادگیری

- درک پنج اصل SOLID با مثال C#
- تشخیص نقض SOLID در کد واقعی
- Refactor کد bad به good با SOLID
- ارتباط SOLID با DI و Design Patterns
- نوشتن کد maintainable برای پروژه تیمی

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | SOLID چیست و چرا مهم است؟ |
| 10–25 | S — Single Responsibility |
| 25–40 | O — Open/Closed |
| 40–55 | L — Liskov Substitution |
| 55–70 | I — Interface Segregation |
| 70–85 | D — Dependency Inversion |
| 85–90 | Refactor exercise + تکلیف |

---

## ۱. SOLID چیست؟

| حرف | اصل | خلاصه |
|-----|-----|-------|
| **S** | Single Responsibility | یک کلاس، یک دلیل تغییر |
| **O** | Open/Closed | باز برای extension، بسته برای modification |
| **L** | Liskov Substitution | subclass جایگزین base بدون شکست |
| **I** | Interface Segregation | interface کوچک و متمرکز |
| **D** | Dependency Inversion | وابسته به abstraction نه concrete |

---

## ۲. S — Single Responsibility Principle

```csharp
// ❌ Bad: چند مسئولیت
class UserManager
{
    public void SaveToDatabase(User user) { }
    public void SendWelcomeEmail(User user) { }
    public string GenerateReport() => "";
}

// ✅ Good: جدا
class UserRepository { public void Save(User user) { } }
class EmailService { public void SendWelcome(User user) { } }
class ReportGenerator { public string Generate() => ""; }
```

**تست SRP:** «چند دلیل برای تغییر این کلاس دارید؟» — اگر بیش از یک، SRP نقض شده.

---

## ۳. O — Open/Closed Principle

```csharp
// ❌ Bad: هر discount جدید → تغییر Calculate
decimal Calculate(string type, decimal price) => type switch
{
    "member" => price * 0.9m,
    "season" => price * 0.8m,
    _ => price
};

// ✅ Good: Strategy + interface
interface IDiscount { decimal Apply(decimal price); }
class MemberDiscount : IDiscount { public decimal Apply(decimal p) => p * 0.9m; }
```

---

## ۴. L — Liskov Substitution Principle

```csharp
// ❌ Bad: Square نقض رفتار Rectangle
class Rectangle { public virtual int Width { get; set; } public virtual int Height { get; set; } }
class Square : Rectangle
{
    public override int Width { set { base.Width = base.Height = value; } }
    public override int Height { set { base.Width = base.Height = value; } }
}

void Test(Rectangle r)
{
    r.Width = 5; r.Height = 4;
    // انتظار area=20 ولی Square → 16
}

// ✅ Good: abstraction جدا
interface IShape { int Area { get; } }
```

---

## ۵. I — Interface Segregation Principle

```csharp
// ❌ Bad: interface بزرگ
interface IWorker
{
    void Work();
    void Eat();
    void Sleep();
}

// ✅ Good: interface‌های کوچک
interface IWorkable { void Work(); }
interface IFeedable { void Eat(); }
class Human : IWorkable, IFeedable { /* ... */ }
class Robot : IWorkable { /* فقط Work */ }
```

---

## ۶. D — Dependency Inversion Principle

```csharp
// ❌ Bad: وابستگی مستقیم
class OrderService
{
    private readonly SqlOrderRepository _repo = new();
}

// ✅ Good: وابستگی به abstraction
class OrderService
{
    private readonly IOrderRepository _repo;
    public OrderService(IOrderRepository repo) => _repo = repo;
}
```

**High-level modules** نباید به **low-level modules** وابسته باشند؛ هر دو به **abstractions**.

---

## ۷. SOLID در ASP.NET Core

```csharp
// Program.cs — DIP
builder.Services.AddScoped<IOrderRepository, EfOrderRepository>();
builder.Services.AddScoped<IOrderService, OrderService>();

// Controller — فقط orchestration (SRP)
public class OrdersController : ControllerBase
{
    private readonly IOrderService _service;
    public OrdersController(IOrderService service) => _service = service;
}
```

---

## ۸. فایل‌های این جلسه

| فایل | موضوع |
|------|--------|
| [01_single_responsibility.cs](./examples/01_single_responsibility.cs) | SRP |
| [02_open_closed.cs](./examples/02_open_closed.cs) | OCP |
| [03_liskov_substitution.cs](./examples/03_liskov_substitution.cs) | LSP |
| [04_interface_dependency.cs](./examples/04_interface_dependency.cs) | ISP و DIP |

---

## ۹. Refactor Checklist

| سوال | اصل |
|------|-----|
| کلاس بیش از ۲۰۰ خط دارد؟ | SRP |
| switch/case برای type؟ | OCP |
| override رفتار base را می‌شکند؟ | LSP |
| کلاس مجبور به implement متد خالی؟ | ISP |
| `new ConcreteClass()` در service؟ | DIP |

---

## ۱۰. مثال واقعی — Before/After

```csharp
// Before: God Service
class PatientService
{
    public void Register(Patient p) { /* save + email + log + validate */ }
}

// After: SOLID
class PatientValidator { public bool Validate(Patient p) => true; }
class PatientRepository : IPatientRepository { }
class NotificationService : INotificationService { }
class PatientRegistrationService
{
    public PatientRegistrationService(
        IPatientRepository repo,
        INotificationService notifier,
        PatientValidator validator) { }
}
```

---

## ۱۱. اشتباهات رایج

| اشتباه | اصل نقض‌شده |
|--------|-------------|
| Controller با LINQ به DB | SRP |
| if/else برای هر نوع پرداخت | OCP |
| Empty method در implement | ISP |
| Service Locator | DIP |
| Inheritance برای code reuse فقط | LSP |

---

## ۱۲. تمرین کلاسی

1. Refactor `ReportService` که print + save + email دارد
2. Discount با OCP بدون switch
3. `IStorage` جدا از `IEmailSender`

---

## ۱۳. نکات حرفه‌ای

- SOLID **راهنما** است نه قانون سخت — pragmatism مهم است
- SRP رایج‌ترین نقض در پروژه‌های junior
- DIP + DI در .NET یکپارچه است
- Code Review با SOLID lens بسیار مؤثر است
- Unit Test راحت = احتمالاً SOLID رعایت شده

---

## ۱۴. پیش‌نیاز و ادامه

**پیش‌نیاز:** جلسه ۴۱ (Design Patterns)، ۳۶ (DI)

**جلسه بعد:** Repository Pattern

**تکلیف:** [question.md](./exercises/question.md)

**جلسه قبل:** [۴۱ — Design Patterns](../session-41-design-patterns/) | **بعد:** [۴۳ — Repository](../session-43-repository-pattern/)
