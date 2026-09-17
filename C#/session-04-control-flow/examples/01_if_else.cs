// ============================================================
// جلسه ۴ — ساختار if/else و else if
// ============================================================

// --- مثال ۱: قبول/مردود ---
Console.Write("نمره: ");
int.TryParse(Console.ReadLine(), out int score);

if (score >= 50)
    Console.WriteLine("✅ قبول");
else
    Console.WriteLine("❌ مردود");

Console.WriteLine();

// --- مثال ۲: else if — رتبه‌بندی ---
string grade;
if (score >= 90)      grade = "A — عالی";
else if (score >= 75) grade = "B — خوب";
else if (score >= 50) grade = "C — قابل قبول";
else                  grade = "F — مردود";

Console.WriteLine($"رتبه: {grade}");
Console.WriteLine();

// --- مثال ۳: شرط ترکیبی ---
Console.Write("سن: ");
int.TryParse(Console.ReadLine(), out int age);
Console.Write("کارت عضویت دارید؟ (y/n): ");
bool hasCard = Console.ReadLine()?.ToLower() == "y";

if (age >= 18 && hasCard)
    Console.WriteLine("🎫 تخفیف ۲۰٪");
else if (age >= 18)
    Console.WriteLine("🎫 تخفیف ۱۰٪");
else
    Console.WriteLine("⛔ تخفیف ندارید");

Console.WriteLine();

// --- مثال ۴: اعتبارسنجی ورودی ---
Console.Write("عدد ۱ تا ۱۰: ");
int.TryParse(Console.ReadLine(), out int n);

if (n < 1 || n > 10)
    Console.WriteLine("⚠️ عدد خارج از بازه!");
else if (n % 2 == 0)
    Console.WriteLine($"{n} زوج است");
else
    Console.WriteLine($"{n} فرد است");
