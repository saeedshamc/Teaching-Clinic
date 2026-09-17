// ============================================================
// جلسه ۳۰ — پروژه پیشرفته: Model و Repository
// ============================================================

using System.Text.Json;

var repo = new JsonStudentRepository();
await repo.LoadAsync();
await repo.AddAsync(new Student(1, "علی رضایی", "مهندسی نرم‌افزار", 18.5));
await repo.AddAsync(new Student(2, "مریم احمدی", "IT", 16.2));
await repo.SaveAsync();

var all = await repo.GetAllAsync();
Console.WriteLine("دانشجویان:");
foreach (var s in all)
    Console.WriteLine($"  [{s.Id}] {s.FullName} — GPA: {s.Gpa} {(s.IsHonorStudent ? "★" : "")}");

File.Delete("students-demo.json");

public record Student(int Id, string FullName, string Major, double Gpa)
{
    public bool IsHonorStudent => Gpa >= 17.0;
}

public interface IStudentRepository
{
    Task<List<Student>> GetAllAsync();
    Task AddAsync(Student student);
    Task SaveAsync();
}

public class JsonStudentRepository : IStudentRepository
{
    private readonly string _path = "students-demo.json";
    private List<Student> _students = new();

    public async Task LoadAsync()
    {
        if (!File.Exists(_path)) return;
        string json = await File.ReadAllTextAsync(_path);
        _students = JsonSerializer.Deserialize<List<Student>>(json) ?? new();
    }

    public Task<List<Student>> GetAllAsync() => Task.FromResult(_students.ToList());

    public Task AddAsync(Student student)
    {
        _students.Add(student);
        return Task.CompletedTask;
    }

    public async Task SaveAsync()
    {
        var json = JsonSerializer.Serialize(_students, new JsonSerializerOptions { WriteIndented = true });
        await File.WriteAllTextAsync(_path, json);
    }
}
