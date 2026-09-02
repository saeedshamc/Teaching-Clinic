// ============================================================
// جلسه 14 — Property و Constructor
// فایل: 03_readonly.cs
// ============================================================
class Config
{
    public readonly string Env;
    public Config(string env) => Env = env;
}
Console.WriteLine(new Config("dev").Env);
