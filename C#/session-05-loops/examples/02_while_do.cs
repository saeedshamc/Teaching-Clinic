// ============================================================
// جلسه ۵ — while و do-while
// ============================================================

// --- مثال ۱: while — شمارش ---
Console.WriteLine("=== while ===");
int count = 0;
while (count < 5)
{
    Console.WriteLine($"count = {count}");
    count++;
}

Console.WriteLine();

// --- مثال ۲: while — ورودی تا صفر ---
Console.WriteLine("=== مربع اعداد (0=خروج) ===");
Console.Write("عدد: ");
int.TryParse(Console.ReadLine(), out int num);

while (num != 0)
{
    Console.WriteLine($"  {num}² = {num * num}");
    Console.Write("عدد: ");
    int.TryParse(Console.ReadLine(), out num);
}

Console.WriteLine();

// --- مثال ۳: do-while — منو ---
Console.WriteLine("=== منو do-while ===");
int choice;
do
{
    Console.WriteLine("  1. گزینه اول");
    Console.WriteLine("  2. گزینه دوم");
    Console.WriteLine("  0. خروج");
    Console.Write("انتخاب: ");
    int.TryParse(Console.ReadLine(), out choice);

    switch (choice)
    {
        case 1: Console.WriteLine("  → اول"); break;
        case 2: Console.WriteLine("  → دوم"); break;
        case 0: Console.WriteLine("  → خداحافظ!"); break;
        default: Console.WriteLine("  → نامعتبر"); break;
    }
} while (choice != 0);

Console.WriteLine();

// --- مثال ۴: while vs do-while ---
// while: اگر شرط false باشد، بدنه اجرا نمی‌شود
int x = 10;
while (x < 5)
    Console.WriteLine("while: این چاپ نمی‌شود");

// do-while: حداقل یک‌بار اجرا می‌شود
int y = 10;
do
    Console.WriteLine("do-while: این یک‌بار چاپ می‌شود");
while (y < 5);
