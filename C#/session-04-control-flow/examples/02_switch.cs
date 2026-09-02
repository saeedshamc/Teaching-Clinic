// ============================================================
// جلسه ۴ — switch statement
// ============================================================

// --- مثال ۱: منوی ساده ---
Console.WriteLine("=== منو ===");
Console.WriteLine("1. پیتزا");
Console.WriteLine("2. برگر");
Console.WriteLine("3. سالاد");
Console.Write("انتخاب: ");
int.TryParse(Console.ReadLine(), out int choice);

switch (choice)
{
    case 1:
        Console.WriteLine("🍕 پیتزا — ۱۵۰٬۰۰۰ تومان");
        break;
    case 2:
        Console.WriteLine("🍔 برگر — ۱۲۰٬۰۰۰ تومان");
        break;
    case 3:
        Console.WriteLine("🥗 سالاد — ۸۰٬۰۰۰ تومان");
        break;
    default:
        Console.WriteLine("❌ گزینه نامعتبر");
        break;
}

Console.WriteLine();

// --- مثال ۲: switch روی char — عملگر ---
Console.Write("عملگر (+,-,*,/): ");
string? opInput = Console.ReadLine();
char op = string.IsNullOrEmpty(opInput) ? '?' : opInput[0];

switch (op)
{
    case '+':
    case '-':
        Console.WriteLine("عملگر جمع/تفریق");
        break;
    case '*':
    case '/':
        Console.WriteLine("عملگر ضرب/تقسیم");
        break;
    default:
        Console.WriteLine("عملگر ناشناخته");
        break;
}

Console.WriteLine();

// --- مثال ۳: switch روی string — روز هفته ---
Console.Write("روز (شنبه/یکشنبه/...): ");
string? day = Console.ReadLine();

switch (day)
{
    case "شنبه":
    case "یکشنبه":
    case "دوشنبه":
    case "سه‌شنبه":
    case "چهارشنبه":
        Console.WriteLine("📅 روز کاری");
        break;
    case "پنج‌شنبه":
    case "جمعه":
        Console.WriteLine("🏖️ آخر هفته");
        break;
    default:
        Console.WriteLine("⚠️ روز نامعتبر");
        break;
}
