// ============================================================
// جلسه 27 — Async/Await
// فایل: 01_async_basic.cs
// ============================================================
async Task DelayHello()
{
    await Task.Delay(500);
    Console.WriteLine("Done");
}
await DelayHello();
