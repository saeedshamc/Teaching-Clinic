# جلسه ۵: حلقه‌ها (Loops) (۱.۵ ساعت)

## 🎯 اهداف یادگیری

- استفاده از حلقه `for` برای تکرار با شمارنده مشخص
- کار با `while` و `do-while` برای تکرار شرطی
- پیمایش مجموعه‌ها با `foreach`
- کنترل جریان با `break` و `continue`
- انتخاب حلقه مناسب برای هر سناریو
- جلوگیری از حلقه بی‌نهایت و off-by-one errors

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | مرور شرط‌ها — چرا حلقه؟ |
| 10–30 | `for` — شمارش، جدول ضرب |
| 30–45 | `while` و `do-while` |
| 45–60 | `foreach` — پیمایش آرایه و List |
| 60–75 | `break` / `continue` — تمرین |
| 75–90 | پروژه mini + تکلیف |

---

## ۱. چرا حلقه؟

بدون حلقه:
```csharp
Console.WriteLine(1);
Console.WriteLine(2);
Console.WriteLine(3);
// ... ۱۰۰ بار copy-paste 😱
```

با حلقه:
```csharp
for (int i = 1; i <= 100; i++)
    Console.WriteLine(i);
```

**اصل DRY:** Don't Repeat Yourself

---

## ۲. حلقه `for`

### ۲.۱. ساختار

```csharp
for (مقداردهی; شرط; افزایش)
{
    // بدنه
}
```

```csharp
for (int i = 0; i < 5; i++)
{
    Console.WriteLine($"شمارش: {i}");
}
// خروجی: 0, 1, 2, 3, 4
```

### ۲.۲. اجزای for

| بخش | نقش | مثال |
|-----|-----|------|
| Init | یک‌بار در ابتدا | `int i = 0` |
| Condition | قبل از هر iteration | `i < 5` |
| Increment | بعد از هر iteration | `i++` |

### ۲.۳. حلقه نزولی

```csharp
for (int i = 10; i >= 1; i--)
    Console.Write($"{i} ");
// 10 9 8 ... 1
```

### ۲.۴. گام دلخواه

```csharp
for (int i = 0; i <= 20; i += 2)
    Console.Write($"{i} ");
// 0 2 4 ... 20
```

### ۲.۵. حلقه تو در تو (Nested)

```csharp
for (int row = 1; row <= 3; row++)
{
    for (int col = 1; col <= 3; col++)
        Console.Write($"({row},{col}) ");
    Console.WriteLine();
}
```

**کاربرد:** جدول ضرب، ماتریس، pattern

---

## ۳. حلقه `while`

```csharp
int count = 0;
while (count < 5)
{
    Console.WriteLine(count);
    count++;
}
```

**تفاوت با for:** وقتی **تعداد تکرار از قبل مشخص نیست**.

```csharp
Console.Write("عدد (0 برای خروج): ");
int.TryParse(Console.ReadLine(), out int n);

while (n != 0)
{
    Console.WriteLine($"مربع: {n * n}");
    Console.Write("عدد بعدی: ");
    int.TryParse(Console.ReadLine(), out n);
}
```

### ⚠️ حلقه بی‌نهایت

```csharp
int i = 0;
while (i < 5)
{
    Console.WriteLine(i);
    // فراموش کردن i++ → بی‌نهایت!
}
```

---

## ۴. حلقه `do-while`

```csharp
int choice;
do
{
    Console.WriteLine("1. ادامه  0. خروج");
    int.TryParse(Console.ReadLine(), out choice);
} while (choice != 0);
```

**تفاوت:** بدنه **حداقل یک‌بار** اجرا می‌شود — حتی اگر شرط false باشد.

| حلقه | بررسی شرط |
|------|-----------|
| `while` | **قبل** بدنه |
| `do-while` | **بعد** بدنه |

**کاربرد:** منو — حداقل یک‌بار منو نمایش داده شود.

---

## ۵. حلقه `foreach`

```csharp
string[] names = { "علی", "مریم", "رضا" };

foreach (string name in names)
{
    Console.WriteLine($"سلام {name}!");
}
```

