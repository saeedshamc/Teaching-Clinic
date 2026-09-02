// ============================================================
// جلسه 27 — Async/Await
// فایل: 03_parallel_tasks.cs
// ============================================================
var t1 = Task.Run(async () => { await Task.Delay(200); return 1; });
var t2 = Task.Run(async () => { await Task.Delay(100); return 2; });
int[] results = await Task.WhenAll(t1, t2);
Console.WriteLine(string.Join(",", results));
