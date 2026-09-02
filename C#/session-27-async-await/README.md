# جلسه ۲۷: Async/Await (برنامه‌نویسی ناهمزمان) — ۱.۵ ساعت

## 🎯 اهداف یادگیری

- تفاوت sync و async و دلیل نیاز به async
- نوشتن متد `async Task` و استفاده از `await`
- درک Thread Pool و non-blocking I/O (مفهومی)
- کار با `Task.WhenAll`, `Task.WhenAny`, `Task.Delay`
- مدیریت exception در async
- اجتناب از anti-patternهای رایج (`async void`, `.Result`, `.Wait()`)

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | مشکل blocking و UI freeze |
| 10–25 | async/await پایه |
| 25–40 | Task و return types |
| 40–55 | parallel tasks |
| 55–70 | exception handling |
| 70–85 | تمرین |
| 85–90 | جمع‌بندی + تکلیف |

---

## ۱. چرا Async؟

فرض کنید برنامه در حال دانلود فایل از اینترنت است:

```csharp
// ❌ Sync — thread اصلی block می‌شود
string data = DownloadFile(url);  // 5 ثانیه صبر...
Console.WriteLine("ادامه کار");
```

در UI (WPF/WinForms) → freeze. در Web → thread pool اشغال.

```csharp
// ✅ Async — thread آزاد می‌ماند
string data = await DownloadFileAsync(url);
Console.WriteLine("ادامه کار");
```

**await** = «منتظر نتیجه بمان، اما thread را block نکن»

---

## ۲. سینتکس پایه

```csharp
async Task DoWorkAsync()
{
    Console.WriteLine("شروع");
    await Task.Delay(1000);  // شبیه‌سازی I/O
    Console.WriteLine("پایان");
}

await DoWorkAsync();
```

### قوانین

| قانون | توضیح |
|--------|--------|
| `async` روی متد | الزامی |
| `await` داخل `async` | الزامی (معمولاً) |
| پسوند `Async` | convention (نه اجبار) |
| return `Task` | برای void-like async |
| return `Task<T>` | برای مقدار برگشتی |

---

## ۳. انواع Return Type

```csharp
async Task DoSomethingAsync() { }           // بدون return
async Task<int> GetNumberAsync() => 42;      // با return
async ValueTask<string> GetCachedAsync() { } // بهینه‌سازی حافظه
```

---

## ۴. Task.Delay vs Thread.Sleep

```csharp
Thread.Sleep(1000);   // ❌ thread را block می‌کند
await Task.Delay(1000); // ✅ async wait
```

---

## ۵. اجرای موازی — Task.WhenAll

```csharp
Task<string> t1 = FetchUserAsync(1);
Task<string> t2 = FetchUserAsync(2);
Task<string> t3 = FetchUserAsync(3);

string[] results = await Task.WhenAll(t1, t2, t3);
// هر سه همزمان اجرا می‌شوند
```

---

## ۶. Task.WhenAny — اولین پاسخ

```csharp
var tasks = new[] { SlowApi(), FastApi() };
Task completed = await Task.WhenAny(tasks);
Console.WriteLine("اولین پاسخ رسید");
```

---

## ۷. Exception Handling

```csharp
try
{
    await RiskyAsync();
}
catch (HttpRequestException ex)
{
    Console.WriteLine($"خطای شبکه: {ex.Message}");
}
```

⚠️ Exception در `Task.WhenAll` — اولین exception پرتاب می‌شود، بقیه در `AggregateException` پنهان‌اند.

---

## ۸. ConfigureAwait (مقدمه)

```csharp
await SomeAsync().ConfigureAwait(false);
// در library code: ادامه روی thread pool نه UI thread
```

در Console/Web API معمولاً نیاز نیست. در UI library مهم است.

---

## ۹. Anti-Patterns — هرگز!

| Anti-Pattern | مشکل |
|--------------|------|
| `task.Result` | deadlock در UI |
| `task.Wait()` | deadlock |
| `async void` | exception گم می‌شود (جز event handler) |
| `Task.Run` برای I/O | thread اضافی بی‌دلیل |
| fire-and-forget بدون log | خطا گم می‌شود |

```csharp
// ❌ Deadlock در UI
var result = GetDataAsync().Result;

// ✅
var result = await GetDataAsync();
```

---

## ۱۰. async در Main

```csharp
// .NET 6+ top-level statements
await MainAsync();

async Task MainAsync()
{
    await DoWorkAsync();
}
```

---

## ۱۱. CancellationToken (مقدمه)

```csharp
async Task LongWorkAsync(CancellationToken ct)
{
    for (int i = 0; i < 10; i++)
    {
        ct.ThrowIfCancellationRequested();
        await Task.Delay(500, ct);
    }
}

using var cts = new CancellationTokenSource(TimeSpan.FromSeconds(2));
try
{
    await LongWorkAsync(cts.Token);
}
catch (OperationCanceledException)
{
    Console.WriteLine("لغو شد");
}
```

---

## ۱۲. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_async_basic.cs](./examples/01_async_basic.cs) | async/await پایه |
| [02_async_return.cs](./examples/02_async_return.cs) | Task<T> و return |
| [03_parallel_tasks.cs](./examples/03_parallel_tasks.cs) | WhenAll/WhenAny |
| [04_exception_cancellation.cs](./examples/04_exception_cancellation.cs) | exception و cancel |

---

## ۱۳. مثال واقعی — دانلود چند API

```csharp
async Task<string[]> FetchAllProductsAsync(int[] ids)
{
    var tasks = ids.Select(id => FetchProductAsync(id));
    return await Task.WhenAll(tasks);
}
```

---

## ۱۴. State Machine (مفهومی)

کامپایلر `async/await` را به state machine تبدیل می‌کند. لازم نیست دستی بنویسید، اما بدانید:

- هر `await` = نقطه resume
- متغیرهای local بین awaitها حفظ می‌شوند

---

## ۱۵. اشتباهات رایج

| اشتباه | راه‌حل |
|--------|--------|
| فراموش async | compile error روی await |
| return; در Task<T> | باید مقدار برگردانید |
| await در loop سریال | از WhenAll برای موازی استفاده کنید |
| async all the way | async را تا بالا propagate کنید |

---

## ۱۶. تمرین کلاسی

1. متد `CountdownAsync(n)` — از n تا 1 با Delay
2. سه Task موازی با WhenAll
3. try/catch دور async method
4. CancellationToken با timeout 3 ثانیه

**تکلیف:** [question.md](./exercises/question.md)

---

## ۱۷. مقایسه Sync vs Async

| سناریو | Sync | Async |
|--------|------|-------|
| File read | block | non-block |
| HTTP call | block | non-block |
| CPU-heavy | OK | Task.Run |
| UI update | freeze | smooth |

---

## 📚 مطالعه تکمیلی

- [Microsoft Docs — Async](https://learn.microsoft.com/dotnet/csharp/asynchronous-programming/)
- [Task-based Asynchronous Pattern (TAP)](https://learn.microsoft.com/dotnet/standard/asynchronous-programming-patterns/task-based-asynchronous-pattern-tap)

---

**جلسه قبل:** [۲۶ — Extension Methods](../session-26-extension-methods/) | **بعد:** [۲۸ — File I/O](../session-28-file-io/)
