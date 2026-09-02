// ============================================================
// جلسه ۳۰ — پروژه پیشرفته: CRUD کامل
// ============================================================

using System.Text.Json;

var mgr = new StudentManager();
await mgr.InitializeAsync();

await mgr.CreateAsync("علی", "SE", 18.0);
await mgr.CreateAsync("زهرا", "AI", 19.5);
await mgr.UpdateAsync(1, "علی محمدی", "SE", 18.5);
await mgr.DeleteAsync(2);

Console.WriteLine("=== CRUD Result ===");
foreach (var s in await mgr.ReadAllAsync())
    Console.WriteLine($"  {s.Id}: {s.FullName} ({s.Major}) GPA={s.Gpa}");

File.Delete("students.json");

public record Student(int Id, string FullName, string Major, double Gpa);

public class StudentManager
{
    private readonly string _path = "students.json";
    private List<Student> _students = new();

    public async Task InitializeAsync()
    {
        if (!File.Exists(_path)) return;
        _students = JsonSerializer.Deserialize<List<Student>>(
            await File.ReadAllTextAsync(_path)) ?? new();
    }

    public async Task<Student> CreateAsync(string name, string major, double gpa)
    {
        int id = _students.Count == 0 ? 1 : _students.Max(s => s.Id) + 1;
        var s = new Student(id, name, major, gpa);
        _students.Add(s);
        await PersistAsync();
        return s;
    }

    public Task<IReadOnlyList<Student>> ReadAllAsync() =>
        Task.FromResult<IReadOnlyList<Student>>(_students.AsReadOnly());

    public async Task<bool> UpdateAsync(int id, string name, string major, double gpa)
    {
        int idx = _students.FindIndex(s => s.Id == id);
        if (idx < 0) return false;
        _students[idx] = new Student(id, name, major, gpa);
        await PersistAsync();
        return true;
    }

    public async Task<bool> DeleteAsync(int id)
    {
        int removed = _students.RemoveAll(s => s.Id == id);
        if (removed == 0) return false;
        await PersistAsync();
        return true;
    }

    private async Task PersistAsync()
    {
        await File.WriteAllTextAsync(_path,
            JsonSerializer.Serialize(_students, new JsonSerializerOptions { WriteIndented = true }));
    }
}
