# جلسه ۲۶: Extension Methods (متدهای توسعه) — ۱.۵ ساعت

## 🎯 اهداف یادگیری

- درک مفهوم Extension Method و دلیل وجود آن
- نوشتن متد توسعه با `static` class و `this` parameter
- تفاوت Extension Method با متد معمولی instance
- استفاده از Extension Methods در LINQ و کتابخانه‌های .NET
- رعایت قوانین naming، namespace و سازماندهی کد
- تشخیص زمان مناسب استفاده (و زمان اجتناب) از Extension Method

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | مرور static methods و LINQ |
| 10–25 | سینتکس Extension Method |
| 25–40 | مثال‌های string و collection |
| 40–55 | generic extension و chaining |
| 55–70 | real-world: validation helpers |
| 70–85 | تمرین کلاسی |
| 85–90 | جمع‌بندی + تکلیف |

---

## ۱. چرا Extension Method؟

گاهی می‌خواهید رفتاری به نوع موجود (`string`, `int`, کلاس خودتان) اضافه کنید، اما:

- به سورس اصلی دسترسی ندارید
- نمی‌خواهید کلاس را با متدهای utility شلوغ کنید
- می‌خواهید API خوانا مثل `"hello".ToPersianDigits()` داشته باشید

**Extension Method** به شما اجازه می‌دهد متد جدید «به ظاهر» روی instance صدا بزنید، در حالی که در واقع static است.

---

## ۲. سینتکس پایه

```csharp
public static class StringExtensions
{
    // پارامتر اول باید this داشته باشد
    public static bool IsNullOrEmptyPersian(this string? value)
    {
        return string.IsNullOrWhiteSpace(value);
    }
}

// استفاده — مثل متد instance
string name = "  ";
Console.WriteLine(name.IsNullOrEmptyPersian()); // true
```

### قوانین مهم

| قانون | توضیح |
|--------|--------|
| کلاس حامل | باید `static` باشد |
| متد | باید `static` باشد |
| پارامتر اول | `this TypeName parameter` |
| namespace | برای دیده شدن، using مناسب لازم است |
| null | اگر `this` برابر null باشد، NullReferenceException رخ می‌دهد (مگر null-check کنید) |

---

## ۳. مثال — توسعه string

```csharp
public static class StringExtensions
{
    public static string Reverse(this string text)
    {
        char[] chars = text.ToCharArray();
        Array.Reverse(chars);
        return new string(chars);
    }

    public static string Truncate(this string text, int maxLength)
    {
        if (text.Length <= maxLength) return text;
        return text[..maxLength] + "...";
    }
}

Console.WriteLine("سلام".Reverse());           // "مالس"
Console.WriteLine("متن طولانی".Truncate(5));   // "متن ط..."
```

---

## ۴. Extension روی IEnumerable — پل به LINQ

بسیاری از متدهای LINQ (`Where`, `Select`, `OrderBy`) در واقع Extension Method هستند:

```csharp
public static class EnumerableExtensions
{
    public static void PrintAll<T>(this IEnumerable<T> source)
    {
        foreach (var item in source)
            Console.WriteLine(item);
    }
}

int[] numbers = { 1, 2, 3 };
numbers.PrintAll();
```

**نکته:** LINQ روی `System.Linq.Enumerable` تعریف شده و با `using System.Linq` در دسترس است.

---

## ۵. Generic Extension Method

```csharp
public static class ListExtensions
{
    public static void Swap<T>(this IList<T> list, int i, int j)
    {
        (list[i], list[j]) = (list[j], list[i]);
    }
}
```

Generic بودن اجازه می‌دهد یک متد روی `List<int>`, `List<string>` و ... کار کند.

---

## ۶. Chaining (زنجیره‌ای کردن)

Extension Methodها می‌توانند پشت سر هم صدا زده شوند:

```csharp
var result = "  HELLO WORLD  "
    .Trim()
    .ToLower()
    .Replace("world", "csharp");

Console.WriteLine(result); // hello csharp
```

متدهای built-in مثل `Trim()` هم extension هستند!

---

