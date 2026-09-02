// ============================================================
// جلسه ۱۲ — فاز ۱: منو و Loop
// ============================================================

void ShowMenu()
{
    Console.WriteLine("╔══════════════════════════════╗");
    Console.WriteLine("║     ماشین‌حساب CLI           ║");
    Console.WriteLine("╠══════════════════════════════╣");
    Console.WriteLine("║  1. جمع (+)                  ║");
    Console.WriteLine("║  2. تفریق (-)                ║");
    Console.WriteLine("║  3. ضرب (*)                  ║");
    Console.WriteLine("║  4. تقسیم (/)                ║");
    Console.WriteLine("║  0. خروج                     ║");
    Console.WriteLine("╚══════════════════════════════╝");
}

int GetChoice()
{
    Console.Write("انتخاب: ");
    int.TryParse(Console.ReadLine(), out int choice);
    return choice;
}

// --- Main loop ---
bool running = true;

while (running)
{
    ShowMenu();
    int choice = GetChoice();

    switch (choice)
    {
        case 1:
            Console.WriteLine("→ عملیات جمع (فاز بعدی)");
            break;
        case 2:
            Console.WriteLine("→ عملیات تفریق (فاز بعدی)");
            break;
        case 3:
            Console.WriteLine("→ عملیات ضرب (فاز بعدی)");
            break;
        case 4:
            Console.WriteLine("→ عملیات تقسیم (فاز بعدی)");
            break;
        case 0:
            running = false;
            Console.WriteLine("خداحافظ! 👋");
            break;
        default:
            Console.WriteLine("❌ گزینه نامعتبر");
            break;
    }

    if (running)
        Console.WriteLine();
}

// نکته: do-while هم برای منو مناسب است:
// do { ShowMenu(); ... } while (choice != 0);
