// ============================================================
// جلسه ۲ — Casting
// ============================================================

double pi = 3.99;
int rounded = (int)pi;
Console.WriteLine($"double {pi} → int {rounded}");

// تبدیل string به عدد — روش خطرناک vs امن
string good = "123";
string bad = "abc";

// int n1 = int.Parse(bad); // Exception!

if (int.TryParse(good, out int n2))
    Console.WriteLine($"Parse OK: {n2}");

if (!int.TryParse(bad, out int n3))
    Console.WriteLine("Parse failed for 'abc'");
