// ============================================================
// جلسه ۲۷ — Async/Await: Task<T> و return types
// ============================================================

async Task<int> CalculateSumAsync(int[] numbers)
{
    await Task.Delay(300); // شبیه‌سازی پردازش
    return numbers.Sum();
}

async Task<string> FetchUserNameAsync(int userId)
{
    await Task.Delay(200);
    return userId switch
    {
        1 => "علی",
        2 => "مریم",
        _ => "مهمان"
    };
}

async Task ProcessAsync()
{
    int sum = await CalculateSumAsync(new[] { 10, 20, 30 });
    Console.WriteLine($"مجموع: {sum}");

    string name = await FetchUserNameAsync(2);
    Console.WriteLine($"کاربر: {name}");
}

await ProcessAsync();

// Task بدون return
async Task LogAsync(string message)
{
    await Task.Delay(100);
    Console.WriteLine($"[LOG] {message}");
}

await LogAsync("عملیات تمام شد");