### قوانین foreach

- ✅ روی `IEnumerable` / آرایه / List
- ❌ نمی‌توان متغیر loop را تغییر داد (`name = "x"` خطاست)
- ❌ نمی‌توان index مستقیم داشت — از `for` استفاده کنید

```csharp
// foreach با index — از for استفاده کنید
for (int i = 0; i < names.Length; i++)
    Console.WriteLine($"{i}: {names[i]}");
```

---

## ۶. `break` و `continue`

### ۶.۱. `break` — خروج از حلقه

```csharp
for (int i = 1; i <= 100; i++)
{
    if (i == 7)
        break;  // حلقه تمام — i=7 چاپ نمی‌شود اگر break قبل print باشد
    Console.WriteLine(i);
}
```

### ۶.۲. `continue` — رد شدن از iteration فعلی

```csharp
for (int i = 1; i <= 10; i++)
{
    if (i % 2 == 0)
        continue;  // زوج‌ها skip
    Console.WriteLine(i);  // فقط فرد: 1,3,5,7,9
}
```

### ۶.۳. break در nested loop

`break` فقط **یک سطح** حلقه را می‌شکند. برای خروج کامل از چند لایه از `goto` یا flag استفاده کنید (یا refactor).

---

## ۷. انتخاب حلقه مناسب

| سناریو | حلقه |
|--------|------|
| تعداد مشخص (N بار) | `for` |
| تا وقتی شرط برقرار است | `while` |
| حداقل یک‌بار + تکرار | `do-while` |
| پیمایش مجموعه | `foreach` |
| نیاز به index | `for` |

---

## ۸. مثال واقعی — جدول ضرب و جمع

```csharp
// جدول ضرب 1 تا 5
for (int i = 1; i <= 5; i++)
{
    for (int j = 1; j <= 5; j++)
        Console.Write($"{i * j,4}");
    Console.WriteLine();
}

// جمع اعداد 1 تا 100
int sum = 0;
for (int i = 1; i <= 100; i++)
    sum += i;
Console.WriteLine($"جمع: {sum}"); // 5050
```

---

## ۹. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_for_loop.cs](./examples/01_for_loop.cs) | for و nested |
| [02_while_do.cs](./examples/02_while_do.cs) | while و do-while |
| [03_foreach.cs](./examples/03_foreach.cs) | foreach |
| [04_break_continue.cs](./examples/04_break_continue.cs) | break/continue |

---

## ۱۰. اشتباهات رایج

| اشتباه | مشکل |
|--------|------|
| `for (int i = 0; i <= arr.Length)` | IndexOutOfRange — باید `<` باشد |
| فراموش کردن increment در while | حلقه بی‌نهایت |
| تغییر متغیر foreach | خطای کامپایل |
| off-by-one (`<` vs `<=`) | یک iteration کم/زیاد |
| break در switch داخل for | فقط switch را می‌شکند نه for |

---

## ۱۱. بهترین شیوه‌ها

1. **نام متغیر loop:** `i`, `j` برای index؛ `item`, `name` در foreach
2. **طول مشخص:** `for (int i = 0; i < items.Length; i++)`
3. **foreach** برای read-only iteration
4. **do-while** فقط وقتی حداقل یک اجرا لازم است
5. **از break زیاد** استفاده نکنید — گاهی flag خواناتر است

---

## ۱۲. تمرین کلاسی

1. چاپ اعداد ۱ تا ۲۰
2. جدول ضرب عدد ۷
3. جمع اعداد زوج ۱ تا ۱۰۰
4. پیدا کردن اولین عدد اول بزرگتر از ۱۰۰

**تکلیف:** [question.md](./exercises/question.md)

---

## ۱۳. خلاصه

- ✅ `for` — تکرار با شمارنده
- ✅ `while` / `do-while` — تکرار شرطی
- ✅ `foreach` — پیمایش مجموعه
- ✅ `break` / `continue` — کنترل جریان

**جلسه قبل:** [۴](../session-04-control-flow/) | **بعد:** [۶](../session-06-methods/)
