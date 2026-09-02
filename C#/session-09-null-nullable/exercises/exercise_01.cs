// ============================================================
// پاسخ نمونه — تمرین‌های کلاسی (۱–۴)
// ============================================================

// ۱. int? parse
Console.Write("سن: ");
string? ageInput = Console.ReadLine();
int? age = string.IsNullOrWhiteSpace(ageInput)
    ? null
    : int.TryParse(ageInput, out int a) ? a : null;
Console.WriteLine(age.HasValue ? $"سن: {age}" : "وارد نشده");

// ۲. ?? default
string? name = null;
Console.WriteLine($"سلام {name ?? "مهمان"}!");

// ۳. ?. chain
class Address { public string? City { get; set; } }
class Person { public Address? Address { get; set; } }
Person? person = new Person { Address = new Address { City = "تهران" } };
Console.WriteLine($"شهر: {person?.Address?.City ?? "نامشخص"}");

// ۴. ??= init
string? cache = null;
cache ??= "default";
Console.WriteLine($"cache: {cache}");
