# جلسه ۸: رشته‌ها (Strings) (۱.۵ ساعت)

## 🎯 اهداف یادگیری

- درک `string` به‌عنوان immutable reference type
- String Interpolation (`$""`) و Composite Formatting
- متدهای پرکاربرد: `Length`, `ToUpper`, `Trim`, `Contains`, `Replace`, ...
- `Split` و `Join` — تجزیه و ترکیب
- مقایسه رشته: `==`, `Equals`, `Compare`
- مقدمه `StringBuilder` — وقتی concatenation زیاد است
- کاراکتر (`char`) و escape sequences

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | string vs char — immutable |
| 10–25 | Interpolation و formatting |
| 25–45 | متدهای string |
| 45–55 | Split / Join |
| 55–70 | StringBuilder |
| 70–90 | تمرین + تکلیف |

---

## ۱. مبانی string

```csharp
string name = "علی";
char first = name[0];        // 'ع' — char
int len = name.Length;       // property

// ❌ name[0] = 'م';  // خطا — immutable!
```

### ۱.۱. Immutable بودن

```csharp
string s = "hello";
s = s + " world";  // string جدید ساخته می‌شود
```

**تاثیر:** concatenation در loop → performance issue → `StringBuilder`

### ۱.۲. Literal و verbatim

```csharp
string path = "C:\\Users\\Ali";      // escape
string path2 = @"C:\Users\Ali";    // verbatim — \ literal
string multiline = """
    خط اول
    خط دوم
    """;
```

---

## ۲. String Interpolation

```csharp
string name = "علی";
int age = 25;
double price = 150_000.5;

string msg = $"سلام {name}! سن: {age}";
string money = $"قیمت: {price:N0} تومان";
string calc = $"2+3={2+3}";
```

### ۲.۱. Format Specifiers

| Specifier | معنی | مثال |
|-----------|------|------|
| `N0` | عدد با جداکننده | `1,250,000` |
| `F2` | ۲ رقم اعشار | `3.14` |
| `P0` | درصد | `25%` |
| `D4` | padding صفر | `0042` |
| `X` | hex | `FF` |

```csharp
Console.WriteLine($"{42:D5}");    // 00042
Console.WriteLine($"{0.256:P1}"); // 25.6%
```

### ۲.۲. Composite Formatting (قدیمی)

```csharp
string s = string.Format("سلام {0}! سن: {1}", name, age);
```

**پیشنهاد:** interpolation خواناتر است.

---

## ۳. Concatenation

```csharp
string full = firstName + " " + lastName;
string full2 = string.Concat(firstName, " ", lastName);
string full3 = $"{firstName} {lastName}";
```

**در loop از `+` پرهیز کنید** — StringBuilder

---

## ۴. متدهای پرکاربرد

### ۴.۱. تغییر case و trim

```csharp
"  Hello  ".Trim();       // "Hello"
"hello".ToUpper();        // "HELLO"
"HELLO".ToLower();        // "hello"
```

### ۴.۲. جستجو

```csharp
"programming".Contains("gram");     // true
"hello".StartsWith("he");           // true
"hello".EndsWith("lo");             // true
"hello".IndexOf('l');               // 2
"hello".LastIndexOf('l');           // 3
```

### ۴.۳. Replace و Substring

```csharp
"hello world".Replace("world", "C#");  // "hello C#"
"programming".Substring(3, 4);         // "gram"
```

### ۴.۴. IsNullOrEmpty / IsNullOrWhiteSpace

```csharp
string.IsNullOrEmpty("");           // true
string.IsNullOrWhiteSpace("   ");   // true
```

---

## ۵. Split و Join

```csharp
string csv = "علی,مریم,رضا,سارا";
string[] names = csv.Split(',');

string joined = string.Join(" | ", names);
// "علی | مریم | رضا | سارا"
```

### Split پیشرفته

```csharp
"one  two   three".Split(' ', StringSplitOptions.RemoveEmptyEntries);
// ["one", "two", "three"]
```

---

## ۶. مقایسه رشته

```csharp
string a = "hello";
string b = "Hello";

a == b;                              // false
a.Equals(b, StringComparison.OrdinalIgnoreCase); // true
string.Compare(a, b, StringComparison.OrdinalIgnoreCase); // 0 = equal
```

**Culture-aware:** برای متن فارسی/محلی از `StringComparison.CurrentCulture` استفاده کنید.

---

## ۷. StringBuilder

```csharp
using System.Text;

var sb = new StringBuilder();
for (int i = 1; i <= 5; i++)
    sb.Append($"خط {i}\n");

sb.AppendLine("پایان");
string result = sb.ToString();
```

**کی استفاده کنیم:**
- concatenation در loop
- ساخت HTML/JSON/SQL دستی (تا قبل از serializer)

---

## ۸. char

```csharp
char c = 'A';
char.IsDigit('5');    // true
char.IsLetter('ب');   // true
char.ToUpper('a');    // 'A'
```

---

## ۹. مثال واقعی — فرمت فاکتور

```csharp
string customer = "علی احمدی";
double total = 1_250_000;
DateTime date = DateTime.Now;

string invoice = $"""
    ═══════════════════════════
    فاکتور فروش
    مشتری: {customer}
    تاریخ: {date:yyyy/MM/dd}
    ───────────────────────────
    مبلغ کل: {total:N0} تومان
    ═══════════════════════════
    """;
Console.WriteLine(invoice);
```

---

## ۱۰. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_interpolation.cs](./examples/01_interpolation.cs) | $ و format |
| [02_string_methods.cs](./examples/02_string_methods.cs) | متدهای string |
| [03_split_join.cs](./examples/03_split_join.cs) | Split/Join |
| [04_stringbuilder.cs](./examples/04_stringbuilder.cs) | StringBuilder |

---

## ۱۱. اشتباهات رایج

| اشتباه | مشکل |
|--------|------|
| `+` در loop بزرگ | performance — GC pressure |
| `== null` برای object | OK؛ برای value types متفاوت |
| فراموش Trim ورودی کاربر | `" 5 "` parse fail |
| Substring index اشتباه | ArgumentOutOfRange |
| مقایسه case-sensitive | `"Yes" != "yes"` |

---

## ۱۲. بهترین شیوه‌ها

1. **Interpolation** برای format
2. **Trim** ورودی کاربر
3. **StringBuilder** برای build تدریجی
4. **`IsNullOrWhiteSpace`** قبل از پردازش
5. **`StringComparison`** explicit در Equals

---

## ۱۳. تمرین کلاسی

1. reverse رشته با loop
2. شمارش واژه‌ها با Split
3. mask کردن ایمیل: `a***@gmail.com`
4. StringBuilder — ساخت جدول ASCII

**تکلیف:** [question.md](./exercises/question.md)

---

## ۱۴. خلاصه

- ✅ string immutable
- ✅ `$""` interpolation
- ✅ Split/Join, Trim, Replace
- ✅ StringBuilder برای loop

**جلسه قبل:** [۷](../session-07-arrays/) | **بعد:** [۹](../session-09-null-nullable/)
