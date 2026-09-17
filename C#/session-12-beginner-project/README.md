# جلسه ۱۲: پروژه مبتدی — ماشین‌حساب CLI (۱.۵ ساعت)

## 🎯 اهداف یادگیری

- ترکیب مفاهیم جلسات ۱–۱۱ در یک پروژه واقعی
- طراحی منوی CLI با `while` / `do-while`
- ساختاردهی کد با **متدها** — separation of concerns
- handle خطا با **try/catch** و **TryParse**
- ذخیره **تاریخچه** عملیات با `List<string>`
- توسعه تدریجی (incremental development)
- آماده‌سازی برای OOP (جلسه ۱۳+)

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | مرور اهداف پروژه + demo |
| 10–25 | فاز ۱: منو + loop |
| 25–45 | فاز ۲: عملیات ریاضی + متدها |
| 45–60 | فاز ۳: exception handling |
| 60–75 | فاز ۴: تاریخچه + polish |
| 75–90 | code review + تکلیف گسترش |

---

## ۱. مشخصات پروژه

### ۱.۱. قابلیت‌های پایه (MVP)

```
╔══════════════════════════════╗
║     ماشین‌حساب CLI           ║
╠══════════════════════════════╣
║  1. جمع (+)                  ║
║  2. تفریق (-)                ║
║  3. ضرب (*)                  ║
║  4. تقسیم (/)                ║
║  5. تاریخچه                  ║
║  0. خروج                     ║
╚══════════════════════════════╝
```

### ۱.۲. قابلیت‌های پیشرفته (Stretch)

- توان (`^`) و باقیمانده (`%`)
- پاک کردن تاریخچه
- آخرین نتیجه (`ANS`)
- اعشار با `double`

---

## ۲. معماری پروژه

```
Program (top-level)
├── ShowMenu()
├── GetChoice()
├── ReadNumber(prompt)
├── Calculate(a, b, op)
├── TryDivide(a, b, out result)
├── AddToHistory(entry)
├── ShowHistory()
└── MainLoop (while)
```

**اصل:** هر متد **یک کار** انجام دهد.

---

## ۳. فاز ۱ — منو و Loop

```csharp
void ShowMenu()
{
    Console.WriteLine("1. جمع  2. تفریق  3. ضرب  4. تقسیم  0. خروج");
}

int GetChoice()
{
    Console.Write("انتخاب: ");
    int.TryParse(Console.ReadLine(), out int choice);
    return choice;
}

bool running = true;
while (running)
{
    ShowMenu();
    int choice = GetChoice();
    if (choice == 0) running = false;
}
```

**الگو:** `while` + flag — همان pattern منو ATM

---

## ۴. فاز ۲ — متدهای محاسبه

```csharp
double Add(double a, double b) => a + b;
double Subtract(double a, double b) => a - b;
double Multiply(double a, double b) => a * b;

bool TryDivide(double a, double b, out double result)
{
    result = 0;
    if (b == 0) return false;
    result = a / b;
    return true;
}

double Calculate(double a, double b, char op)
{
    return op switch
    {
        '+' => Add(a, b),
        '-' => Subtract(a, b),
        '*' => Multiply(a, b),
        '/' => TryDivide(a, b, out double r) ? r : throw new DivideByZeroException(),
        _   => throw new ArgumentException($"عملگر نامعتبر: {op}")
    };
}
```

**نکات:**
- Switch Expression از جلسه ۴
- `TryDivide` + `out` از جلسه ۶
- Overload potential برای int/double

---

## ۵. فاز ۳ — Exception Handling

```csharp
double ReadNumber(string prompt)
{
    while (true)
    {
        Console.Write(prompt);
        if (double.TryParse(Console.ReadLine(), out double n))
            return n;
        Console.WriteLine("❌ عدد نامعتبر — دوباره تلاش کنید");
    }
}

// در main loop:
try
{
    double a = ReadNumber("عدد اول: ");
    double b = ReadNumber("عدد دوم: ");
    double result = Calculate(a, b, op);
    Console.WriteLine($"= {result}");
}
catch (DivideByZeroException)
{
    Console.WriteLine("❌ تقسیم بر صفر!");
}
catch (ArgumentException ex)
{
    Console.WriteLine($"❌ {ex.Message}");
}
```

