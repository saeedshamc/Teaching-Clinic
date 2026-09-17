# جلسه ۱۹: کپسوله‌سازی (Encapsulation) (۱.۵ ساعت)

## 🎯 اهداف یادگیری

- درک **Encapsulation** — پنهان‌سازی جزئیات داخلی
- سطوح دسترسی: `public`, `private`, `protected`, `internal`
- Property به‌عنوان واسط کنترل‌شده
- **Information Hiding** — چرا فیلد public بد است
- Immutable Objects و defensive copy
- اصول SOLID — Single Responsibility در عمل

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع | فعالیت |
|------|--------|--------|
| 0–10 | مرور | Abstract Class و Interface |
| 10–30 | Access Modifiers | public/private/protected |
| 30–50 | Property Patterns | validation، lazy init |
| 50–65 | Immutable Objects | record، init |
| 65–80 | تمرین | BankAccount امن |
| 80–90 | جمع‌بندی + تکلیف |

---

## ۱. Encapsulation چیست؟

> **کپسوله‌سازی:** بسته‌بندی داده و رفتار در یک واحد، و **محدود کردن دسترسی** به جزئیات داخلی.

```csharp
// ❌ بد — داده آزاد
class BadAccount
{
    public double Balance;  // هرکسی می‌تواند منفی کند!
}

// ✅ خوب — دسترسی کنترل‌شده
class GoodAccount
{
    private double _balance;

    public double Balance => _balance;  // فقط خواندن

    public void Deposit(double amount)
    {
        if (amount > 0) _balance += amount;
    }

    public bool Withdraw(double amount)
    {
        if (amount > 0 && amount <= _balance)
        {
            _balance -= amount;
            return true;
        }
        return false;
    }
}
```

---

## ۲. سطوح دسترسی (Access Modifiers)

| Modifier | کلاس | Assembly | ارث‌بری | World |
|----------|------|----------|---------|-------|
| `public` | ✅ | ✅ | ✅ | ✅ |
| `private` | ✅ | ❌ | ❌ | ❌ |
| `protected` | ✅ | ❌ | ✅ | ❌ |
| `internal` | ✅ | ✅ | ❌ | ❌ |
| `protected internal` | ✅ | ✅ | ✅ | ❌ |
| `private protected` | ✅ | ❌ | ✅* | ❌ |

```csharp
class Example
{
    public int PublicField;           // همه
    private int _privateField;        // فقط این کلاس
    protected int ProtectedField;     // این کلاس + فرزندان
    internal int InternalField;       // همین assembly
}
```

**قانون طلایی:** `private` به‌صورت پیش‌فرض — فقط در صورت نیاز بازتر کنید.

---

## ۳. Property Patterns

### Validation در Setter

```csharp
class Person
{
    private int _age;

    public int Age
    {
        get => _age;
        set
        {
            if (value < 0 || value > 150)
                throw new ArgumentOutOfRangeException(nameof(value));
            _age = value;
        }
    }
}
```

### Lazy Initialization

```csharp
class DataService
{
    private List<string>? _cache;

    public List<string> Cache =>
        _cache ??= LoadFromDatabase();
}
```

### Private Set

```csharp
class Order
{
    public int Id { get; private set; }
    public string Status { get; private set; } = "Pending";

    public void Confirm() => Status = "Confirmed";
}
```

---

## ۴. Information Hiding

```csharp
class TemperatureSensor
{
    private readonly double[] _readings = new double[10];
    private int _index;

    public void AddReading(double value)
    {
        _readings[_index % 10] = value;
        _index++;
    }

    // فقط میانگین — نه دسترسی مستقیم به آرایه
    public double GetAverage()
    {
        int count = Math.Min(_index, 10);
        return count == 0 ? 0 : _readings.Take(count).Average();
    }
}
```

**مزیت:** پیاده‌سازی داخلی قابل تغییر بدون شکستن مصرف‌کننده.

---

## ۵. Immutable Objects

