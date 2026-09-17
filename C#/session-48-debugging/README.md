# جلسه ۴۸: Debugging (اشکال‌زدایی) (۱.۵ ساعت)

## 🎯 اهداف یادگیری

- استفاده از Debugger در Visual Studio / VS Code
- Breakpoint، Watch، Call Stack
- Debug.WriteLine و Structured Logging
- تشخیص NullReferenceException و logic bugs
- Remote debugging و log analysis
- Debugging async و multi-thread code

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | Debugging mindset |
| 10–25 | Breakpoints و Step commands |
| 25–40 | Watch، Locals، Call Stack |
| 40–55 | Conditional Breakpoint |
| 55–70 | Logging vs Debugging |
| 70–85 | Common bugs و راه‌حل |
| 85–90 | جمع‌بندی + تکلیف |

---

## ۱. Debugging Process

```
1. Reproduce the bug
2. Isolate (minimal test case)
3. Hypothesize
4. Verify with debugger/log
5. Fix and regression test
```

---

## ۲. Breakpoints

| نوع | کاربرد |
|-----|--------|
| Standard | توقف در خط |
| Conditional | `i > 100` |
| Hit Count | بعد از N بار |
| Logpoint | log بدون stop |

### Step Commands

| کلید | عمل |
|------|-----|
| F10 | Step Over |
| F11 | Step Into |
| Shift+F11 | Step Out |
| F5 | Continue |

---

## ۳. Watch و Locals

```
Watch: patient.Name, patient.Appointments.Count
Locals: همه متغیرهای scope فعلی
Immediate Window: عبارت arbitrary
```

---

## ۴. Call Stack

```
Main()
  → ProcessOrders()
    → CalculateDiscount()  ← bug اینجاست
      → ApplyStrategy()
```

---

## ۵. Debug Logging

```csharp
// ❌ Console.WriteLine در production
Console.WriteLine($"value={x}");

// ✅ ILogger
_logger.LogDebug("Processing patient {PatientId}", id);
_logger.LogError(ex, "Failed to save patient {PatientId}", id);
```

### Serilog

```csharp
Log.Information("Order {OrderId} placed by {UserId}", orderId, userId);
```

---

## ۶. Common Bugs

### NullReferenceException

```csharp
// ❌
string name = patient.Address.Street; // patient یا Address null

// ✅
string name = patient?.Address?.Street ?? "N/A";
```

### Off-by-one

```csharp
for (int i = 0; i <= array.Length; i++) // ❌ index out of range
for (int i = 0; i < array.Length; i++)  // ✅
```

### Async deadlock

```csharp
var result = GetDataAsync().Result; // ❌
var result = await GetDataAsync();  // ✅
```

---

## ۷. Conditional Breakpoint

```
Condition: order.Total > 1_000_000
Action: Log "Large order: {order.Id}"
```

---

## ۸. dotnet CLI Debugging

```powershell
dotnet run --configuration Debug
dotnet test --logger "console;verbosity=detailed"
```

---

## ۹. فایل‌های این جلسه

| فایل | موضوع |
|------|--------|
| [01_debug_basics.cs](./examples/01_debug_basics.cs) | Debug basics |
| [02_null_and_logic_bugs.cs](./examples/02_null_and_logic_bugs.cs) | Null bugs |
| [03_logging_debug.cs](./examples/03_logging_debug.cs) | Structured logging |
| [04_stack_trace.cs](./examples/04_stack_trace.cs) | Stack trace |

---

## ۱۰. Exception Settings

Visual Studio → Exception Settings → break when thrown:
- NullReferenceException
- ArgumentException
- InvalidOperationException

---

## ۱۱. Unit Test برای Debug

```csharp
[Fact]
public void CalculateDiscount_Member_Returns10Percent()
{
    var service = new OrderService(new MemberDiscount());
    var result = service.CalculateTotal(1000);
    Assert.Equal(900, result); // fails → debug
}
```

---

## ۱۲. اشتباهات رایج

| اشتباه | راه‌حل |
|--------|--------|
| Debug بدون reproduce | minimal case بساز |
| Print everywhere | breakpoint + watch |
| Ignore stack trace | از بالا بخوان |
| Fix symptom not cause | root cause analysis |
| Debug Release build | Debug config |

---

## ۱۳. تمرین کلاسی

1. پیدا کردن bug در CalculateAverage
2. Null-safe refactor
3. Log با level مناسب

---

## ۱�۴. نکات حرفه‌ای

- **Rubber duck debugging** — توضیح bug به دیگری
- Git bisect برای regression
- Application Insights برای production
- `Debugger.Break()` برای attach
- DiagnosticId در compiler warnings

---

## ۱۵. پیش‌نیاز و ادامه

**پیش‌نیاز:** جلسه ۱۰ (Exceptions)، ۳۳ (Unit Testing)

**جلسه بعد:** Deployment

**تکلیف:** [question.md](./exercises/question.md)

**جلسه قبل:** [۴۷ — Performance](../session-47-performance/) | **بعد:** [۴۹ — Deployment](../session-49-deployment/)
