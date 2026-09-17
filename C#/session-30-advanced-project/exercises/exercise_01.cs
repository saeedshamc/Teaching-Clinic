// ============================================================
// پاسخ نمونه — تمرین جلسه ۳۰ (خلاصه)
// ============================================================

using System.Text.Json;

public record Student(int Id, string Name, double Gpa);

public class StudentApp
{
    private List<Student> _data = new();
    private const string Path = "app-students.json";

    public void Validate(string name, double gpa)
    {
        if (name.Length < 2) throw new ArgumentException("نام کوتاه");
        if (gpa is < 0 or > 20) throw new ArgumentOutOfRangeException(nameof(gpa));
    }

    public Student Add(string name, double gpa)
    {
        Validate(name, gpa);
        int id = _data.Count == 0 ? 1 : _data.Max(s => s.Id) + 1;
        var s = new Student(id, name, gpa);
        _data.Add(s);
        return s;
    }

    public void Save() =>
        File.WriteAllText(Path, JsonSerializer.Serialize(_data, new JsonSerializerOptions { WriteIndented = true }));

    public void Load()
    {
        if (!File.Exists(Path)) return;
        _data = JsonSerializer.Deserialize<List<Student>>(File.ReadAllText(Path)) ?? new();
    }

    public IEnumerable<Student> Top(int n) =>
        _data.OrderByDescending(s => s.Gpa).Take(n);
}

var app = new StudentApp();
app.Add("علی", 18);
app.Add("مریم", 19.5);
app.Save();
app.Load();
Console.WriteLine("Top:");
foreach (var s in app.Top(2)) Console.WriteLine($"  {s.Name}: {s.Gpa}");
File.Delete("app-students.json");
