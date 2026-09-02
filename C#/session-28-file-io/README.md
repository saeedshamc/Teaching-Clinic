# جلسه ۲۸: File I/O (کار با فایل و پوشه) — ۱.۵ ساعت

## 🎯 اهداف یادگیری

- خواندن و نوشتن فایل با `File` و `FileStream`
- کار با مسیرها: `Path` class
- مدیریت پوشه‌ها: `Directory`
- async file I/O با `ReadAllTextAsync` / `WriteAllTextAsync`
- مدیریت exception (فایل وجود ندارد، دسترسی)
- الگوهای real-world: log file، config ساده، backup

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | مرور File System |
| 10–25 | File.Read/Write |
| 25–40 | Path و Directory |
| 40–55 | StreamReader/Writer |
| 55–70 | async I/O |
| 70–85 | تمرین |
| 85–90 | جمع‌بندی + تکلیف |

---

## ۱. کلاس‌های اصلی

| کلاس | کاربرد |
|------|--------|
| `File` | عملیات ساده روی فایل |
| `Directory` | پوشه‌ها |
| `Path` | ترکیب و تحلیل مسیر |
| `FileStream` | کنترل سطح پایین |
| `StreamReader/Writer` | خواندن/نوشتن متنی |

---

## ۲. نوشتن و خواندن ساده

```csharp
string path = "notes.txt";

// نوشتن
File.WriteAllText(path, "سلام دنیا!\nخط دوم");

// خواندن
string content = File.ReadAllText(path);
Console.WriteLine(content);

// append
File.AppendAllText(path, "\nخط جدید");
```

---

## ۳. Path — مسیرهای cross-platform

```csharp
string folder = Path.Combine("data", "2024", "report.txt");
// data\2024\report.txt (Windows) یا data/2024/report.txt (Linux)

string fileName = Path.GetFileName(folder);     // report.txt
string dir = Path.GetDirectoryName(folder);       // data\2024
string ext = Path.GetExtension(folder);           // .txt
string full = Path.GetFullPath("notes.txt");      // مسیر مطلق
```

---

## ۴. Directory

```csharp
string dir = "logs";

if (!Directory.Exists(dir))
    Directory.CreateDirectory(dir);

string[] files = Directory.GetFiles(dir, "*.log");
string[] subdirs = Directory.GetDirectories(dir);

foreach (var file in files)
    Console.WriteLine(file);
```

---

## ۵. StreamReader — خط به خط

```csharp
using var reader = new StreamReader("large.txt");
string? line;
while ((line = reader.ReadLine()) is not null)
{
    Console.WriteLine(line);
}
```

**using** = dispose خودکار (بستن فایل)

---

## ۶. FileStream — کنترل بیشتر

```csharp
using var stream = new FileStream("data.bin", FileMode.Create);
byte[] data = { 1, 2, 3, 4, 5 };
await stream.WriteAsync(data);
```

---

## ۷. Async File I/O

```csharp
await File.WriteAllTextAsync("async.txt", "محتوای async");
string text = await File.ReadAllTextAsync("async.txt");

string[] lines = await File.ReadAllLinesAsync("async.txt");
await File.WriteAllLinesAsync("output.txt", lines);
```

---

## ۸. Exception Handling

```csharp
try
{
    string content = File.ReadAllText("missing.txt");
}
catch (FileNotFoundException)
{
    Console.WriteLine("فایل پیدا نشد");
}
catch (UnauthorizedAccessException)
{
    Console.WriteLine("دسترسی رد شد");
}
catch (IOException ex)
{
    Console.WriteLine($"خطای I/O: {ex.Message}");
}
```

---

## ۹. FileInfo و DirectoryInfo

```csharp
var info = new FileInfo("notes.txt");
Console.WriteLine($"اندازه: {info.Length} bytes");
Console.WriteLine($"آخرین تغییر: {info.LastWriteTime}");

info.CopyTo("backup.txt", overwrite: true);
info.Delete();
```

---

## ۱۰. الگوی Log File

```csharp
void WriteLog(string message)
{
    string logPath = Path.Combine("logs", $"{DateTime.Today:yyyy-MM-dd}.log");
    Directory.CreateDirectory("logs");
    string entry = $"[{DateTime.Now:HH:mm:ss}] {message}";
    File.AppendAllText(logPath, entry + Environment.NewLine);
}
```

---

## ۱۱. JSON/Text Config ساده

```csharp
// ذخیره تنظیمات
var settings = new Dictionary<string, string>
{
    ["Theme"] = "Dark",
    ["Language"] = "fa"
};
File.WriteAllLines("settings.ini",
    settings.Select(kv => $"{kv.Key}={kv.Value}"));

// بارگذاری
var loaded = File.ReadAllLines("settings.ini")
    .Select(line => line.Split('='))
    .ToDictionary(p => p[0], p => p[1]);
```

---

## ۱۲. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_concept.cs](./examples/01_concept.cs) | Read/Write پایه |
| [02_practice.cs](./examples/02_practice.cs) | Path و Directory |
| [03_real_world.cs](./examples/03_real_world.cs) | log file |
| [04_async_io.cs](./examples/04_async_io.cs) | async I/O |

---

## ۱۳. امنیت و Best Practices

- همیشه مسیر user input را validate کنید
- از `Path.GetFullPath` برای جلوگیری از path traversal
- فایل‌های بزرگ → Stream نه ReadAllText
- `using` برای dispose
- encoding: `Encoding.UTF8` برای فارسی

```csharp
File.WriteAllText(path, text, Encoding.UTF8);
```

---

## ۱۴. اشتباهات رایج

| اشتباه | مشکل |
|--------|------|
| `\` hardcode | cross-platform نشکند — Path.Combine |
| فراموش using | فایل lock می‌ماند |
| ReadAllText فایل 1GB | OutOfMemory |
| عدم check Exists | FileNotFoundException |
| relative path بدون base | مسیر اشتباه |

---

## ۱۵. تمرین کلاسی

1. فایل `students.txt` — هر خط یک نام، تعداد خطوط را بشمارید
2. کپی فایل با `File.Copy`
3. پوشه `backup/` بسازید و فایل‌ها را کپی کنید
4. log writer با timestamp

**تکلیف:** [question.md](./exercises/question.md)

---

## ۱۶. Temp و Special Folders

```csharp
string temp = Path.GetTempPath();
string appData = Environment.GetFolderPath(Environment.SpecialFolder.ApplicationData);
```

---

## 📚 مطالعه تکمیلی

- [File class](https://learn.microsoft.com/dotnet/api/system.io.file)
- [Async I/O best practices](https://learn.microsoft.com/dotnet/standard/io/async-i-o)

---

**جلسه قبل:** [۲۷ — Async/Await](../session-27-async-await/) | **بعد:** [۲۹ — JSON](../session-29-json-serialization/)
