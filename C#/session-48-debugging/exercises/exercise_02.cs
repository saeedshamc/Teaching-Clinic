// ============================================================
// تمرین ۲ جلسه ۴۸ — Null-Safe GetEmail (پاسخ نمونه)
// ============================================================

class Profile { public string? Email { get; set; } }
class User { public string? Name { get; set; } public Profile? Profile { get; set; } }

string GetEmail(User? user) => user?.Profile?.Email ?? "no-email@unknown";

Console.WriteLine(GetEmail(new User { Profile = new Profile { Email = "ali@test.com" } }));
Console.WriteLine(GetEmail(new User { Name = "Guest" }));
Console.WriteLine(GetEmail(null));
