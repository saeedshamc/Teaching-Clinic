// ============================================================
// جلسه 27 — Async/Await
// فایل: 02_async_return.cs
// ============================================================
async Task<int> ComputeAsync() { await Task.Delay(100); return 42; }
int value = await ComputeAsync();
Console.WriteLine(value);
