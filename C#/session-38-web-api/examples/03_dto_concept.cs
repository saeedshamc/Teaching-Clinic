// ============================================================
// جلسه ۳۸ — Web API: DTO Pattern
// ============================================================

using System.Text.Json;

var entity = PatientMapper.FromCreateDto(
    new CreatePatientRequestDto("علی رضایی", "1234567890"), id: 1);

string json = JsonSerializer.Serialize(PatientMapper.ToDto(entity),
    new JsonSerializerOptions { WriteIndented = true });

Console.WriteLine("=== API Response (DTO) ===");
Console.WriteLine(json);

bool IsValid(CreatePatientRequestDto dto) =>
    !string.IsNullOrWhiteSpace(dto.Name) && dto.NationalCode.Length == 10;
Console.WriteLine($"\nValidation: {IsValid(new CreatePatientRequestDto("Ali", "1234567890"))}");

public record PatientResponseDto(int Id, string Name, string CreatedAt);
public record CreatePatientRequestDto(string Name, string NationalCode);

public class PatientEntity
{
    public int Id { get; set; }
    public string Name { get; set; } = "";
    public string NationalCode { get; set; } = "";
    public string InternalNotes { get; set; } = "";
    public DateTime CreatedAt { get; set; }
}

public static class PatientMapper
{
    public static PatientResponseDto ToDto(PatientEntity entity) =>
        new(entity.Id, entity.Name, entity.CreatedAt.ToString("yyyy-MM-dd"));

    public static PatientEntity FromCreateDto(CreatePatientRequestDto dto, int id) =>
        new()
        {
            Id = id,
            Name = dto.Name,
            NationalCode = dto.NationalCode,
            InternalNotes = "auto-created",
            CreatedAt = DateTime.UtcNow
        };
}
