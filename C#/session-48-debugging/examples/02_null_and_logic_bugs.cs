// ============================================================
// جلسه ۴۸ — Null و Logic Bugs
// ============================================================

class Patient { public string? Name { get; set; } public Address? Address { get; set; } }
class Address { public string? Street { get; set; } public string? City { get; set; } }

// ❌ Bug: NullReferenceException
string GetCityBad(Patient? patient)
{
    return patient.Address.City!; // crash if null
}

// ✅ Fix: null-conditional
string GetCityGood(Patient? patient)
{
    return patient?.Address?.City ?? "Unknown";
}

// ❌ Bug: off-by-one
double CalculateAverageBad(int[] scores)
{
    int sum = 0;
    for (int i = 0; i <= scores.Length; i++) // index out of range!
        sum += scores[i];
    return (double)sum / scores.Length;
}

// ✅ Fix
double CalculateAverageGood(int[] scores)
{
    if (scores.Length == 0) return 0;
    int sum = 0;
    for (int i = 0; i < scores.Length; i++)
        sum += scores[i];
    return (double)sum / scores.Length;
}

Console.WriteLine("=== Null Bug Fix ===");
var p1 = new Patient { Name = "Ali", Address = new Address { City = "Tehran" } };
var p2 = new Patient { Name = "Guest" };
Console.WriteLine($"  p1 city: {GetCityGood(p1)}");
Console.WriteLine($"  p2 city: {GetCityGood(p2)}");

Console.WriteLine("\n=== Logic Bug Fix ===");
var scores = new[] { 80, 90, 85 };
Console.WriteLine($"  Average: {CalculateAverageGood(scores)}");

try { CalculateAverageBad(scores); }
catch (IndexOutOfRangeException ex)
{
    Console.WriteLine($"  ❌ Off-by-one bug: {ex.GetType().Name}");
}
