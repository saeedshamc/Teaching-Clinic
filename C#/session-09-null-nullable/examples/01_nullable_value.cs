// ============================================================
// جلسه ۹ — Nullable Value Types
// ============================================================

// --- int? ---
int? age = null;
Console.WriteLine($"age = {(age.HasValue ? age.Value.ToString() : "null")}");

age = 25;
Console.WriteLine($"age = {age}");

Console.WriteLine();

// --- bool? — سه حالت ---
bool? answer = null;
Console.WriteLine($"null: {answer?.ToString() ?? "پاسخ داده نشده"}");

answer = true;
Console.WriteLine($"true: {answer}");

answer = false;
Console.WriteLine($"false: {answer}");

Console.WriteLine();

// --- Nullable در عمل — parse اختیاری ---
Console.Write("سن (Enter برای رد): ");
string? input = Console.ReadLine();

int? userAge = string.IsNullOrWhiteSpace(input)
    ? null
    : int.TryParse(input, out int parsed) ? parsed : null;

if (userAge.HasValue)
    Console.WriteLine($"سن: {userAge.Value}");
else
    Console.WriteLine("سن وارد نشد");

Console.WriteLine();

// --- GetValueOrDefault ---
int? score = null;
Console.WriteLine($"score: {score.GetValueOrDefault(-1)}"); // -1

score = 85;
Console.WriteLine($"score: {score.GetValueOrDefault(-1)}"); // 85

Console.WriteLine();

// --- مقایسه null vs zero ---
int? nullableAge = null;
int zeroAge = 0;
Console.WriteLine($"null == 0? {nullableAge == zeroAge}"); // false
Console.WriteLine($"null.HasValue? {nullableAge.HasValue}"); // false
