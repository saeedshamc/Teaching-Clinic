// ============================================================
// تمرین ۱ و ۲ — پاسخ نمونه
// ============================================================

Console.WriteLine("╔════════════════════╗");
Console.WriteLine("║   کارت معرفی       ║");
Console.WriteLine("╠════════════════════╣");
Console.WriteLine("║ نام: سعید          ║");
Console.WriteLine("║ شهر: تهران         ║");
Console.WriteLine("║ هدف: یادگیری C#    ║");
Console.WriteLine("╚════════════════════╝");

Console.WriteLine();

Console.Write("نام شما: ");
string? name = Console.ReadLine();
Console.Write("سن شما: ");
string? age = Console.ReadLine();

Console.WriteLine($"سلام {name}! سن وارد شده: {age}");
