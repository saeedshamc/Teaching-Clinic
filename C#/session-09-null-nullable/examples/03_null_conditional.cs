// ============================================================
// جلسه ۹ — Null-Conditional (?.) و ?[]
// ============================================================

// --- ?. روی string ---
string? text = null;
Console.WriteLine($"Length: {text?.Length?.ToString() ?? "null"}");

text = "سلام";
Console.WriteLine($"Length: {text?.Length}");

Console.WriteLine();

// --- ?. روی object chain ---
class Address { public string? City { get; set; } }
class Person { public string? Name { get; set; } public Address? Address { get; set; } }

Person? person = null;
string? city = person?.Address?.City;
Console.WriteLine($"شهر: {city ?? "نامشخص"}");

person = new Person { Name = "علی", Address = new Address { City = "تهران" } };
Console.WriteLine($"شهر: {person?.Address?.City ?? "نامشخص"}");

Console.WriteLine();

// --- ?. با method ---
person?.GetType();  // null-safe — crash نمی‌کند

void PrintName(Person? p) => Console.WriteLine(p?.Name ?? "(بدون نام)");
PrintName(null);
PrintName(person);

Console.WriteLine();

// --- ?[] ---
int[]? numbers = null;
int? first = numbers?[0];
Console.WriteLine($"first: {first?.ToString() ?? "null"}");

numbers = new[] { 10, 20, 30 };
Console.WriteLine($"first: {numbers?[0]}");

Console.WriteLine();

// --- الگوی طلایی: ?. + ?? ---
Person? user = new Person { Name = "مریم" };
string displayName = user?.Name?.Trim() ?? "کاربر ناشناس";
string displayCity = user?.Address?.City ?? "شهر نامشخص";
Console.WriteLine($"{displayName} — {displayCity}");

Console.WriteLine();

// --- ?. در event (مفهومی) ---
Action? handler = null;
handler?.Invoke();  // اگر null → هیچ

handler = () => Console.WriteLine("Event fired!");
handler?.Invoke();
