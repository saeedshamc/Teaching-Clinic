// ============================================================
// جلسه 9 — Null و Nullable Types
// فایل: 02_null_coalescing.cs
// ============================================================
string? nickname = null;
string display = nickname ?? "کاربر";
Console.WriteLine(display);
