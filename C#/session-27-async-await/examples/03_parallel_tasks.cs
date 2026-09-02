// ============================================================
// جلسه ۲۷ — Async/Await: اجرای موازی
// ============================================================

async Task<string> DownloadAsync(string name, int delayMs)
{
    Console.WriteLine($"  → شروع دانلود {name}");
    await Task.Delay(delayMs);
    Console.WriteLine($"  ✓ پایان {name}");
    return $"داده-{name}";
}

// سریال — کند (800 + 600 + 400 = 1800ms)
Console.WriteLine("=== سریال ===");
var sw = System.Diagnostics.Stopwatch.StartNew();
var r1 = await DownloadAsync("A", 800);
var r2 = await DownloadAsync("B", 600);
sw.Stop();
Console.WriteLine($"سریال: {sw.ElapsedMilliseconds}ms\n");

// موازی — سریع (~800ms = بیشترین delay)
Console.WriteLine("=== موازی (WhenAll) ===");
sw.Restart();
var t1 = DownloadAsync("X", 800);
var t2 = DownloadAsync("Y", 600);
var t3 = DownloadAsync("Z", 400);
string[] results = await Task.WhenAll(t1, t2, t3);
sw.Stop();
Console.WriteLine($"WhenAll: {sw.ElapsedMilliseconds}ms — {string.Join(", ", results)}");

// WhenAny — اولین پاسخ
Console.WriteLine("\n=== WhenAny ===");
var winner = await Task.WhenAny(
    DownloadAsync("کند", 2000),
    DownloadAsync("سریع", 300)
);
Console.WriteLine($"برنده: {await winner}");
