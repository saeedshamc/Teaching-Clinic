// ============================================================
// جلسه ۱۱ — List<T>
// ============================================================

// --- ایجاد و مقداردهی ---
var fruits = new List<string> { "سیب", "موز", "پرتقال" };
Console.WriteLine($"تعداد: {fruits.Count}");

// --- Add / Insert ---
fruits.Add("انگور");
fruits.Insert(0, "هلو");
Console.WriteLine(string.Join(", ", fruits));

Console.WriteLine();

// --- Remove ---
fruits.Remove("موز");
fruits.RemoveAt(0);
Console.WriteLine(string.Join(", ", fruits));

Console.WriteLine();

// --- Contains / IndexOf ---
Console.WriteLine($"Contains سیب: {fruits.Contains("سیب")}");
Console.WriteLine($"IndexOf پرتقال: {fruits.IndexOf("پرتقال")}");

Console.WriteLine();

// --- Sort / Reverse ---
var numbers = new List<int> { 5, 2, 8, 1, 9, 3 };
numbers.Sort();
Console.WriteLine("Sort: " + string.Join(", ", numbers));
numbers.Reverse();
Console.WriteLine("Reverse: " + string.Join(", ", numbers));

Console.WriteLine();

// --- foreach ---
Console.WriteLine("=== foreach ===");
foreach (string f in fruits)
    Console.WriteLine($"  🍎 {f}");

Console.WriteLine();

// --- List<int> — جمع و فیلتر دستی ---
var scores = new List<int> { 85, 92, 78, 96, 88, 45 };
int sum = 0;
foreach (int s in scores) sum += s;
Console.WriteLine($"میانگین: {(double)sum / scores.Count:F1}");

var passed = new List<int>();
foreach (int s in scores)
    if (s >= 50) passed.Add(s);
Console.WriteLine($"قبولی‌ها: {string.Join(", ", passed)}");

Console.WriteLine();

// --- ToArray ---
int[] arr = numbers.ToArray();
Console.WriteLine($"Array length: {arr.Length}");
