// ============================================================
// جلسه 38 — Web API
// فایل: 03_dto_concept.cs
// ============================================================
record UserDto(int Id, string Name);
var user = new UserDto(1, "Ali");
Console.WriteLine(user);
