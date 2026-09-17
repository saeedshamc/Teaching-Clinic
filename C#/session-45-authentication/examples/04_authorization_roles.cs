// ============================================================
// جلسه ۴۵ — Authorization: Roles و Policy
// ============================================================

class User { public string Name { get; set; } = ""; public List<string> Roles { get; set; } = new(); }

class AuthorizationService
{
    private readonly Dictionary<string, Func<User, bool>> _policies = new()
    {
        ["CanManagePatients"] = u => u.Roles.Contains("Admin") || u.Roles.Contains("Doctor"),
        ["CanDeleteUsers"] = u => u.Roles.Contains("Admin"),
        ["CanViewReports"] = u => u.Roles.Any(r => r is "Admin" or "Doctor" or "Manager"),
    };

    public bool Authorize(User user, string policy)
        => _policies.TryGetValue(policy, out var check) && check(user);

    public bool IsInRole(User user, string role) => user.Roles.Contains(role);
}

var authz = new AuthorizationService();
var admin = new User { Name = "Admin", Roles = new() { "Admin" } };
var doctor = new User { Name = "Dr. Ali", Roles = new() { "Doctor" } };
var guest = new User { Name = "Guest", Roles = new() { "Guest" } };

void CheckAccess(User user, string policy)
{
    var ok = authz.Authorize(user, policy);
    Console.WriteLine($"  {user.Name} → {policy}: {(ok ? "✓ 200" : "✗ 403")}");
}

Console.WriteLine("=== Role-based ===");
Console.WriteLine($"  Admin is Admin: {authz.IsInRole(admin, "Admin")}");
Console.WriteLine($"  Doctor is Admin: {authz.IsInRole(doctor, "Admin")}");

Console.WriteLine("\n=== Policy-based ===");
foreach (var u in new[] { admin, doctor, guest })
    foreach (var p in new[] { "CanManagePatients", "CanDeleteUsers", "CanViewReports" })
        CheckAccess(u, p);
