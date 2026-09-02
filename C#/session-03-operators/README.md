# جلسه ۳: عملگرها (Operators) (۱.۵ ساعت)

## 🎯 اهداف یادگیری

- استفاده از عملگرهای حسابی، مقایسه‌ای و منطقی
- درک اولویت عملگرها (Operator Precedence)
- کار با عملگرهای ترکیبی (`+=`, `++`, ...)
- استفاده از عملگر سه‌تایی (`?:`)
- نوشتن عبارت‌های readable برای تصمیم‌گیری

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | مرور متغیرها |
| 10–25 | عملگرهای حسابی |
| 25–40 | مقایسه‌ای و منطقی |
| 40–55 | ترکیبی و increment |
| 55–75 | ternary + تمرین |
| 75–90 | پروژه کوچک + تکلیف |

---

## ۱. عملگرهای حسابی

```csharp
int a = 10, b = 3;

Console.WriteLine(a + b);  // 13
Console.WriteLine(a - b);  // 7
Console.WriteLine(a * b);  // 30
Console.WriteLine(a / b);  // 3  (تقسیم صحیح!)
Console.WriteLine(a % b);  // 1  (باقیمانده)
```

### ⚠️ تقسیم int

```csharp
Console.WriteLine(10 / 4);      // 2 نه 2.5
Console.WriteLine(10 / 4.0);    // 2.5
Console.WriteLine(10 / (double)4); // 2.5
```

---

## ۲. عملگرهای مقایسه‌ای

```csharp
int x = 5, y = 8;

Console.WriteLine(x == y);  // false
Console.WriteLine(x != y);  // true
Console.WriteLine(x < y);   // true
Console.WriteLine(x >= 5);  // true
```

**نتیجه:** همیشه `bool`

---

## ۳. عملگرهای منطقی

```csharp
bool a = true, b = false;

Console.WriteLine(a && b);  // AND → false
Console.WriteLine(a || b);  // OR  → true
Console.WriteLine(!a);      // NOT → false
```

### Short-circuit

```csharp
bool ok = false && ExpensiveCheck(); // ExpensiveCheck اجرا نمی‌شود
```

---

## ۴. عملگرهای ترکیبی و Increment

```csharp
int score = 10;
score += 5;   // score = score + 5  → 15
score++;      // postfix → 16
++score;      // prefix  → 17
```

| عملگر | معادل |
|--------|--------|
| `x += 2` | `x = x + 2` |
| `x -= 2` | `x = x - 2` |
| `x *= 2` | `x = x * 2` |
| `x /= 2` | `x = x / 2` |
| `x %= 2` | `x = x % 2` |

---

## ۵. عملگر سه‌تایی (Ternary)

```csharp
int age = 20;
string status = age >= 18 ? "بزرگسال" : "نوجوان";
Console.WriteLine(status);
```

**کاربرد:** جایگزین کوتاه `if/else` برای مقداردهی

---

## ۶. اولویت عملگرها

```
()  →  */%  →  +-  →  < > <= >=  →  == !=  →  &&  →  ||
```

```csharp
int result = 2 + 3 * 4; // 14 نه 20
```

**نکته حرفه‌ای:** برای خوانایی از پرانتز استفاده کنید.

---

## ۷. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_arithmetic.cs](./examples/01_arithmetic.cs) | حسابی |
| [02_comparison_logical.cs](./examples/02_comparison_logical.cs) | مقایسه و منطق |
| [03_compound_increment.cs](./examples/03_compound_increment.cs) | += و ++ |
| [04_ternary_precedence.cs](./examples/04_ternary_precedence.cs) | ternary و اولویت |

---

## ۸. مثال واقعی — تخفیف فروشگاه

```csharp
double price = 250_000;
bool isMember = true;

double finalPrice = isMember ? price * 0.9 : price;
Console.WriteLine($"قیمت نهایی: {finalPrice:N0} تومان");
```

---

## ۹. اشتباهات رایج

| اشتباه | مشکل |
|--------|------|
| `if (x = 5)` | `=` انتساب است نه مقایسه |
| `10 / 4` برای اعشار | تقسیم صحیح |
| `a ++ b` | syntax نامعتبر |

---

## ۱۰. تمرین کلاسی

1. زوج/فرد بودن عدد
2. بزرگترین دو عدد بدون `Math.Max`
3. ماشین‌حساب ساده با char operator (+,-,*,/)

**تکلیف:** [question.md](./exercises/question.md)

**جلسه قبل:** [۲](../session-02-variables-types/) | **بعد:** [۴](../session-04-control-flow/)
