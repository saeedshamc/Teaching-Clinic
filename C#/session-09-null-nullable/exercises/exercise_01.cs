// ============================================================
// تمرین جلسه 9 — پاسخ نمونه
// ============================================================
bool IsNullOrEmpty(string? value) => string.IsNullOrWhiteSpace(value);
Console.WriteLine(IsNullOrEmpty("  "));
Console.WriteLine(IsNullOrEmpty("ok"));
