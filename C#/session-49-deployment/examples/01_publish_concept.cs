// ============================================================
// جلسه ۴۹ — Publish Concept
// ============================================================

// شبیه‌سازی dotnet publish output
class PublishProfile
{
    public string Configuration { get; set; } = "Release";
    public bool SelfContained { get; set; }
    public string Runtime { get; set; } = "win-x64";
    public bool SingleFile { get; set; }
}

class PublishSimulator
{
    public List<string> Publish(PublishProfile profile)
    {
        var output = new List<string>
        {
            $"Building {profile.Configuration}...",
            "  Restoring packages...",
            "  Compiling...",
            "  Generating output..."
        };

        var folder = "./publish";
        output.Add($"  Output: {folder}/");
        output.Add($"    ClinicApp.dll");
        output.Add($"    appsettings.json");

        if (profile.SelfContained)
        {
            output.Add($"    .NET Runtime ({profile.Runtime}) bundled");
            output.Add($"    Total size: ~70 MB");
        }
        else
        {
            output.Add($"    Framework-dependent (requires .NET 8 runtime)");
            output.Add($"    Total size: ~5 MB");
        }

        if (profile.SingleFile)
            output.Add($"    Single file: ClinicApp.exe (~80 MB)");

        output.Add("  ✓ Publish succeeded");
        return output;
    }
}

var sim = new PublishSimulator();

Console.WriteLine("=== dotnet publish -c Release ===");
foreach (var line in sim.Publish(new PublishProfile()))
    Console.WriteLine(line);

Console.WriteLine("\n=== dotnet publish --self-contained -r linux-x64 ===");
foreach (var line in sim.Publish(new PublishProfile { SelfContained = true, Runtime = "linux-x64" }))
    Console.WriteLine(line);

Console.WriteLine("\n=== Commands ===");
Console.WriteLine("  dotnet publish -c Release -o ./publish");
Console.WriteLine("  dotnet publish -r win-x64 --self-contained");
Console.WriteLine("  dotnet publish -p:PublishSingleFile=true");
