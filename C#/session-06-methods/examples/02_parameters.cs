// ============================================================
// جلسه 6 — متدها
// فایل: 02_parameters.cs
// ============================================================
void Greet(string name, int times = 1)
{
    for (int i = 0; i < times; i++) Console.WriteLine($"سلام {name}");
}
Greet("Ali", 2);
