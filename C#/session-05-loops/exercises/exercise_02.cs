// ============================================================
// پاسخ نمونه — تمرین‌های خانه (۵–۸)
// ============================================================

// ۵. Fibonacci — 15 عدد اول
Console.WriteLine("=== Fibonacci ===");
int a = 0, b = 1;
Console.Write($"{a} {b}");
for (int i = 2; i < 15; i++)
{
    int next = a + b;
    Console.Write($" {next}");
    a = b;
    b = next;
}
Console.WriteLine("\n");

// ۶. عدد اول
Console.Write("N: ");
int.TryParse(Console.ReadLine(), out int num);
bool isPrime = num > 1;
for (int i = 2; i * i <= num; i++)
{
    if (num % i == 0) { isPrime = false; break; }
}
Console.WriteLine(isPrime ? "اول است" : "اول نیست");

// ۷. حدس عدد
Random rng = new();
int secret = rng.Next(1, 101);
int attempts = 0, guess;

Console.WriteLine("عدد 1-100 را حدس بزنید:");
do
{
    Console.Write("حدس: ");
    int.TryParse(Console.ReadLine(), out guess);
    attempts++;
    if (guess < secret)      Console.WriteLine("بیشتر!");
    else if (guess > secret) Console.WriteLine("کمتر!");
} while (guess != secret);
Console.WriteLine($"✅ درست! {attempts} تلاش");

// ۸. مثلث ستاره
Console.WriteLine("\n=== مثلث ===");
for (int row = 1; row <= 5; row++)
{
    for (int star = 1; star <= row; star++)
        Console.Write("*");
    Console.WriteLine();
}
