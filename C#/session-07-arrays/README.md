# جلسه ۷: آرایه‌ها (Arrays) (۱.۵ ساعت)

## 🎯 اهداف یادگیری

- تعریف و مقداردهی آرایه یک‌بعدی (1D)
- دسترسی با index، `Length`، و iteration
- آرایه دو‌بعدی (2D) — ماتریس
- آرایه jagged — آرایه‌ای از آرایه‌ها
- Index و Range (C# 8+) — `^`, `..`
- تفاوت array و collection — cuándo usar cuál

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | مرور حلقه و foreach |
| 10–30 | آرایه 1D — تعریف، دسترسی، loop |
| 30–45 | آرایه 2D — ماتریس |
| 45–55 | Jagged arrays |
| 55–70 | Index ^ و Range .. |
| 70–90 | تمرین + تکلیف |

---

## ۱. آرایه یک‌بعدی

### ۱.۱. تعریف

```csharp
// روش ۱: مقداردهی مستقیم
int[] numbers = { 10, 20, 30, 40, 50 };

// روش ۲: اندازه + مقداردهی بعدی
string[] names = new string[3];
names[0] = "علی";
names[1] = "مریم";
names[2] = "رضا";

// روش ۳: اندازه با default
int[] zeros = new int[5];  // همه 0
```

### ۱.۲. دسترسی — Index از ۰

```csharp
int[] arr = { 100, 200, 300 };
Console.WriteLine(arr[0]);  // 100
Console.WriteLine(arr[2]);  // 300
arr[1] = 250;               // تغییر
```

**⚠️ IndexOutOfRangeException:** `arr[3]` برای Length=3

### ۱.۳. Length

```csharp
int len = arr.Length;  // 3 — property نه method
```

### ۱.۴. Iteration

```csharp
for (int i = 0; i < arr.Length; i++)
    Console.WriteLine(arr[i]);

foreach (int n in arr)
    Console.WriteLine(n);
```

---

## ۲. آرایه دو‌بعدی (2D)

```csharp
int[,] matrix = {
    { 1, 2, 3 },
    { 4, 5, 6 },
    { 7, 8, 9 }
};

Console.WriteLine(matrix[0, 0]); // 1
Console.WriteLine(matrix[2, 1]); // 8

int rows = matrix.GetLength(0); // 3
int cols = matrix.GetLength(1); // 3
```

### پیمایش 2D

```csharp
for (int r = 0; r < matrix.GetLength(0); r++)
{
    for (int c = 0; c < matrix.GetLength(1); c++)
        Console.Write($"{matrix[r, c],4}");
    Console.WriteLine();
}
```

---

## ۳. Jagged Array

```csharp
int[][] jagged = {
    new[] { 1, 2 },
    new[] { 3, 4, 5 },
    new[] { 6 }
};

Console.WriteLine(jagged[0][1]); // 2
Console.WriteLine(jagged[1].Length); // 3
```

**تفاوت:**

| | `int[,]` | `int[][]` |
|---|---------|----------|
| ساختار | مستطیل یکپارچه | ردیف‌های با طول متفاوت |
| syntax | `[row, col]` | `[row][col]` |
| کاربرد | ماتریس | جدول نامتقارن |

---

## ۴. Index و Range (C# 8+)

### ۴.۱. Index از انتها — `^`

```csharp
int[] arr = { 10, 20, 30, 40, 50 };
Console.WriteLine(arr[^1]);  // 50 — آخرین
Console.WriteLine(arr[^2]);  // 40 — یکی مانده به آخر
```

### ۴.۲. Range — slice

```csharp
int[] slice = arr[1..4];   // { 20, 30, 40 }
int[] fromStart = arr[..3]; // { 10, 20, 30 }
int[] toEnd = arr[2..];     // { 30, 40, 50 }
int[] all = arr[..];        // کپی کل
```

**نکته:** slice آرایه **جدید** می‌سازد — original تغییر نمی‌کند (shallow copy).

---

## ۵. Array Methods (Static)

```csharp
int[] nums = { 3, 1, 4, 1, 5 };
Array.Sort(nums);
Array.Reverse(nums);
int idx = Array.IndexOf(nums, 4);
Array.ForEach(nums, n => Console.Write($"{n} "));
```

**جلسه ۱۱:** `List<T>` جایگزین flexible‌تر

---

## ۶. مثال واقعی — نمرات کلاس

```csharp
double[] grades = { 18.5, 16.0, 19.25, 14.5, 17.0 };

double sum = 0;
foreach (double g in grades)
    sum += g;
double avg = sum / grades.Length;

double max = grades[0];
foreach (double g in grades)
    if (g > max) max = g;

Console.WriteLine($"میانگین: {avg:F2}, بالاترین: {max}");
```

---

## ۷. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_array_basics.cs](./examples/01_array_basics.cs) | 1D basics |
| [02_multidimensional.cs](./examples/02_multidimensional.cs) | 2D matrix |
| [03_jagged_arrays.cs](./examples/03_jagged_arrays.cs) | jagged |
| [04_index_range.cs](./examples/04_index_range.cs) | ^ و .. |

---

## ۸. اشتباهات رایج

| اشتباه | مشکل |
|--------|------|
| `arr[arr.Length]` | IndexOutOfRange — max index = Length-1 |
| `int[,]` vs `int[][]` اشتباه | syntax متفاوت |
| فرض Length ثابت بعد resize | array fixed-size |
| مقایسه آرایه با `==` | reference compare نه content |
| `foreach` + modify element | compile error |

---

## ۹. بهترین شیوه‌ها

1. **`for` با index** وقتی index لازم است
2. **`foreach`** برای read-only
3. **`List<T>`** اگر اندازه متغیر — جلسه بعدی collections
4. **نام جمع:** `names`, `scores` نه `name`, `score`
5. **Bounds check** برای ورودی کاربر

---

## ۱۰. تمرین کلاسی

1. میانگین آرایه int
2. چاپ ماتریس 3×3
3. معکوس آرایه با loop
4. slice سه عنصر وسط با Range

**تکلیف:** [question.md](./exercises/question.md)

---

## ۱۱. مقداردهی و default values

```csharp
int[] nums = new int[5];       // { 0, 0, 0, 0, 0 }
bool[] flags = new bool[3];    // { false, false, false }
string[] names = new string[2]; // { null, null }
```

**Value types:** default (0, false)  
**Reference types:** null

---

## ۱۲. کپی آرایه

```csharp
int[] original = { 1, 2, 3 };
int[] copy = (int[])original.Clone();
int[] slice = original[1..];  // C# 8 — shallow copy بخشی
```

**⚠️ shallow copy:** برای reference type elements، reference کپی می‌شود.

---

## ۱۳. آرایه به‌عنوان پارامتر متد

```csharp
double Average(int[] scores)
{
    if (scores.Length == 0) return 0;
    int sum = 0;
    foreach (int s in scores) sum += s;
    return (double)sum / scores.Length;
}
```

**نکته:** آرایه reference type — mutate داخل متد روی original effect دارد.

---

## ۱۴. مثال ترکیبی — جدول امتیاز

```csharp
string[] players = { "علی", "مریم", "رضا" };
int[] wins = { 5, 8, 3 };
int[] losses = { 2, 1, 7 };

for (int i = 0; i < players.Length; i++)
{
    int total = wins[i] + losses[i];
    double winRate = total > 0 ? (double)wins[i] / total * 100 : 0;
    Console.WriteLine($"{players[i]}: {winRate:F0}% برد");
}
```

---

## ۱۵. خلاصه

- ✅ `int[]` — 1D fixed-size
- ✅ `int[,]` — 2D matrix
- ✅ `int[][]` — jagged
- ✅ `^` و `..` — Index/Range
- ✅ Array static methods — Sort, IndexOf

**جلسه بعد:** رشته‌ها — کار با text!

---

**جلسه قبل:** [۶](../session-06-methods/) | **بعد:** [۸](../session-08-strings/)
