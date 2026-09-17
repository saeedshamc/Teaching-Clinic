# جلسه ۶: متدها (Methods) (۱.۵ ساعت)

## 🎯 اهداف یادگیری

- تعریف و فراخوانی متد — DRY و modular code
- پارامترها، آرگومان‌ها و مقدار بازگشتی (`return`)
- پارامترهای پیش‌فرض (default) و `params`
- Overloading — چند متد با یک نام
- مقدمه `ref` و `out` — pass by reference
- Expression-bodied methods (`=>`)

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | چرا متد؟ — refactor کد تکراری |
| 10–30 | تعریف متد، return، void |
| 30–45 | پارامترها، default، named arguments |
| 45–60 | Overloading + params |
| 60–75 | ref و out — تفاوت و کاربرد |
| 75–90 | تمرین + تکلیف |

---

## ۱. چرا متد؟

```csharp
// ❌ تکراری
Console.WriteLine($"سلام علی!");
Console.WriteLine($"سلام مریم!");

// ✅ متد
void Greet(string name) => Console.WriteLine($"سلام {name}!");
Greet("علی");
Greet("مریم");
```

**مزایا:** خوانایی، testability، reuse، maintainability

---

## ۲. ساختار متد

```csharp
// [access] returnType MethodName(parameters)
// {
//     body
//     return value;  // اگر returnType != void
// }

int Add(int a, int b)
{
    return a + b;
}

void PrintSum(int a, int b)
{
    Console.WriteLine(a + b);
    // void — return لازم نیست (یا return; خالی)
}
```

### ۲.۱. Expression-bodied (C# 6+)

```csharp
int Add(int a, int b) => a + b;
bool IsEven(int n) => n % 2 == 0;
void Log(string msg) => Console.WriteLine(msg);
```

**مناسب:** متدهای کوتاه یک‌خطی

---

## ۳. پارامترها و آرگومان‌ها

```csharp
double CalcArea(double width, double height)
{
    return width * height;
}

double area = CalcArea(5, 3);  // آرگومان positional
```

### ۳.۱. پارامتر پیش‌فرض

```csharp
void Greet(string name, string greeting = "سلام")
{
    Console.WriteLine($"{greeting} {name}!");
}

Greet("علی");              // سلام علی!
Greet("مریم", "درود");     // درود مریم!
```

**قانون:** پارامتر با default باید **بعد از** پارامتر بدون default باشد.

### ۳.۲. Named arguments

```csharp
CalcArea(height: 3, width: 5);  // ترتیب مهم نیست
```

---

## ۴. `params` — تعداد متغیر آرگومان

```csharp
int Sum(params int[] numbers)
{
    int total = 0;
    foreach (int n in numbers)
        total += n;
    return total;
}

Console.WriteLine(Sum(1, 2, 3));       // 6
Console.WriteLine(Sum(10, 20, 30, 40)); // 100
Console.WriteLine(Sum());               // 0
```

**قانون:** فقط **یک** پارامتر `params` و باید **آخرین** باشد.

---

## ۵. Method Overloading

```csharp
int Add(int a, int b) => a + b;
double Add(double a, double b) => a + b;
int Add(int a, int b, int c) => a + b + c;

Console.WriteLine(Add(2, 3));       // int version → 5
Console.WriteLine(Add(2.5, 3.1));   // double version → 5.6
Console.WriteLine(Add(1, 2, 3));    // 3-param → 6
```

**Overloading:** همان نام، **امضای متفاوت** (تعداد/نوع پارامتر)

**❌ Overload بر اساس return type فقط — مجاز نیست**

---

## ۶. Pass by Value vs Reference

### ۶.۱. Value (پیش‌فرض)

```csharp
void Increment(int x)
{
    x++;
}

int n = 5;
Increment(n);
Console.WriteLine(n); // 5 — تغییر نکرد!
```

### ۶.۲. `ref` — reference دوطرفه

```csharp
void Increment(ref int x)
{
    x++;
}

int n = 5;
Increment(ref n);
Console.WriteLine(n); // 6 — تغییر کرد
```

**`ref`:** متغیر باید **قبل از فراخوانی** مقداردهی شده باشد.

### ۶.۳. `out` — خروجی اجباری

```csharp
bool TryDivide(int a, int b, out int result)
{
    if (b == 0)
    {
        result = 0;
        return false;
    }
    result = a / b;
    return true;
}

if (TryDivide(10, 3, out int q))
    Console.WriteLine($"نتیجه: {q}");
```

**`out`:** متغیر **داخل متد** حتماً مقداردهی می‌شود — قبل از فراخوانی مقدار اولیه لازم نیست.

### ۶.۴. مقایسه ref vs out

| | `ref` | `out` |
|---|-------|-------|
| مقدار قبل از call | الزامی | لازم نیست |
| مقداردهی داخل متد | اختیاری | **الزامی** |
| کاربرد | modify in-place | return چند مقدار / Try pattern |

---

## ۷. Scope متغیرها

```csharp
void Demo()
{
    int x = 10;  // local
    if (true)
    {
        int y = 20;  // فقط داخل if
    }
    // Console.WriteLine(y); // ❌ خطا — y در scope نیست
}
```

---

## ۸. مثال واقعی — ماشین‌حساب با متد

```csharp
double Add(double a, double b) => a + b;
double Subtract(double a, double b) => a - b;
double Multiply(double a, double b) => a * b;

bool TryDivide(double a, double b, out double result)
{
    if (b == 0) { result = 0; return false; }
    result = a / b;
    return true;
}

double Calculate(double x, double y, char op)
{
    return op switch
    {
        '+' => Add(x, y),
        '-' => Subtract(x, y),
        '*' => Multiply(x, y),
        '/' => TryDivide(x, y, out double r) ? r : double.NaN,
        _   => double.NaN
    };
}
```

---

## ۹. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_basic_method.cs](./examples/01_basic_method.cs) | تعریف و return |
| [02_parameters.cs](./examples/02_parameters.cs) | default و params |
| [03_overload.cs](./examples/03_overload.cs) | overloading |
| [04_ref_out.cs](./examples/04_ref_out.cs) | ref و out |

---

## ۱۰. اشتباهات رایج

| اشتباه | مشکل |
|--------|------|
| فراموش کردن `return` در non-void | خطای کامپایل |
| `ref`/`out` بدون keyword در call | خطای کامپایل |
| default param قبل از required | خطای کامپایل |
| side effect در property/getter | anti-pattern |
| متد ۲۰۰ خطی | refactor به متدهای کوچک |

---

## ۱۱. بهترین شیوه‌ها

1. **یک کار — یک متد** (Single Responsibility)
2. **نام فعل:** `Calculate`, `Validate`, `GetUser`
3. **متدهای کوتاه** — ۱۰–۲۰ خط ideal
4. **`Try` pattern** با `out` برای عملیات risky
5. **Expression body** فقط برای سادگی واقعی

---

## ۱۲. تمرین کلاسی

1. متد `IsEven(int)` → bool
2. متد `Max(int, int)` بدون `Math.Max`
3. متد `PrintTable(int n)` — جدول ضرب
4. overload `FormatPrice` برای int و double

**تکلیف:** [question.md](./exercises/question.md)

---

## ۱۳. خلاصه

- ✅ متد = reusable block
- ✅ پارامتر، return، void
- ✅ default, params, overload
- ✅ ref/out برای reference semantics

**جلسه قبل:** [۵](../session-05-loops/) | **بعد:** [۷](../session-07-arrays/)
