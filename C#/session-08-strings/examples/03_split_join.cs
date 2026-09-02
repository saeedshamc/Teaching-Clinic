// ============================================================
// جلسه ۸ — Split و Join
// ============================================================

// --- Split ساده ---
string csv = "علی,مریم,رضا,سارا";
string[] names = csv.Split(',');
Console.WriteLine($"تعداد: {names.Length}");
foreach (string n in names)
    Console.WriteLine($"  → {n}");

Console.WriteLine();

// --- Join ---
string joined = string.Join(" | ", names);
Console.WriteLine(joined);

Console.WriteLine();

// --- Split با چند separator ---
string data = "apple;banana,orange|grape";
string[] fruits = data.Split(new[] { ';', ',', '|' });
Console.WriteLine(string.Join(", ", fruits));

Console.WriteLine();

// --- Split با RemoveEmptyEntries ---
string spaced = "one  two   three";
string[] words = spaced.Split(' ', StringSplitOptions.RemoveEmptyEntries);
Console.WriteLine($"کلمات: {words.Length} → [{string.Join(", ", words)}]");

Console.WriteLine();

// --- Split خطوط ---
string multiline = "خط اول\nخط دوم\nخط سوم";
string[] lines = multiline.Split('\n');
for (int i = 0; i < lines.Length; i++)
    Console.WriteLine($"  {i + 1}: {lines[i]}");

Console.WriteLine();

// --- کاربرد: پارس CSV ساده ---
string productLine = "لپ‌تاپ;15000000;5";
string[] parts = productLine.Split(';');
string productName = parts[0];
double price = double.Parse(parts[1]);
int stock = int.Parse(parts[2]);
Console.WriteLine($"{productName}: {price:N0} تومان — موجودی: {stock}");

Console.WriteLine();

// --- Join برای ساخت مسیر ---
string path = string.Join("/", "home", "user", "documents", "file.txt");
Console.WriteLine(path);