## ۷. Extension vs Helper Method

| روش | مزیت | معایب |
|-----|------|-------|
| `StringHelper.Reverse(s)` | صریح، بدون ابهام | خوانایی کمتر |
| `s.Reverse()` | fluent، خوانا | ممکن است developer نفهمد static است |
| متد instance واقعی | دسترسی به private members | نیاز به تغییر کلاس |

**قانون thumb:** اگر به state داخلی private نیاز دارید → متد instance. اگر utility عمومی است → extension.

---

## ۸. Null-safe Extensions

```csharp
public static string OrDefault(this string? value, string fallback)
{
    return string.IsNullOrWhiteSpace(value) ? fallback : value;
}

string? input = null;
Console.WriteLine(input.OrDefault("مهمان")); // مهمان
```

⚠️ اگر `this` null باشد و چک نکنید، قبل از رسیدن به body خطا می‌گیرید. برای reference typeها null-check بنویسید.

---

## ۹. سازماندهی در پروژه واقعی

```
MyApp/
  Extensions/
    StringExtensions.cs
    DateTimeExtensions.cs
    CollectionExtensions.cs
```

- هر فایل یک static class
- نام کلاس: `{Type}Extensions`
- namespace یکسان: `MyApp.Extensions`

---

## ۱۰. Extension Methods در .NET

| متد | نوع هدف |
|-----|---------|
| `Any()`, `Count()` | `IEnumerable<T>` |
| `AddRange()` | `List<T>` (در برخی نسخه‌ها) |
| `CopyTo()` | `Array` |
| `ConfigureAwait()` | `Task` |

---

## ۱۱. محدودیت‌ها

- نمی‌توانید field، property یا event جدید اضافه کنید
- نمی‌توانید متد موجود را override کنید
- اولویت: متد instance واقعی بر extension غلبه می‌کند
- نمی‌توانید روی nested private type extension بنویسید

---

## ۱۲. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_concept.cs](./examples/01_concept.cs) | سینتکس پایه |
| [02_practice.cs](./examples/02_practice.cs) | string و collection |
| [03_real_world.cs](./examples/03_real_world.cs) | validation helpers |
| [04_generic_chaining.cs](./examples/04_generic_chaining.cs) | generic + chaining |

---

## ۱۳. مثال واقعی — اعتبارسنجی فرم

```csharp
public static class ValidationExtensions
{
    public static bool IsValidEmail(this string email)
    {
        return email.Contains('@') && email.Contains('.');
    }

    public static bool IsValidIranianMobile(this string mobile)
    {
        return mobile.Length == 11 && mobile.StartsWith("09");
    }
}

// در Controller یا Service
if (!model.Email.IsValidEmail())
    return "ایمیل نامعتبر";
```

---

## ۱۴. اشتباهات رایج

| اشتباه | مشکل |
|--------|------|
| فراموش کردن `this` | متد extension نمی‌شود |
| کلاس non-static | compile error |
| namespace اشتباه | متد extension دیده نمی‌شود |
| extension روی همه چیز | API شلوغ و گیج‌کننده |
| side effect در extension | رفتار غیرمنتظره |

---

## ۱۵. تمرین کلاسی

1. Extension `IsPalindrome()` برای string
2. Extension `Average()` برای `IEnumerable<int>`
3. Extension `ToPersianDigits()` — تبدیل 0-9 به ۰-۹
4. Extension `Clamp(min, max)` برای int

**تکلیف:** [question.md](./exercises/question.md)

---

## ۱۶. بحث گروهی (۵ دقیقه)

- آیا همیشه extension بهتر از helper static است؟
- چرا Microsoft LINQ را extension طراحی کرد؟

---

## 📚 مطالعه تکمیلی

- [Microsoft Docs — Extension Methods](https://learn.microsoft.com/dotnet/csharp/programming-guide/classes-and-structs/extension-methods)
- مرور سورس LINQ با Go to Definition

---

**جلسه قبل:** [۲۵ — LINQ پیشرفته](../session-25-linq-advanced/) | **بعد:** [۲۷ — Async/Await](../session-27-async-await/)
