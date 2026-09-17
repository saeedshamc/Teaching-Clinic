// ============================================================
// جلسه ۹ — Nullable Reference Types (NRT)
// ============================================================
#nullable enable

// --- non-nullable vs nullable ---
string required = "علی";       // نمی‌توان null assign کرد (warning)
string? optional = null;       // مجاز

Console.WriteLine($"required: {required}");
Console.WriteLine($"optional: {optional ?? "(null)"}");

Console.WriteLine();

// --- warning example (commented) ---
// string name = null;  // ⚠️ CS8600 warning

// --- null check قبل از use ---
void PrintLength(string text)  // non-nullable parameter
{
    Console.WriteLine($"طول '{text}': {text.Length}");
}

string? input = GetUserName();
if (input is not null)
    PrintLength(input);

// یا
PrintLength(input ?? "default");

Console.WriteLine();

// --- null-forgiving operator ! ---
string GetConfigValue() => "production";

string env = GetConfigValue();  // compiler می‌داند non-null
// اگر مطمئنید GetMaybeNull null نمی‌دهد:
string? maybe = GetMaybeNull();
string forced = maybe!;  // suppress warning — با احتیاط!

Console.WriteLine();

// --- Guard clause pattern ---
void ProcessOrder(string? orderId)
{
    if (string.IsNullOrWhiteSpace(orderId))
    {
        Console.WriteLine("❌ شماره سفارش نامعتبر");
        return;
    }
    Console.WriteLine($"✅ پردازش سفارش: {orderId}");
}

ProcessOrder(null);
ProcessOrder("ORD-12345");

Console.WriteLine();

// --- Helper methods ---
string? GetUserName() => null;
string? GetMaybeNull() => null;

// --- Annotate return ---
string? FindUser(int id) => id > 0 ? "علی" : null;

string? found = FindUser(1);
Console.WriteLine(found ?? "پیدا نشد");

string? notFound = FindUser(-1);
Console.WriteLine(notFound ?? "پیدا نشد");