**ترکیب:** TryParse (بدون exception) + catch (برای edge cases)

---

## ۶. فاز ۴ — تاریخچه با List

```csharp
var history = new List<string>();

void AddToHistory(string entry)
{
    history.Add($"[{DateTime.Now:HH:mm:ss}] {entry}");
}

void ShowHistory()
{
    if (history.Count == 0)
    {
        Console.WriteLine("تاریخچه خالی است");
        return;
    }
    for (int i = 0; i < history.Count; i++)
        Console.WriteLine($"  {i + 1}. {history[i]}");
}

// بعد از هر محاسبه:
AddToHistory($"{a} {op} {b} = {result}");
```

---

## ۷. جریان کامل Main Loop

```csharp
var history = new List<string>();
double lastResult = 0;
bool running = true;

while (running)
{
    ShowMenu();
    int choice = GetChoice();

    switch (choice)
    {
        case 0:
            running = false;
            Console.WriteLine("خداحافظ! 👋");
            break;
        case 1: case 2: case 3: case 4:
            char op = choice switch { 1 => '+', 2 => '-', 3 => '*', 4 => '/', _ => '?' };
            try
            {
                double a = ReadNumber("عدد اول: ");
                double b = ReadNumber("عدد دوم: ");
                double result = Calculate(a, b, op);
                lastResult = result;
                string entry = $"{a} {op} {b} = {result}";
                Console.WriteLine($"= {result}");
                history.Add(entry);
            }
            catch (DivideByZeroException)
            {
                Console.WriteLine("❌ تقسیم بر صفر!");
            }
            break;
        case 5:
            ShowHistory(history);
            break;
        default:
            Console.WriteLine("❌ گزینه نامعتبر");
            break;
    }
    Console.WriteLine();
}
```

---

## ۸. مفاهیم به کار رفته — نقشه جلسات

| مفهوم | جلسه |
|--------|------|
| متغیر، نوع | ۲ |
| عملگر | ۳ |
| if/switch | ۴ |
| while/do-while | ۵ |
| متد، out, overload | ۶ |
| string interpolation | ۸ |
| null (`string?`) | ۹ |
| try/catch | ۱۰ |
| List | ۱۱ |

---

## ۹. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_menu_loop.cs](./examples/01_menu_loop.cs) | منو + while |
| [02_calculator_core.cs](./examples/02_calculator_core.cs) | متدهای محاسبه |
| [03_full_calculator.cs](./examples/03_full_calculator.cs) | نسخه کامل |
| [04_enhanced_calculator.cs](./examples/04_enhanced_calculator.cs) | تاریخچه + ANS |

---

## ۱۰. اشتباهات رایج

| اشتباه | مشکل |
|--------|------|
| همه کد در Main | test/debug سخت |
| بدون validation ورودی | crash |
| تقسیم int | از دست رفتن اعشار |
| history بدون limit | memory (stretch: max 100) |
| copy-paste per operation | DRY violation |

---

## ۱۱. بهترین شیوه‌ها

1. **Incremental:** منو → یک عمل → همه → history
2. **متدهای کوچک** — هر کدام testable
3. **TryParse** برای ورودی
4. **پیام خطای فارسی** و واضح
5. **Git commit** بعد از هر فاز (اختیاری)

---

## ۱۲. ایده‌های گسترش (Stretch)

- [ ] ذخیره history در فایل (جلسه ۲۸)
- [ ] تبدیل به class `Calculator` (جلسه ۱۳)
- [ ] unit test (جلسه ۳۳)
- [ ] رابط WPF/Blazor (جلسات web)

---

## ۱۳. تمرین کلاسی

1. MVP — ۴ عمل + خروج
2. ReadNumber با retry
3. تاریخچه ۵ عمل آخر
4. Case 5 — نمایش history

**تکلیف:** [question.md](./exercises/question.md)

---

## ۱۴. خلاصه

در این جلسه:
- ✅ پروژه end-to-end ساختیم
- ✅ منو + loop + switch
- ✅ متد + exception + List
- ✅ آماده OOP و پروژه‌های بزرگ‌تر

**تبریک! بخش مبتدی C# تمام شد 🎉**

---

**جلسه قبل:** [۱۱ — Collections](../session-11-collections/) | **بعد:** [۱۳ — Classes](../session-13-classes/)
