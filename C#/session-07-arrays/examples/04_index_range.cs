// ============================================================
// جلسه ۷ — Index (^) و Range (..)
// ============================================================

int[] arr = { 10, 20, 30, 40, 50, 60, 70 };

// --- Index از انتها ---
Console.WriteLine($"arr[^1] = {arr[^1]}"); // 70 — آخرین
Console.WriteLine($"arr[^2] = {arr[^2]}"); // 60
Console.WriteLine($"arr[0] = {arr[0]}, arr[^arr.Length] = {arr[^arr.Length]}"); // هر دو 10

Console.WriteLine();

// --- Range: slice ---
int[] slice1 = arr[1..4];    // { 20, 30, 40 }
int[] slice2 = arr[..3];     // { 10, 20, 30 }
int[] slice3 = arr[3..];     // { 40, 50, 60, 70 }
int[] slice4 = arr[..];      // کپی کامل
int[] slice5 = arr[2..^1];   // { 30, 40, 50, 60 } — از index 2 تا یکی مانده به آخر

Console.WriteLine("arr[1..4]:  " + string.Join(", ", slice1));
Console.WriteLine("arr[..3]:   " + string.Join(", ", slice2));
Console.WriteLine("arr[3..]:   " + string.Join(", ", slice3));
Console.WriteLine("arr[2..^1]: " + string.Join(", ", slice5));

Console.WriteLine();

// --- Range روی string ---
string text = "برنامه‌نویسی C#";
Console.WriteLine(text[..7]);    // "برنامه‌"
Console.WriteLine(text[^3..]);   // " C#"
Console.WriteLine(text[3..8]);   // "امه‌ن"

Console.WriteLine();

// --- کاربرد: سه عنصر وسط ---
int[] numbers = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
int midStart = numbers.Length / 2 - 1;
int[] middle = numbers[midStart..(midStart + 3)];
Console.WriteLine("وسط: " + string.Join(", ", middle)); // 4, 5, 6

// --- معکوس با Range (ساده) ---
int[] reversed = arr[..];
Array.Reverse(reversed);
Console.WriteLine("معکوس: " + string.Join(", ", reversed));
