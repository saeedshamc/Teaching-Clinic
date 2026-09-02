// ============================================================
// جلسه ۲۷ — Async/Await: exception و cancellation
// ============================================================

async Task RiskyOperationAsync(bool shouldFail)
{
    await Task.Delay(200);
    if (shouldFail)
        throw new InvalidOperationException("عملیات ناموفق بود!");
    Console.WriteLine("عملیات موفق");
}

// Exception handling
try
{
    await RiskyOperationAsync(shouldFail: true);
}
catch (InvalidOperationException ex)
{
    Console.WriteLine($"خطا گرفته شد: {ex.Message}");
}

// CancellationToken
async Task LongRunningAsync(CancellationToken ct)
{
    for (int i = 1; i <= 10; i++)
    {
        ct.ThrowIfCancellationRequested();
        Console.WriteLine($"  گام {i}/10");
        await Task.Delay(400, ct);
    }
}

using var cts = new CancellationTokenSource();
cts.CancelAfter(TimeSpan.FromSeconds(1.5)); // لغو بعد از 1.5 ثانیه

try
{
    await LongRunningAsync(cts.Token);
}
catch (OperationCanceledException)
{
    Console.WriteLine("عملیات لغو شد (timeout)");
}
