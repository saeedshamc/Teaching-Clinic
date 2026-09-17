# جلسه ۹: Null و Nullable Types (۱.۵ ساعت)

## 🎯 اهداف یادگیری

- درک `null` — absence of value
- `NullReferenceException` — علت و پیشگیری
- Nullable Value Types: `int?`, `bool?`, `DateTime?`
- Nullable Reference Types (NRT) — C# 8+
- عملگر `??` (null-coalescing) و `??=`
- عملگر `?.` (null-conditional) و `?[]`
- الگوی `?.` + `??` برای safe access

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | null چیست؟ — reference vs value |
| 10–25 | Nullable value types (`T?`) |
| 25–40 | `??` و `??=` |
| 40–55 | `?.` و `?[]` |
| 55–70 | Nullable Reference Types |
| 70–90 | تمرین + تکلیف |

---

## ۱. null چیست؟

```csharp
string? name = null;   // «هیچ رشته‌ای وجود ندارد»
object? obj = null;
```

**Value types** به‌صورت پیش‌فرض null **نیستند:**

```csharp
int x = null;  // ❌ خطای کامپایل
```

---

## ۲. NullReferenceException

```csharp
string? text = null;
Console.WriteLine(text.Length);  // 💥 NullReferenceException
```

**پیشگیری:** check قبل از access

```csharp
if (text != null)
    Console.WriteLine(text.Length);
```

---

## ۳. Nullable Value Types

```csharp
int? nullableInt = null;
nullableInt = 42;
nullableInt = null;

bool? flag = null;

// HasValue و Value
if (nullableInt.HasValue)
    Console.WriteLine(nullableInt.Value);

// یا
int safe = nullableInt ?? 0;
```

### ۳.۱. چرا nullable value؟

- فیلد اختیاری در database (سن نامشخص)
- parse ناموفق
- «مقدار وجود ندارد» vs «صفر»

```csharp
int? age = null;  // «سن وارد نشده»
int age2 = 0;     // «سن صفر است» — معنی متفاوت!
```

---

## ۴. Null-Coalescing — `??`

```csharp
string? name = null;
string display = name ?? "مهمان";
// اگر name null → "مهمان"
```

### ۴.۱. زنجیره

```csharp
string? a = null, b = null, c = "سلام";
string result = a ?? b ?? c ?? "پیش‌فرض";
```

### ۴.۲. `??=`

```csharp
string? cache = null;
cache ??= "مقدار پیش‌فرض";  // فقط اگر null باشد assign
cache ??= "دیگری";            // اجرا نمی‌شود — cache دیگر null نیست
```

---

## ۵. Null-Conditional — `?.`

```csharp
string? name = null;
int? length = name?.Length;  // null — crash نمی‌کند

Person? person = null;
string? city = person?.Address?.City;
```

### ۵.۱. با method call

```csharp
person?.PrintInfo();  // اگر person null → هیچ
```

### ۵.۲. `?[]` — index

```csharp
int[]? arr = null;
int? first = arr?[0];  // null
```

### ۵.۳. ترکیب با `??`

```csharp
string city = person?.Address?.City ?? "نامشخص";
```

**الگوی طلایی:** `?.` + `??`

---

## ۶. Nullable Reference Types (NRT)

```csharp
#nullable enable

string name = "علی";     // non-nullable — compiler warning اگر null assign
string? optional = null; // nullable
```

**بدون `#nullable enable`:** reference types همیشه nullable هستند (legacy behavior).

### ۶.۱. Null-forgiving `!`

```csharp
string text = GetMaybeNull()!;  // «مطمئنم null نیست»
```

**فقط وقتی واقعاً مطمئنید** — suppress warning

---

## ۷. الگوهای عملی

### ۷.۱. TryParse (از قبل nullable-aware)

```csharp
if (int.TryParse(input, out int n))
    Console.WriteLine(n);
else
    Console.WriteLine("نامعتبر");
```

### ۷.۲. Guard clause

```csharp
void Process(string? data)
{
    if (string.IsNullOrWhiteSpace(data))
        return;
    // data safe here
}
```

### ۷.۳. GetValueOrDefault

```csharp
int? score = null;
int safe = score.GetValueOrDefault(0);
```

---

## ۸. مثال واقعی — پروفایل کاربر

```csharp
string? username = null;
string? email = "user@test.com";
string? phone = null;

Console.WriteLine($"کاربر: {username ?? "ناشناس"}");
Console.WriteLine($"ایمیل: {email ?? "ندارد"}");
Console.WriteLine($"تماس: {phone?.Trim() ?? "ثبت نشده"}");
```

---

## ۹. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_nullable_value.cs](./examples/01_nullable_value.cs) | int?, HasValue |
| [02_null_coalescing.cs](./examples/02_null_coalescing.cs) | ?? و ??= |
| [03_null_conditional.cs](./examples/03_null_conditional.cs) | ?. |
| [04_nullable_reference.cs](./examples/04_nullable_reference.cs) | NRT |

---

## ۱۰. اشتباهات رایج

| اشتباه | مشکل |
|--------|------|
| `.Length` بدون null check | NullReferenceException |
| `int x = nullableInt` بدون check | InvalidOperationException |
| overuse `!` | پنهان کردن باگ |
| `??` با non-nullable left | همیشه left |
| فراموش `#nullable enable` | warnings NRT |

---

## ۱۱. بهترین شیوه‌ها

1. **`?.` + `??`** برای safe chain
2. **Guard clause** در ابتدای متد
3. **`Try` pattern** به‌جای exception
4. **NRT enable** در پروژه جدید
5. **nullable فقط وقتی لازم** — نه همه‌جا

---

## ۱۲. تمرین کلاسی

1. `int?` — parse اختیاری از کاربر
2. `??` — نام پیش‌فرض «مهمان»
3. `?.` — chain روی object تو در تو
4. `??=` — lazy initialization

**تکلیف:** [question.md](./exercises/question.md)

---

## ۱۳. خلاصه

- ✅ null = no value
- ✅ `T?` برای value types
- ✅ `??`, `??=`, `?.`, `?[]`
- ✅ NRT برای compile-time safety

**جلسه قبل:** [۸](../session-08-strings/) | **بعد:** [۱۰](../session-10-exceptions/)