```csharp
// record — immutable by default
record Point(int X, int Y);

var p1 = new Point(3, 4);
var p2 = p1 with { X = 5 };  // کپی با تغییر
// p1.X = 10;  // ❌ خطا

// کلاس immutable دستی
class ImmutablePerson
{
    public string Name { get; }
    public int Age { get; }

    public ImmutablePerson(string name, int age)
    {
        Name = name;
        Age = age;
    }
}
```

---

## ۶. Defensive Copy

```csharp
class Team
{
    private readonly List<string> _members = new();

    public void AddMember(string name) => _members.Add(name);

    // ❌ برگرداندن reference مستقیم
    // public List<string> Members => _members;

    // ✅ کپی دفاعی
    public IReadOnlyList<string> Members => _members.AsReadOnly();
}
```

---

## ۷. Encapsulation در سلسله‌مراتب

```csharp
class BankAccount
{
    protected double Balance { get; private set; }

    public void Deposit(double amount)
    {
        if (ValidateAmount(amount))
            Balance += amount;
    }

    protected virtual bool ValidateAmount(double amount) =>
        amount > 0;
}

class SavingsAccount : BankAccount
{
    protected override bool ValidateAmount(double amount) =>
        base.ValidateAmount(amount) && amount >= 1000;
}
```

---

## ۸. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_access_modifiers.cs](./examples/01_access_modifiers.cs) | سطوح دسترسی |
| [02_property_validation.cs](./examples/02_property_validation.cs) | Validation |
| [03_immutable_objects.cs](./examples/03_immutable_objects.cs) | Immutable |
| [04_secure_bank_account.cs](./examples/04_secure_bank_account.cs) | حساب امن |

---

## ۹. مثال واقعی — User Account

```csharp
class UserAccount
{
    private string _passwordHash = "";
    private int _failedAttempts;

    public string Username { get; }
    public bool IsLocked => _failedAttempts >= 3;

    public UserAccount(string username, string password)
    {
        Username = username;
        SetPassword(password);
    }

    public bool Login(string password)
    {
        if (IsLocked) return false;
        if (VerifyPassword(password))
        {
            _failedAttempts = 0;
            return true;
        }
        _failedAttempts++;
        return false;
    }

    private void SetPassword(string password) { /* hash */ }
    private bool VerifyPassword(string password) => true;
}
```

---

## ۱۰. اجرای مثال‌ها

```powershell
./run-example.ps1 session-19-encapsulation/examples/01_access_modifiers.cs
```

---

## ۱۱. اشتباهات رایج

| اشتباه | مشکل | راه‌حل |
|--------|------|--------|
| `public` field | بدون کنترل | Property |
| برگرداندن mutable collection | دستکاری خارجی | `IReadOnlyList` |
| Setter بدون validation | داده نامعتبر | بررسی در set |
| همه چیز `private` | API ناقص | public interface مشخص |
| Logic در getter سنگین | performance | Lazy یا cache |

---

## ۱۲. اصل Single Responsibility

هر کلاس **یک دلیل** برای تغییر داشته باشد:

```csharp
// ❌ کلاس شلوغ
class User { /* login + email + profile + ... */ }

// ✅ جدا
class UserCredentials { /* login */ }
class UserProfile { /* name, avatar */ }
```

---

## ۱۳. تمرین کلاسی

1. `BankAccount` — Balance فقط از طریق Deposit/Withdraw
2. `Product` — Price با validation مثبت
3. `Team` — Members فقط خواندنی از بیرون

---

## ۱۴. تکلیف

**تکلیف:** [question.md](./exercises/question.md)

پاسخ: [exercise_01.cs](./exercises/exercise_01.cs) | [exercise_02.cs](./exercises/exercise_02.cs)

---

## ۱۵. خلاصه

```
Encapsulation = داده private + دسترسی کنترل‌شده
public        = همه
private       = فقط کلاس
protected     = کلاس + فرزند
Property      = واسط با validation
Immutable     = تغییرناپذیر بعد از ساخت
```

---

**جلسه قبل:** [۱۸ — Abstract Class](../session-18-abstract-classes/) | **بعد:** [۲۰ — پروژه OOP](../session-20-oop-project/)
