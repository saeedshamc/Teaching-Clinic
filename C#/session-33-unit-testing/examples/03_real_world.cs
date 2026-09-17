// ============================================================
// جلسه ۳۳ — Unit Testing: test service با fake repository
// ============================================================

await RunAsyncTests();

async Task RunAsyncTests()
{
    var repo = new FakeStudentRepository();
    repo.Data.AddRange(new[]
    {
        new Student(1, "A", 18), new Student(2, "B", 16),
        new Student(3, "C", 19), new Student(4, "D", 14)
    });
    var svc = new StudentService(repo);

    double avg = await svc.AverageGpaAsync();
    Console.WriteLine($"AverageGpa: {avg} — {(avg == 16.75 ? "✓" : "✗")}");

    int honors = await svc.HonorCountAsync();
    Console.WriteLine($"HonorCount: {honors} — {(honors == 2 ? "✓" : "✗")}");

    var emptySvc = new StudentService(new FakeStudentRepository());
    double emptyAvg = await emptySvc.AverageGpaAsync();
    Console.WriteLine($"EmptyAvg: {emptyAvg} — {(emptyAvg == 0 ? "✓" : "✗")}");
}

public record Student(int Id, string Name, double Gpa);

public interface IStudentRepository
{
    Task<List<Student>> GetAllAsync();
}

public class FakeStudentRepository : IStudentRepository
{
    public List<Student> Data { get; } = new();
    public Task<List<Student>> GetAllAsync() => Task.FromResult(Data.ToList());
}

public class StudentService
{
    private readonly IStudentRepository _repo;
    public StudentService(IStudentRepository repo) => _repo = repo;

    public async Task<double> AverageGpaAsync()
    {
        var all = await _repo.GetAllAsync();
        return all.Count == 0 ? 0 : all.Average(s => s.Gpa);
    }

    public async Task<int> HonorCountAsync(double threshold = 17)
    {
        var all = await _repo.GetAllAsync();
        return all.Count(s => s.Gpa >= threshold);
    }
}
