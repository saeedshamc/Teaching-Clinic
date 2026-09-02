# جلسه ۳۰: پروژه پیشرفته (Advanced Console Project) — ۱.۵ ساعت

## 🎯 اهداف یادگیری

- ترکیب مفاهیم جلسات ۲۰–۲۹ در یک پروژه یکپارچه
- طراحی لایه‌بندی ساده: Models, Services, Repository
- استفاده از async، File I/O، JSON، LINQ، OOP
- پیاده‌سازی CRUD برای موجودیت `Student`
- نوشتن کد maintainable با separation of concerns
- آماده‌سازی ذهنی برای topics enterprise (DI، Testing، Web API)

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–15 | معرفی پروژه و requirements |
| 15–30 | طراحی Models و Interfaces |
| 30–50 | Repository با JSON file |
| 50–65 | Service layer |
| 65–80 | Program/Menu |
| 80–90 | تست + تکلیف |

---

## ۱. شرح پروژه — Student Manager

یک **Console App** برای مدیریت دانشجو:

- افزودن / ویرایش / حذف / لیست دانشجو
- ذخیره داده در `students.json`
- جستجو با LINQ
- گزارش آمار (میانگین نمره، تعداد)

---

## ۲. ساختار پوشه (پیشنهادی)

```
StudentManager/
  Models/
    Student.cs
  Repositories/
    IStudentRepository.cs
    JsonStudentRepository.cs
  Services/
    StudentService.cs
  Program.cs
```

در جلسه با فایل‌های تک‌فایلی مفاهیم را یاد می‌گیریم.

---

## ۳. Model

```csharp
public record Student(int Id, string FullName, string Major, double Gpa)
{
    public bool IsHonorStudent => Gpa >= 17.0;
}
```

---

## ۴. Repository Pattern

```csharp
public interface IStudentRepository
{
    Task<List<Student>> GetAllAsync();
    Task<Student?> GetByIdAsync(int id);
    Task AddAsync(Student student);
    Task UpdateAsync(Student student);
    Task DeleteAsync(int id);
    Task SaveAsync();
}
```

**Repository** = جداسازی logic دسترسی به داده از business logic.

---

## ۵. JSON Repository

```csharp
public class JsonStudentRepository : IStudentRepository
{
    private readonly string _filePath = "students.json";
    private List<Student> _cache = new();

    public async Task LoadAsync()
    {
        if (!File.Exists(_filePath)) return;
        string json = await File.ReadAllTextAsync(_filePath);
        _cache = JsonSerializer.Deserialize<List<Student>>(json) ?? new();
    }

    public async Task SaveAsync()
    {
        var options = new JsonSerializerOptions { WriteIndented = true };
        string json = JsonSerializer.Serialize(_cache, options);
        await File.WriteAllTextAsync(_filePath, json);
    }
}
```

---

## ۶. Service Layer

```csharp
public class StudentService
{
    private readonly IStudentRepository _repo;

    public StudentService(IStudentRepository repo) => _repo = repo;

    public async Task<Student> RegisterAsync(string name, string major, double gpa)
    {
        var all = await _repo.GetAllAsync();
        int newId = all.Count == 0 ? 1 : all.Max(s => s.Id) + 1;
        var student = new Student(newId, name, major, gpa);
        await _repo.AddAsync(student);
        await _repo.SaveAsync();
        return student;
    }

    public async Task<double> AverageGpaAsync()
    {
        var all = await _repo.GetAllAsync();
        return all.Count == 0 ? 0 : all.Average(s => s.Gpa);
    }
}
```

---

## ۷. Menu-driven Program

```csharp
while (true)
{
    Console.WriteLine("1. لیست  2. افزودن  3. حذف  4. آمار  0. خروج");
    var choice = Console.ReadLine();
    switch (choice)
    {
        case "1": await ListStudents(); break;
        case "2": await AddStudent(); break;
        // ...
        case "0": return;
    }
}
```

---

## ۸. LINQ در پروژه

```csharp
// دانشجویان ممتاز
var honors = students.Where(s => s.IsHonorStudent).OrderByDescending(s => s.Gpa);

// گروه‌بندی بر اساس رشته
var byMajor = students.GroupBy(s => s.Major);

// جستجو
var found = students.FirstOrDefault(s =>
    s.FullName.Contains(searchTerm, StringComparison.OrdinalIgnoreCase));
```

---

## ۹. Error Handling

```csharp
try
{
    await service.DeleteAsync(id);
}
catch (InvalidOperationException ex)
{
    Console.WriteLine($"خطا: {ex.Message}");
}
```

---

## ۱۰. Async All the Way

تمام I/O (file read/write) باید async باشد:

```csharp
await repository.LoadAsync();
await service.RegisterAsync(...);
await repository.SaveAsync();
```

---

## ۱۱. Extension Methods در پروژه

```csharp
public static class StudentExtensions
{
    public static string ToDisplayString(this Student s) =>
        $"[{s.Id}] {s.FullName} — {s.Major} — GPA: {s.Gpa:F1}";
}
```

---

## ۱۲. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_concept.cs](./examples/01_concept.cs) | Model و Repository |
| [02_practice.cs](./examples/02_practice.cs) | Service layer |
| [03_real_world.cs](./examples/03_real_world.cs) | CRUD کامل |
| [04_linq_reports.cs](./examples/04_linq_reports.cs) | گزارش LINQ |

---

## ۱۳. Design Principles

| اصل | توضیح |
|-----|--------|
| SRP | هر class یک مسئولیت |
| DIP | Service به interface وابسته |
| DRY | JSON logic فقط در Repository |
| KISS | Console ساده، بدون over-engineering |

---

## ۱۴. تست دستی Checklist

- [ ] افزودن 3 دانشجو
- [ ] لیست همه
- [ ] حذف یکی
- [ ] فایل JSON درست ذخیره شد
- [ ] restart برنامه — داده persist شد
- [ ] میانگین GPA درست

---

## ۱۵. اشتباهات رایج

| اشتباه | مشکل |
|--------|------|
| همه logic در Program.cs | untestable |
| sync file I/O | block |
| Id دستی تکراری | duplicate key |
| بدون validation | داده نامعتبر |
| Save فراموش | از دست رفتن داده |

---

## ۱۶. تمرین کلاسی

1. فیلد `Email` به Student اضافه کنید
2. متد `SearchByMajor(major)` بنویسید
3. export به CSV (bonus)

**تکلیف:** [question.md](./exercises/question.md)

---

## ۱۷. گام بعد — Enterprise

این پروژه پایه‌ای برای:
- **جلسه ۳۳:** Unit Test روی Service
- **جلسه ۳۶:** DI برای Repository
- **جلسه ۳۷:** EF Core به جای JSON
- **جلسه ۳۸:** Web API به جای Console

---

## 📚 مطالعه تکمیلی

- Repository Pattern
- Clean Architecture (Robert Martin) — فصول intro

---

**جلسه قبل:** [۲۹ — JSON](../session-29-json-serialization/) | **بعد:** [۳۱ — Namespaces](../session-31-namespaces-assemblies/)
