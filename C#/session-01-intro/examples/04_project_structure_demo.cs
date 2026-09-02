// ============================================================
// جلسه ۱ — شبیه‌سازی ساختار یک برنامه واقعی
// فایل: 04_project_structure_demo.cs
// ============================================================

// در پروژه واقعی معمولاً منطق را به متدها تقسیم می‌کنیم (جلسه ۶)

PrintHeader();
PrintBody();
PrintFooter();

// --- متدهای کمکی ---
void PrintHeader()
{
    Console.WriteLine("╔══════════════════════════╗");
    Console.WriteLine("║   MyFirstApp - Demo      ║");
    Console.WriteLine("╚══════════════════════════╝");
}

void PrintBody()
{
    Console.WriteLine("این بدنه برنامه است.");
    Console.WriteLine("در جلسات بعد: متغیر، شرط، حلقه...");
}

void PrintFooter()
{
    Console.WriteLine("--- پایان ---");
}
