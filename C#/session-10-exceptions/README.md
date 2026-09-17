# جلسه ۱۰: مدیریت استثنا (Exceptions) (۱.۵ ساعت)

## 🎯 اهداف یادگیری

- درک Exception — flow control غیرعادی
- `try` / `catch` / `finally` — ساختار پایه
- انواع exception: `FormatException`, `DivideByZeroException`, ...
- `throw` — پرتاب exception
- Exception custom — کلاس exception اختصاصی
- بهترین شیوه‌ها — کی catch کنیم، کی throw

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | Exception چیست؟ — crash vs handle |
| 10–30 | try/catch — چند catch |
| 30–45 | finally — cleanup |
| 45–60 | throw و rethrow |
| 60–75 | Custom exception |
| 75–90 | تمرین + تکلیف |

---

## ۱. Exception چیست؟

```csharp
int x = int.Parse("abc");  // 💥 FormatException
int y = 10 / 0;            // 💥 DivideByZeroException
```

**بدون handle:** برنامه crash می‌کند.

**با handle:** خطا را مدیریت می‌کنیم و ادامه می‌دهیم.

---

## ۲. try / catch

```csharp
try
{
    Console.Write("عدد: ");
    int n = int.Parse(Console.ReadLine()!);
    Console.WriteLine($"مربع: {n * n}");
}
catch (FormatException)
{
    Console.WriteLine("❌ ورودی عدد نیست!");
}
```

### ۲.۱. چند catch — از specific به general

```csharp
try
{
    int a = int.Parse(input);
    int b = int.Parse(divisor);
    Console.WriteLine(a / b);
}
catch (FormatException ex)
{
    Console.WriteLine($"فرمت اشتباه: {ex.Message}");
}
catch (DivideByZeroException)
{
    Console.WriteLine("تقسیم بر صفر!");
}
catch (Exception ex)
{
    Console.WriteLine($"خطای عمومی: {ex.Message}");
}
```

**ترتیب:** specific قبل از general — `Exception` آخر

### ۲.۲. دسترسی به Exception object

```csharp
catch (Exception ex)
{
    Console.WriteLine(ex.Message);
    Console.WriteLine(ex.StackTrace);  // debug
}
```

---

## ۳. finally

```csharp
FileStream? file = null;
try
{
    file = File.OpenRead("data.txt");
    // پردازش
}
catch (FileNotFoundException)
{
    Console.WriteLine("فایل پیدا نشد");
}
finally
{
    file?.Close();  // همیشه اجرا — حتی با return/exception
}
```

**`finally`:** همیشه اجرا می‌شود — cleanup resource

**C# modern:** `using` statement بهتر از manual finally

---

## ۴. throw

```csharp
void ValidateAge(int age)
{
    if (age < 0 || age > 150)
        throw new ArgumentOutOfRangeException(nameof(age), "سن نامعتبر");
}

try
{
    ValidateAge(-5);
}
catch (ArgumentOutOfRangeException ex)
{
    Console.WriteLine(ex.Message);
}
```

### ۴.۱. throw vs throw ex

```csharp
catch (Exception ex)
{
    throw;      // ✅ stack trace حفظ
    // throw ex; // ❌ stack trace reset
}
```

---

## ۵. Custom Exception

```csharp
class InsufficientBalanceException : Exception
{
    public decimal Balance { get; }
    public decimal Requested { get; }

    public InsufficientBalanceException(decimal balance, decimal requested)
        : base($"موجودی {balance:N0} کافی نیست. درخواست: {requested:N0}")
    {
        Balance = balance;
        Requested = requested;
    }
}
```

**ارث‌بری از:** `Exception` (checked نیست — runtime)

---

## ۶. Exception vs Return Code

| روش | مزیت | معایب |
|-----|------|-------|
| Exception | جدا flow عادی/خطا | overhead |
| Try pattern (`TryParse`) | بدون exception | verbose |
| Result type | functional | کمتر رایج در C# |

**قانون:** exception برای **وضعیت غیرعادی** — نه flow control عادی

---

## ۷. مثال واقعی — برداشت ATM

```csharp
decimal balance = 500_000;

try
{
    Console.Write("مبلغ برداشت: ");
    decimal amount = decimal.Parse(Console.ReadLine()!);

    if (amount <= 0)
        throw new ArgumentException("مبلغ باید مثبت باشد");
    if (amount > balance)
        throw new InsufficientBalanceException(balance, amount);

    balance -= amount;
    Console.WriteLine($"✅ برداشت شد. موجودی: {balance:N0}");
}
catch (FormatException)
{
    Console.WriteLine("❌ مبلغ نامعتبر");
}
catch (InsufficientBalanceException ex)
{
    Console.WriteLine($"❌ {ex.Message}");
}
catch (ArgumentException ex)
{
    Console.WriteLine($"❌ {ex.Message}");
}
finally
{
    Console.WriteLine("--- پایان تراکنش ---");
}
```

---

## ۸. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_try_catch.cs](./examples/01_try_catch.cs) | try/catch |
| [02_finally.cs](./examples/02_finally.cs) | finally |
| [03_throw_custom.cs](./examples/03_throw_custom.cs) | throw + custom |
| [04_exception_patterns.cs](./examples/04_exception_patterns.cs) | الگوها |

---

## ۹. اشتباهات رایج

| اشتباه | مشکل |
|--------|------|
| catch خالی `{}` | خطا پنهان |
| exception برای flow عادی | performance + readability |
| catch `Exception` اول | unreachable catch |
| `throw ex` | stack trace از دست |
| custom exception بدون message | debug سخت |

---

## ۱۰. بهترین شیوه‌ها

1. **Specific catch** — نوع مشخص
2. **Try pattern** (`TryParse`) برای ورودی کاربر
3. **Custom exception** برای domain errors
4. **Log** در catch — `ex.Message` + context
5. **finally/using** برای resource cleanup
6. **Fail fast** — validate early, throw clear

---

## ۱۱. تمرین کلاسی

1. try/catch برای `int.Parse`
2. تقسیم با handle `DivideByZeroException`
3. finally — پیام «پایان»
4. throw برای سن نامعتبر

**تکلیف:** [question.md](./exercises/question.md)

---

## ۱۲. خلاصه

- ✅ try/catch/finally
- ✅ throw — explicit error
- ✅ custom Exception class
- ✅ Try pattern جایگزین

**جلسه قبل:** [۹](../session-09-null-nullable/) | **بعد:** [۱۱](../session-11-collections/)
