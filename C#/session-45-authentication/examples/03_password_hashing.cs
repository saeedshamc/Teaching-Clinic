// ============================================================
// جلسه ۴۵ — Password Hashing
// ============================================================

using System.Security.Cryptography;

// Simple PBKDF2 hash (مشابه ASP.NET Identity)
static (string hash, string salt) HashPassword(string password)
{
    var salt = RandomNumberGenerator.GetBytes(16);
    var hash = Rfc2898DeriveBytes.Pbkdf2(
        password, salt, 100_000, HashAlgorithmName.SHA256, 32);
    return (Convert.ToBase64String(hash), Convert.ToBase64String(salt));
}

static bool VerifyPassword(string password, string hashB64, string saltB64)
{
    var salt = Convert.FromBase64String(saltB64);
    var expected = Convert.FromBase64String(hashB64);
    var actual = Rfc2898DeriveBytes.Pbkdf2(
        password, salt, 100_000, HashAlgorithmName.SHA256, 32);
    return CryptographicOperations.FixedTimeEquals(expected, actual);
}

string password = "MySecurePass123!";
var (hash, salt) = HashPassword(password);

Console.WriteLine("=== Password Hashing ===");
Console.WriteLine($"  Password: {password}");
Console.WriteLine($"  Salt: {salt[..20]}...");
Console.WriteLine($"  Hash: {hash[..20]}...");

Console.WriteLine("\n=== Verify ===");
Console.WriteLine($"  Correct password: {VerifyPassword(password, hash, salt)}");
Console.WriteLine($"  Wrong password:   {VerifyPassword("wrong", hash, salt)}");

Console.WriteLine("\n=== ❌ Never do this ===");
Console.WriteLine("  storedPassword = password; // plain text");
Console.WriteLine("  MD5(password);             // broken algorithm");
