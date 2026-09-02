// ============================================================
// تمرین جلسه 4 — پاسخ نمونه
// ============================================================
Console.Write("a: "); int.TryParse(Console.ReadLine(), out int a);
Console.Write("b: "); int.TryParse(Console.ReadLine(), out int b);
Console.Write("op (+,-,*,/): "); string? op = Console.ReadLine();
int result = op switch
{
    "+" => a + b,
    "-" => a - b,
    "*" => a * b,
    "/" => b == 0 ? 0 : a / b,
    _ => 0
};
Console.WriteLine($"نتیجه: {result}");
