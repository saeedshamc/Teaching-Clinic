# جلسه ۴: ساختارهای شرطی (Control Flow) (۱.۵ ساعت)

## 🎯 اهداف یادگیری

- نوشتن شاخه‌های تصمیم‌گیری با `if`، `else if` و `else`
- استفاده از `switch` کلاسیک برای انتخاب چندحالته
- کار با **Switch Expression** (C# 8+) به‌عنوان جایگزین concise
- ترکیب شرط‌ها با عملگرهای منطقی (`&&`, `||`, `!`)
- درک تفاوت `switch` statement و `switch` expression
- نوشتن کد readable برای سناریوهای واقعی (منو، اعتبارسنجی، نمره)

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | مرور عملگرهای مقایسه و منطقی (جلسه ۳) |
| 10–30 | `if/else` و `else if` — تمرین زنده |
| 30–50 | `switch` statement — fall-through و `break` |
| 50–65 | Switch Expression + pattern matching ساده |
| 65–80 | مثال واقعی: سیستم نمره + منوی CLI |
| 80–90 | اشتباهات رایج + تکلیف |

---

## ۱. ساختار `if/else`

### ۱.۱. فرم پایه

```csharp
int score = 75;

if (score >= 50)
{
    Console.WriteLine("قبول");
}
else
{
    Console.WriteLine("مردود");
}
```

**نکته:** شرط داخل `()` همیشه باید `bool` باشد. `if (x = 5)` **اشتباه** است — `=` انتساب است نه مقایسه.

### ۱.۲. `else if` — چند شرط پشت سر هم

```csharp
int grade = 82;
string label;

if (grade >= 90)
    label = "عالی";
else if (grade >= 75)
    label = "خوب";
else if (grade >= 50)
    label = "قابل قبول";
else
    label = "مردود";

Console.WriteLine(label);
```

**ترتیب مهم است:** شرط‌ها از بالا به پایین بررسی می‌شوند؛ اولین `true` اجرا می‌شود.

### ۱.۳. شرط‌های ترکیبی

```csharp
int age = 20;
bool hasTicket = true;

if (age >= 18 && hasTicket)
    Console.WriteLine("ورود مجاز");
else
    Console.WriteLine("ورود غیرمجاز");
```

### ۱.۴. بدون آکولاد — کی مجاز است؟

```csharp
// فقط برای یک statement
if (score >= 50)
    Console.WriteLine("قبول");

// ❌ خطرناک — فقط خط اول داخل if است!
if (score >= 50)
    Console.WriteLine("قبول");
Console.WriteLine("این همیشه اجرا می‌شود");
```

**بهترین عمل:** همیشه `{}` بگذارید — خوانایی و جلوگیری از باگ.

---

## ۲. عملگر سه‌تایی (مرور)

```csharp
string status = score >= 50 ? "قبول" : "مردود";
```

**تفاوت با `if/else`:** ternary **عبارت** است و مقدار برمی‌گرداند؛ `if/else` **statement** است.

```csharp
// ✅ ternary برای مقداردهی
int max = a > b ? a : b;

// ✅ if/else برای side effect
if (a > b)
    Console.WriteLine("a بزرگتر است");
```

---

## ۳. `switch` Statement

### ۳.۱. فرم کلاسیک

```csharp
Console.Write("عملگر (+,-,*,/): ");
char op = Console.ReadLine()![0];

switch (op)
{
    case '+':
        Console.WriteLine("جمع");
        break;
    case '-':
        Console.WriteLine("تفریق");
        break;
    case '*':
        Console.WriteLine("ضرب");
        break;
    case '/':
        Console.WriteLine("تقسیم");
        break;
    default:
        Console.WriteLine("عملگر نامعتبر");
        break;
}
```

### ۳.۲. چند `case` برای یک بلوک

```csharp
switch (day)
{
    case "شنبه":
    case "یکشنبه":
    case "دوشنبه":
    case "سه‌شنبه":
    case "چهارشنبه":
        Console.WriteLine("روز کاری");
        break;
    case "پنج‌شنبه":
    case "جمعه":
        Console.WriteLine("آخر هفته");
        break;
    default:
        Console.WriteLine("نامعتبر");
        break;
}
```

### ۳.۳. `break` الزامی

بدون `break` (یا `return`/`goto`) کامپایلر خطا می‌دهد — **fall-through** در C# به‌صورت پیش‌فرض مجاز نیست.

### ۳.۴. `switch` روی `string` و `int`

```csharp
switch (month)
{
    case 1:  Console.WriteLine("دی"); break;
    case 2:  Console.WriteLine("بهمن"); break;
    // ...
    default: Console.WriteLine("ماه نامعتبر"); break;
}
```

---

## ۴. Switch Expression (C# 8+)

### ۴.۱. فرم concise

```csharp
string op = Console.ReadLine()!;
string name = op switch
{
    "+" => "جمع",
    "-" => "تفریق",
    "*" => "ضرب",
    "/" => "تقسیم",
    _   => "نامعتبر"   // default
};
Console.WriteLine(name);
```

**تفاوت کلیدی:**

| ویژگی | `switch` statement | Switch Expression |
|--------|-------------------|-------------------|
| خروجی | side effect | **مقدار** برمی‌گرداند |
| default | `default:` | `_` |
| پایان case | `break;` | `=>` |
| استفاده | بلوک چندخطی | مقداردهی inline |

### ۴.۲. Switch Expression در محاسبه

```csharp
double x = 10, y = 3;
char op = '*';

double result = op switch
{
    '+' => x + y,
    '-' => x - y,
    '*' => x * y,
    '/' => y == 0 ? 0 : x / y,
    _   => double.NaN
};
Console.WriteLine($"نتیجه: {result}");
```

### ۴.۳. Pattern Matching ساده در switch

```csharp
object value = 42;

string description = value switch
{
    int n when n < 0    => "عدد منفی",
    int n when n == 0   => "صفر",
    int n               => $"عدد مثبت: {n}",
    string s            => $"رشته: {s}",
    null                => "null",
    _                   => "نوع دیگر"
};
```

**`when`:** شرط اضافی روی pattern.

---

## ۵. مقایسه: کی کدام را استفاده کنیم؟

| سناریو | پیشنهاد |
|--------|---------|
| ۲ حالت (بله/خیر) | `if/else` یا ternary |
| ۳–۸ حالت روی یک متغیر | `switch` یا switch expression |
| شرط‌های پیچیده و مستقل | `if/else if` |
| مقداردهی بر اساس انتخاب | Switch Expression |
| بلوک طولانی per case | `switch` statement |

---

## ۶. مثال واقعی — سیستم نمره

```csharp
Console.Write("نمره (0-100): ");
int.TryParse(Console.ReadLine(), out int score);

if (score < 0 || score > 100)
{
    Console.WriteLine("نمره نامعتبر!");
}
else
{
    string grade = score switch
    {
        >= 90 => "A",
        >= 80 => "B",
        >= 70 => "C",
        >= 60 => "D",
        >= 50 => "E",
        _     => "F"
    };

    string result = score >= 50 ? "قبول" : "مردود";
    Console.WriteLine($"نمره: {grade} — {result}");
}
```

---

## ۷. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_if_else.cs](./examples/01_if_else.cs) | if/else و else if |
| [02_switch.cs](./examples/02_switch.cs) | switch statement |
| [03_switch_expression.cs](./examples/03_switch_expression.cs) | Switch Expression |
| [04_grade_system.cs](./examples/04_grade_system.cs) | سیستم نمره واقعی |

---

## ۸. اشتباهات رایج

| اشتباه | مشکل | راه‌حل |
|--------|------|--------|
| `if (x = 5)` | انتساب به‌جای مقایسه | `if (x == 5)` |
| فراموش کردن `break` در switch | خطای کامپایل | `break;` یا switch expression |
| `else if` ترتیب اشتباه | شرط پایین‌تر هرگز اجرا نمی‌شود | از strict به loose |
| ternary تو در تو زیاد | خوانایی پایین | switch expression |
| مقایسه `string` با `==` | معمولاً OK در C# | برای culture از `Equals` |
| شرط null بدون بررسی | `NullReferenceException` | `is null` یا `?.` |

---

## ۹. بهترین شیوه‌ها (Best Practices)

1. **یک سطح تودرتو:** بیش از ۲–۳ سطح `if` تو در تو → refactor به متد یا switch
2. **Early return:** برای اعتبارسنجی ابتدای متد، زود `return` کنید
3. **Switch Expression** برای mapping مقدار به مقدار
4. **همیشه `default`/`_`** در switch — case فراموش‌شده را بپوشانید
5. **نام‌گذاری bool:** `isValid`, `hasAccess` — خوانایی `if (isValid)` بهتر از `if (flag == true)`
6. **تست edge case:** ۰، منفی، null، مقدار نامعتبر

---

## ۱۰. تمرین کلاسی

1. **زوج/فرد:** عدد از کاربر — `if/else`
2. **فصل سال:** شماره ماه ۱–۱۲ → نام فصل با `switch`
3. **ماشین‌حساب:** دو عدد + عملگر — نتیجه با switch expression
4. **BMI:** وزن و قد → دسته‌بندی (کم‌وزن، نرمال، اضافه‌وزن)

**تکلیف:** [question.md](./exercises/question.md)

---

## ۱۱. خلاصه جلسه

در این جلسه یاد گرفتیم:

- ✅ `if/else/else if` برای تصمیم‌گیری
- ✅ `switch` statement با `case`، `break` و `default`
- ✅ Switch Expression با `=>` و `_`
- ✅ Pattern matching ساده با `when`
- ✅ انتخاب ساختار مناسب برای هر سناریو

**جلسه بعد:** حلقه‌ها — تکرار بدون copy-paste!

---

**جلسه قبل:** [۳ — عملگرها](../session-03-operators/) | **بعد:** [۵ — حلقه‌ها](../session-05-loops/)
