# جلسه ۲۲: Delegate (نماینده) (۱.۵ ساعت)

## 🎯 اهداف یادگیری

- درک **Delegate** — اشاره‌گر به متد
- تعریف و فراخوانی delegate
- **Multicast Delegate** — چند متد روی یک delegate
- Delegateهای built-in: `Action`, `Func`, `Predicate`
- Lambda Expression به‌عنوان delegate
- کاربرد در Callback و Strategy Pattern

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع | فعالیت |
|------|--------|--------|
| 0–10 | مرور | Generics و Func<T> |
| 10–30 | Delegate سفارشی | تعریف و استفاده |
| 30–50 | Action/Func/Predicate | built-in delegates |
| 50–65 | Lambda | `(x) => x * 2` |
| 65–80 | تمرین | فیلتر و مرتب‌سازی |
| 80–90 | جمع‌بندی + تکلیف |

---

## ۱. Delegate چیست؟

> **Delegate** نوعی است که **امضای متد** را تعریف می‌کند — می‌توان به متدهای سازگار اشاره کرد.

```csharp
// تعریف delegate
delegate int MathOperation(int a, int b);

// متدهای سازگار
static int Add(int a, int b) => a + b;
static int Multiply(int a, int b) => a * b;

// استفاده
MathOperation op = Add;
Console.WriteLine(op(5, 3));  // 8

op = Multiply;
Console.WriteLine(op(5, 3));  // 15
```

**مثل:** متغیر که به جای عدد، **متد** نگه می‌دارد.

---

## ۲. Delegate به‌عنوان پارامتر

```csharp
delegate void LogHandler(string message);

static void ProcessOrder(string orderId, LogHandler logger)
{
    logger($"پردازش سفارش {orderId}...");
    logger($"سفارش {orderId} تکمیل شد");
}

ProcessOrder("ORD-001", msg => Console.WriteLine($"[LOG] {msg}"));
```

**Callback Pattern** — متد به متد دیگر پاس داده می‌شود.

---

## ۳. Multicast Delegate

```csharp
delegate void NotifyHandler(string msg);

static void SendEmail(string msg) => Console.WriteLine($"📧 {msg}");
static void SendSms(string msg) => Console.WriteLine($"📱 {msg}");

NotifyHandler notify = SendEmail;
notify += SendSms;    // اضافه کردن
notify += msg => Console.WriteLine($"🔔 {msg}");

notify("سفارش آماده است");  // هر سه اجرا می‌شوند

notify -= SendEmail;  // حذف
notify("یادآوری");
```

---

## ۴. Action — بدون بازگشت

```csharp
Action<string> print = msg => Console.WriteLine(msg);
Action<int, int> printSum = (a, b) => Console.WriteLine(a + b);
Action noParam = () => Console.WriteLine("سلام!");

print("تست");
printSum(3, 7);
noParam();
```

`Action<T1, T2, ...>` — تا ۱۶ پارامتر، بدون return.

---

## ۵. Func — با بازگشت

```csharp
Func<int, int, int> add = (a, b) => a + b;
Func<string, int> getLength = s => s.Length;
Func<bool> isWeekend = () => DateTime.Now.DayOfWeek is DayOfWeek.Friday or DayOfWeek.Saturday;

Console.WriteLine(add(10, 20));       // 30
Console.WriteLine(getLength("سلام"));  // 4
Console.WriteLine(isWeekend());
```

`Func<T1, ..., TResult>` — آخرین type parameter = return type.

---

## ۶. Predicate — bool برمی‌گرداند

```csharp
Predicate<int> isEven = n => n % 2 == 0;
List<int> numbers = new() { 1, 2, 3, 4, 5, 6 };

var evens = numbers.FindAll(isEven);
// معادل: numbers.FindAll(n => n % 2 == 0);
```

`Predicate<T>` = `Func<T, bool>`

---

## ۷. Lambda Expression

```csharp
// Statement lambda
Func<int, int> square = x =>
{
    int result = x * x;
    return result;
};

// Expression lambda
Func<int, int> cube = x => x * x * x;

// Multiple parameters
Func<int, int, string> format = (a, b) => $"{a} + {b} = {a + b}";
```

| سینتکس | مثال |
|--------|------|
| `(x) => expr` | `x => x * 2` |
| `(x, y) => expr` | `(a, b) => a + b` |
| `() => expr` | `() => DateTime.Now` |
| `(x) => { ... }` | با بدنه چندخطی |

---

## ۸. Strategy Pattern با Delegate

```csharp
enum SortOrder { Ascending, Descending }

static void Sort(int[] arr, Func<int, int, bool> compare)
{
    Array.Sort(arr, (a, b) => compare(a, b) ? -1 : 1);
}

Sort(new[] { 3, 1, 4, 1, 5 }, (a, b) => a > b);  // نزولی
```

---

## ۹. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_delegate_basics.cs](./examples/01_delegate_basics.cs) | تعریف delegate |
| [02_action_func.cs](./examples/02_action_func.cs) | Action, Func |
| [03_multicast_lambda.cs](./examples/03_multicast_lambda.cs) | Multicast + Lambda |
| [04_filter_strategy.cs](./examples/04_filter_strategy.cs) | فیلتر و Strategy |

---

## ۱۰. مثال واقعی — اعتبارسنجی

```csharp
class Validator
{
    private readonly List<Func<string, bool>> _rules = new();

    public void AddRule(Func<string, bool> rule) => _rules.Add(rule);
    public bool Validate(string input) => _rules.All(r => r(input));
}

var validator = new Validator();
validator.AddRule(s => s.Length >= 8);
validator.AddRule(s => s.Any(char.IsDigit));
validator.AddRule(s => s.Any(char.IsUpper));
```

---

## ۱۱. اجرای مثال‌ها

```powershell
./run-example.ps1 session-22-delegates/examples/01_delegate_basics.cs
```

---

## ۱۲. اشتباهات رایج

| اشتباه | مشکل | راه‌حل |
|--------|------|--------|
| امضای ناسازگار | compile error | پارامتر و return یکسان |
| `null` delegate | NullReferenceException | بررسی null یا `?.` |
| `-=` متد ثبت‌نشده | خطای خاموش | فقط متدهای `+=` شده |
| Lambda با side effect زیاد | باگ | logic در متد جدا |
| `Func` vs `Action` اشتباه | compile error | return type |

---

## ۱۳. تمرین کلاسی

1. `Calculator` با `Func<int,int,int>` برای عملیات
2. فیلتر لیست با `Predicate<T>`
3. Multicast notification — Email + SMS

---

## ۱۴. تکلیف

**تکلیف:** [question.md](./exercises/question.md)

پاسخ: [exercise_01.cs](./exercises/exercise_01.cs) | [exercise_02.cs](./exercises/exercise_02.cs)

---

## ۱۵. خلاصه

```
delegate       → نوع اشاره‌گر به متد
Action<T>      → void برمی‌گرداند
Func<T, TResult> → TResult برمی‌گرداند
Predicate<T>   → bool برمی‌گرداند
(x) => expr    → Lambda
+= / -=        → Multicast
```

---

**جلسه قبل:** [۲۱ — Generics](../session-21-generics/) | **بعد:** [۲۳ — Events](../session-23-events/)
