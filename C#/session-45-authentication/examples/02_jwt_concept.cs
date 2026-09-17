// ============================================================
// جلسه ۴۵ — JWT Concept (شبیه‌سازی)
// ============================================================

using System.Security.Cryptography;
using System.Text;
using System.Text.Json;

record JwtHeader(string Alg, string Typ);
record JwtPayload(string Sub, string Name, string Role, long Exp);
record JwtToken(string Header, string Payload, string Signature)
{
    public string Encoded => $"{Header}.{Payload}.{Signature}";
}

string Base64UrlEncode(string json)
{
    var bytes = Encoding.UTF8.GetBytes(json);
    return Convert.ToBase64String(bytes).TrimEnd('=').Replace('+', '-').Replace('/', '_');
}

string Sign(string header, string payload, string secret)
{
    var data = $"{header}.{payload}";
    using var hmac = new HMACSHA256(Encoding.UTF8.GetBytes(secret));
    var hash = hmac.ComputeHash(Encoding.UTF8.GetBytes(data));
    return Convert.ToBase64String(hash).TrimEnd('=').Replace('+', '-').Replace('/', '_');
}

string secret = "my-super-secret-key-at-least-32-chars!";
var header = Base64UrlEncode(JsonSerializer.Serialize(new JwtHeader("HS256", "JWT")));
var payload = Base64UrlEncode(JsonSerializer.Serialize(new JwtPayload(
    Sub: "user123", Name: "Ali", Role: "Admin",
    Exp: DateTimeOffset.UtcNow.AddHours(1).ToUnixTimeSeconds()
)));
var signature = Sign(header, payload, secret);
var token = new JwtToken(header, payload, signature);

Console.WriteLine("=== JWT Generated ===");
Console.WriteLine($"  Token: {token.Encoded[..60]}...");

Console.WriteLine("\n=== JWT Validate ===");
var parts = token.Encoded.Split('.');
var expectedSig = Sign(parts[0], parts[1], secret);
bool valid = parts[2] == expectedSig;
Console.WriteLine($"  Signature valid: {valid}");

var payloadJson = Encoding.UTF8.GetString(
    Convert.FromBase64String(parts[1].Replace('-', '+').Replace('_', '/') + "=="));
var decoded = JsonSerializer.Deserialize<JwtPayload>(payloadJson)!;
bool expired = decoded.Exp < DateTimeOffset.UtcNow.ToUnixTimeSeconds();
Console.WriteLine($"  User: {decoded.Name}, Role: {decoded.Role}");
Console.WriteLine($"  Expired: {expired}");
