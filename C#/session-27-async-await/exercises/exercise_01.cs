// ============================================================
// پاسخ نمونه — تمرین جلسه ۲۷
// ============================================================

async Task CountdownAsync(int n)
{
    for (int i = n; i >= 1; i--)
    {
        Console.WriteLine(i);
        await Task.Delay(300);
    }
    Console.WriteLine("شروع!");
}

async Task<string> FetchDataAsync(string name, int delayMs)
{
    await Task.Delay(delayMs);
    return $"[{name}]";
}

async Task RetryAsync(Func<Task> action, int maxAttempts)
{
    for (int attempt = 1; attempt <= maxAttempts; attempt++)
    {
        try
        {
            await action();
            return;
        }
        catch (Exception ex) when (attempt < maxAttempts)
        {
            Console.WriteLine($"تلاش {attempt} ناموفق: {ex.Message}");
            await Task.Delay(500);
        }
    }
}

await CountdownAsync(3);

var results = await Task.WhenAll(
    FetchDataAsync("A", 400),
    FetchDataAsync("B", 200),
    FetchDataAsync("C", 600)
);
Console.WriteLine(string.Join(" ", results));

int attempts = 0;
await RetryAsync(async () =>
{
    attempts++;
    if (attempts < 3) throw new Exception("شکست");
    Console.WriteLine("موفق در تلاش سوم");
}, 3);
