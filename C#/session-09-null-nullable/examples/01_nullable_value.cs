// ============================================================
// جلسه 9 — Null و Nullable Types
// فایل: 01_nullable_value.cs
// ============================================================
int? maybe = null;
Console.WriteLine(maybe ?? 0);
maybe = 5;
Console.WriteLine(maybe.Value);
