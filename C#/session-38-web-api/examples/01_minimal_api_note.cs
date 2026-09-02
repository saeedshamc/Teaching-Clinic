// ============================================================
// جلسه ۳۸ — Web API: Minimal API (مفهومی)
// ============================================================

// برای اجرای واقعی:
// dotnet new webapi -n ClinicApi
// سپس Program.cs:

Console.WriteLine("=== Minimal API Structure ===");
const string minimalApi = """
var builder = WebApplication.CreateBuilder(args);
var app = builder.Build();

var patients = new List<Patient>();

app.MapGet("/api/patients", () => Results.Ok(patients));

app.MapGet("/api/patients/{id}", (int id) =>
{
    var p = patients.FirstOrDefault(x => x.Id == id);
    return p is null ? Results.NotFound() : Results.Ok(p);
});

app.MapPost("/api/patients", (CreatePatientDto dto) =>
{
    var patient = new Patient { Id = patients.Count + 1, Name = dto.Name };
    patients.Add(patient);
    return Results.Created($"/api/patients/{patient.Id}", patient);
});

app.MapDelete("/api/patients/{id}", (int id) =>
{
    var removed = patients.RemoveAll(p => p.Id == id);
    return removed > 0 ? Results.NoContent() : Results.NotFound();
});

app.Run();
""";

Console.WriteLine(minimalApi);
Console.WriteLine("\nEndpoints:");
Console.WriteLine("  GET    /api/patients");
Console.WriteLine("  GET    /api/patients/{id}");
Console.WriteLine("  POST   /api/patients");
Console.WriteLine("  DELETE /api/patients/{id}");
Console.WriteLine("\nRun: dotnet new webapi && dotnet run → Swagger UI");

record Patient(int Id, string Name);
record CreatePatientDto(string Name);
