// ============================================================
// جلسه ۲۷ — Async/Await: مفهوم پایه
// ============================================================

async Task SayHelloWithDelayAsync(string name, int delayMs)
{
    Console.WriteLine($"[{DateTime.Now:HH:mm:ss}] شروع خوش‌آمد {name}");
    await Task.Delay(delayMs);  // انتظار async — thread block نمی‌شود
    Console.WriteLine($"[{DateTime.Now:HH:mm:ss}] سلام {name}!");
}

Console.WriteLine("=== قبل از await ===");
await SayHelloWithDelayAsync("علی", 800);
Console.WriteLine("=== بعد از await ===");

// مقایسه با Thread.Sleep (blocking)
Console.WriteLine("\n--- Sync blocking ---");
Console.WriteLine($"[{DateTime.Now:HH:mm:ss}] قبل Sleep");
Thread.Sleep(500);
Console.WriteLine($"[{DateTime.Now:HH:mm:ss}] بعد Sleep");
