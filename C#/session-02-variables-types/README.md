# جلسه ۲: متغیرها و انواع داده (۱.۵ ساعت)

## 🎯 اهداف یادگیری

- تعریف متغیر با انواع پایه C#
- استفاده از `var` و `const`
- تبدیل نوع (implicit/explicit casting)
- استفاده از `TryParse` برای ورودی امن
- درک Value Type در سطح مقدماتی

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | مرور جلسه قبل + پرسش |
| 10–30 | انواع پایه (int, double, bool, string, char, decimal) |
| 30–50 | var, const, naming |
| 50–65 | Casting و TryParse |
| 65–85 | تمرین کلاسی |
| 85–90 | تکلیف |

---

## ۱. متغیر چیست؟

**متغیر** جعبه‌ای در حافظه با **نام** و **نوع** مشخص است.

```csharp
int age = 25;
string name = "Ali";
bool isStudent = true;
```

| بخش | معنی |
|-----|------|
| `int` | نوع (عدد صحیح) |
| `age` | نام متغیر |
| `=` | انتساب |
| `25` | مقدار اولیه |

---

## ۲. انواع پایه (Primitive / Built-in)

| نوع C# | مثال | کاربرد |
|--------|------|--------|
| `int` | `42` | اعداد صحیح |
| `long` | `9999999999L` | اعداد بزرگ |
| `double` | `3.14` | اعشاری (پیش‌فرض) |
| `float` | `3.14f` | اعشاری تک‌دقت |
| `decimal` | `99.99m` | پول و مالی |
| `bool` | `true/false` | منطقی |
| `char` | `'A'` | یک کاراکتر |
| `string` | `"Hello"` | رشته |

### چرا `decimal` برای پول؟

```csharp
double price = 0.1 + 0.2;
Console.WriteLine(price); // 0.30000000000000004 ❌

decimal total = 0.1m + 0.2m;
Console.WriteLine(total); // 0.3 ✅
```

---

## ۳. var — استنتاج نوع

```csharp
var city = "Tehran";   // string
var count = 10;        // int
var pi = 3.14;         // double
```

**قانون:** فقط وقتی نوع از سمت راست واضح است از `var` استفاده کنید.

---

## ۴. const — ثابت

```csharp
const double Pi = 3.14159;
const int MaxUsers = 100;

// Pi = 3.14; // خطا! const قابل تغییر نیست
```

---

## ۵. تبدیل نوع (Casting)

### صریح (Explicit)

```csharp
double d = 9.8;
int n = (int)d; // 9 — اعشار حذف می‌شود
```

### TryParse (امن برای ورودی کاربر)

```csharp
string input = "42";
if (int.TryParse(input, out int number))
{
    Console.WriteLine(number);
}
else
{
    Console.WriteLine("ورودی نامعتبر");
}
```

---

## ۶. String Interpolation

```csharp
string name = "Sara";
int age = 22;
Console.WriteLine($"نام: {name}, سن: {age}");
```

---

## ۷. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_basic_types.cs](./examples/01_basic_types.cs) | انواع پایه |
| [02_var_and_const.cs](./examples/02_var_and_const.cs) | var و const |
| [03_casting.cs](./examples/03_casting.cs) | تبدیل نوع |
| [04_tryparse_input.cs](./examples/04_tryparse_input.cs) | ورودی امن |

---

## ۸. اشتباهات رایج

```csharp
int x = "10";        // ❌ نوع ناسازگار
int y = 10.5;        // ❌ بدون cast
int z = int.Parse("abc"); // ❌ Exception — از TryParse استفاده کنید
```

---

## ۹. تمرین کلاسی

1. دو عدد از کاربر بگیرید، جمع و میانگین چاپ کنید
2. برنامه تبدیل سانتی‌متر به اینچ (1 inch = 2.54 cm)
3. با `const` نرخ مالیات را ثابت نگه دارید

**تکلیف:** [question.md](./exercises/question.md)

**جلسه قبل:** [۱](../session-01-intro/) | **بعد:** [۳](../session-03-operators/)
