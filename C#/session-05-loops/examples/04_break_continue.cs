// ============================================================
// جلسه ۵ — break و continue
// ============================================================

// --- مثال ۱: break — یافتن اولین عدد بزرگتر از 50 ---
Console.WriteLine("=== break ===");
int[] numbers = { 12, 34, 56, 78, 23, 91, 45 };

foreach (int n in numbers)
{
    if (n > 50)
    {
        Console.WriteLine($"اولین عدد > 50: {n}");
        break;  // بقیه بررسی نمی‌شوند
    }
}

Console.WriteLine();

// --- مثال ۲: continue — فقط اعداد فرد ---
Console.WriteLine("=== continue — فردها 1-10 ===");
for (int i = 1; i <= 10; i++)
{
    if (i % 2 == 0)
        continue;  // زوج‌ها skip
    Console.Write($"{i} ");
}
Console.WriteLine("\n");

// --- مثال ۳: break در جستجو ---
Console.WriteLine("=== جستجوی نام ===");
string[] users = { "علی", "مریم", "رضا", "سارا" };
string target = "رضا";
bool found = false;

foreach (string user in users)
{
    if (user == target)
    {
        found = true;
        break;
    }
}
Console.WriteLine(found ? $"✅ {target} پیدا شد" : "❌ پیدا نشد");

Console.WriteLine();

// --- مثال ۴: continue — رد کردن مقادیر نامعتبر ---
Console.WriteLine("=== فقط مثبت‌ها ===");
int[] data = { 5, -3, 8, 0, -1, 12, 7 };
int positiveSum = 0;

foreach (int val in data)
{
    if (val <= 0)
        continue;
    positiveSum += val;
}
Console.WriteLine($"جمع مثبت‌ها: {positiveSum}"); // 5+8+12+7 = 32
