// ============================================================
// جلسه ۲۱ — Generic Methods
// فایل: 02_generic_methods.cs
// ============================================================

static void Swap<T>(ref T a, ref T b) => (a, b) = (b, a);

static T FindMax<T>(T a, T b) where T : IComparable<T>
    => a.CompareTo(b) > 0 ? a : b;

static void PrintArray<T>(T[] array)
{
    Console.Write($"[{typeof(T).Name}] ");
    Console.WriteLine(string.Join(", ", array));
}

static int CountIf<T>(IEnumerable<T> items, Func<T, bool> predicate)
    => items.Count(predicate);

// Swap
int x = 10, y = 20;
Console.WriteLine($"قبل: x={x}, y={y}");
Swap(ref x, ref y);
Console.WriteLine($"بعد: x={x}, y={y}");

string s1 = "apple", s2 = "zebra";
Swap(ref s1, ref s2);
Console.WriteLine($"رشته: {s1}, {s2}");

// FindMax
Console.WriteLine($"Max int: {FindMax(5, 12)}");
Console.WriteLine($"Max string: {FindMax("banana", "apple")}");
Console.WriteLine($"Max double: {FindMax(3.14, 2.71)}");

// PrintArray
PrintArray(new[] { 1, 2, 3, 4, 5 });
PrintArray(new[] { "علی", "مریم", "رضا" });

// CountIf
var numbers = new[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
Console.WriteLine($"زوج‌ها: {CountIf(numbers, n => n % 2 == 0)}");
Console.WriteLine($"بزرگتر از 5: {CountIf(numbers, n => n > 5)}");
