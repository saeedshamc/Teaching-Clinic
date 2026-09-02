// ============================================================
// تمرین ۱ جلسه ۴۵ — Login Flow (پاسخ نمونه)
// ============================================================

class UserAccount { public string Username { get; set; } = ""; public string Password { get; set; } = ""; public string Token { get; set; } = ""; }

class AuthService
{
    private readonly List<UserAccount> _users = new();

    public void Register(string username, string password)
    {
        _users.Add(new UserAccount { Username = username, Password = password });
        Console.WriteLine($"  Registered: {username}");
    }

    public (bool ok, string? token) Login(string username, string password)
    {
        var user = _users.FirstOrDefault(u => u.Username == username && u.Password == password);
        if (user is null) return (false, null);
        user.Token = $"tok-{Guid.NewGuid():N}"[..16];
        return (true, user.Token);
    }
}

var auth = new AuthService();
auth.Register("ali", "pass123");
var (ok, token) = auth.Login("ali", "pass123");
Console.WriteLine(ok ? $"  ✓ Token: {token}" : "  ✗ Failed");
Console.WriteLine(auth.Login("ali", "wrong").ok ? "" : "  ✗ Wrong password");
