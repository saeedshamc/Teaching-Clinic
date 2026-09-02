// ============================================================
// جلسه ۲۸ — File I/O: Path و Directory
// ============================================================

// ترکیب مسیر — cross-platform
string dataDir = Path.Combine("data", "users");
string filePath = Path.Combine(dataDir, "profile.txt");

Console.WriteLine($"مسیر کامل: {Path.GetFullPath(filePath)}");
Console.WriteLine($"نام فایل: {Path.GetFileName(filePath)}");
Console.WriteLine($"پسوند: {Path.GetExtension(filePath)}");
Console.WriteLine($"پوشه: {Path.GetDirectoryName(filePath)}");

// ساخت پوشه
Directory.CreateDirectory(dataDir);

// نوشتن فایل در پوشه
File.WriteAllText(filePath, "نام: علی\nسن: 25");

// لیست فایل‌های پوشه
Console.WriteLine("\nفایل‌های data/users:");
foreach (var file in Directory.GetFiles(dataDir))
    Console.WriteLine($"  {file} — {new FileInfo(file).Length} bytes");

// کپی فایل
string backupPath = Path.Combine(dataDir, "profile-backup.txt");
File.Copy(filePath, backupPath, overwrite: true);
Console.WriteLine($"\nکپی شد به: {backupPath}");

// پاکسازی
Directory.Delete("data", recursive: true);
Console.WriteLine("پوشه data حذف شد.");
