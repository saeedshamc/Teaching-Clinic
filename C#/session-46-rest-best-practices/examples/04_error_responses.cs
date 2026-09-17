// ============================================================
// جلسه ۴۶ — Error Responses (Problem Details)
// ============================================================

record ProblemDetails(string Type, string Title, int Status, string? Detail = null, Dictionary<string, string[]>? Errors = null);

class ApiController
{
    public (int status, object body) CreatePatient(string? name, string? phone)
    {
        var errors = new Dictionary<string, string[]>();

        if (string.IsNullOrWhiteSpace(name))
            errors["FullName"] = new[] { "نام الزامی است" };
        if (string.IsNullOrWhiteSpace(phone) || phone.Length < 10)
            errors["Phone"] = new[] { "شماره تلفن نامعتبر است" };

        if (errors.Count > 0)
        {
            var problem = new ProblemDetails(
                Type: "https://api.clinic.com/errors/validation",
                Title: "Validation Error",
                Status: 400,
                Detail: "One or more fields are invalid",
                Errors: errors
            );
            Console.WriteLine($"  POST /api/patients → 400 Validation Error");
            return (400, problem);
        }

        Console.WriteLine($"  POST /api/patients → 201 Created");
        return (201, new { id = 1, name, phone });
    }

    public (int status, object body) GetPatient(int id)
    {
        if (id <= 0)
        {
            Console.WriteLine($"  GET /api/patients/{id} → 404");
            return (404, new ProblemDetails(
                "https://api.clinic.com/errors/not-found",
                "Not Found", 404, $"Patient {id} not found"));
        }
        return (200, new { id, name = "Ali" });
    }
}

var ctrl = new ApiController();
ctrl.CreatePatient("", "123");
ctrl.CreatePatient("Ali", "09121234567");
ctrl.GetPatient(99);
