// ============================================================
// جلسه ۳۰ — پروژه پیشرفته: Service Layer
// ============================================================

var repo = new InMemoryStudentRepository();
var service = new StudentService(repo);

await service.RegisterAsync("سارا", "Computer Science", 19.0);
await service.RegisterAsync("رضا", "Math", 15.5);
await service.RegisterAsync("نیما", "Physics", 17.8);

Console.WriteLine($"میانگین GPA: {await service.AverageGpaAsync():F2}");
Console.WriteLine($"حذف ID=2: {await service.RemoveAsync(2)}");
Console.WriteLine($"تعداد باقی‌مانده: {(await repo.GetAllAsync()).Count}");

public record Student(int Id, string FullName, string Major, double Gpa);

public interface IStudentRepository
{
    Task<List<Student>> GetAllAsync();
    Task<Student?> GetByIdAsync(int id);
    Task AddAsync(Student student);
    Task DeleteAsync(int id);
}

public class InMemoryStudentRepository : IStudentRepository
{
    private readonly List<Student> _data = new();
    public Task<List<Student>> GetAllAsync() => Task.FromResult(_data.ToList());
    public Task<Student?> GetByIdAsync(int id) =>
        Task.FromResult(_data.FirstOrDefault(s => s.Id == id));
    public Task AddAsync(Student s) { _data.Add(s); return Task.CompletedTask; }
    public Task DeleteAsync(int id) { _data.RemoveAll(s => s.Id == id); return Task.CompletedTask; }
}

public class StudentService
{
    private readonly IStudentRepository _repo;
    public StudentService(IStudentRepository repo) => _repo = repo;

    public async Task<Student> RegisterAsync(string name, string major, double gpa)
    {
        if (gpa < 0 || gpa > 20) throw new ArgumentOutOfRangeException(nameof(gpa));
        var all = await _repo.GetAllAsync();
        int id = all.Count == 0 ? 1 : all.Max(s => s.Id) + 1;
        var student = new Student(id, name, major, gpa);
        await _repo.AddAsync(student);
        return student;
    }

    public async Task<double> AverageGpaAsync()
    {
        var all = await _repo.GetAllAsync();
        return all.Count == 0 ? 0 : all.Average(s => s.Gpa);
    }

    public async Task<bool> RemoveAsync(int id)
    {
        if (await _repo.GetByIdAsync(id) is null) return false;
        await _repo.DeleteAsync(id);
        return true;
    }
}
