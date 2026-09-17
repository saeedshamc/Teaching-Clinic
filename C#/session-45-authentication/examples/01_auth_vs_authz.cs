// ============================================================
// جلسه ۴۵ — Authentication vs Authorization
// ============================================================

class User { public string Id { get; set; } = ""; public string Name { get; set; } = ""; public string Role { get; set; } = ""; }

class AuthService
{
    private readonly Dictionary<string, (User user, string password)> _users = new()
    {
        ["admin"] = (new User { Id = "1", Name = "Admin", Role = "Admin" }, "admin123"),
        ["doctor"] = (new User { Id = "2", Name = "Dr. Ali", Role = "Doctor" }, "doc456"),
        ["guest"] = (new User { Id = "3", Name = "Guest", Role = "Guest" }, "guest")
    };

    public (bool ok, User? user, string? token) Login(string username, string password)
    {
        if (!_users.TryGetValue(username, out var entry) || entry.password != password)
            return (false, null, null);
        var token = $"jwt-{entry.user.Id}-{Guid.NewGuid():N}"[..32];
        return (true, entry.user, token);
    }
}

class AuthzService
{
    private readonly HashSet<string> _adminActions = new() { "delete_patient", "manage_users" };
    private readonly HashSet<string> _doctorActions = new() { "view_patients", "create_appointment" };

    public (bool allowed, int statusCode, string message) Check(User user, string action)
    {
        if (user.Role == "Admin") return (true, 200, "OK");
        if (user.Role == "Doctor" && _doctorActions.Contains(action)) return (true, 200, "OK");
        if (user.Role == "Guest" && action == "view_public") return (true, 200, "OK");
        return (false, 403, "Forbidden");
    }
}

var auth = new AuthService();
var authz = new AuthzService();

Console.WriteLine("=== Authentication ===");
var (ok, user, token) = auth.Login("doctor", "doc456");
Console.WriteLine(ok ? $"  ✓ Logged in: {user!.Name}, token={token}" : "  ✗ 401 Unauthorized");

var (fail, _, _) = auth.Login("doctor", "wrong");
Console.WriteLine(!fail ? "  ✗ 401 Unauthorized (wrong password)" : "");

Console.WriteLine("\n=== Authorization ===");
if (user is not null)
{
    foreach (var action in new[] { "view_patients", "delete_patient", "view_public" })
    {
        var (allowed, code, msg) = authz.Check(user, action);
        Console.WriteLine($"  {action}: {(allowed ? "✓" : $"✗ {code} {msg}")}");
    }
}
