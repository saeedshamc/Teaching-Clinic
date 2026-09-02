// ============================================================
// پاسخ نمونه — تمرین‌های خانه (۵–۸)
// ============================================================

// ۵. Safe profile
string? username = null;
string? email = "ali@test.com";
string? phone = null;

Console.WriteLine($"کاربر: {username ?? "ناشناس"}");
Console.WriteLine($"ایمیل: {email ?? "ندارد"}");
Console.WriteLine($"تلفن: {phone?.Trim() ?? "ثبت نشده"}");

// ۶. Config loader
var config = new Dictionary<string, string?>
{
    ["host"] = "localhost",
    ["port"] = null,
    ["timeout"] = "30"
};

string host = config.GetValueOrDefault("host") ?? "127.0.0.1";
string port = config.GetValueOrDefault("port") ?? "8080";
string timeout = config.GetValueOrDefault("timeout") ?? "60";
Console.WriteLine($"{host}:{port} timeout={timeout}s");

// ۷. Guard method
bool Validate(string? input) =>
    !string.IsNullOrWhiteSpace(input);

Console.WriteLine($"Validate(null)={Validate(null)}");
Console.WriteLine($"Validate('ok')={Validate("ok")}");

// ۸. Nested null
class Customer { public string? Email { get; set; } }
class Order { public Customer? Customer { get; set; } }

Order? order1 = null;
Order order2 = new() { Customer = new Customer { Email = "a@b.com" } };
Order order3 = new() { Customer = new Customer() };

Console.WriteLine(order1?.Customer?.Email ?? "no-email@local");
Console.WriteLine(order2?.Customer?.Email ?? "no-email@local");
Console.WriteLine(order3?.Customer?.Email ?? "no-email@local");
